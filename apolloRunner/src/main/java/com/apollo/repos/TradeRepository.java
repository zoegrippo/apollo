package com.apollo.repos;

import com.apollo.entities.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Collection;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Integer> {
    @Query(value = "select * from trades where strategyid = :id", nativeQuery = true)
    Collection<Trade> getAllByStrategy_Id(@Param("id") int id);

    @Query(value = "select * from trades where strategyid = :sid and id > :tid", nativeQuery = true)
    Collection<Trade> getAllByStrategy_IdAndTrade_Id(@Param("sid") int sid, @Param("tid") int tid);
}