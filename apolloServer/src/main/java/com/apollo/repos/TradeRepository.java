package com.apollo.repos;

import com.apollo.entities.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Collection;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Integer> {
    @Query(value = "select * from trades as t inner join strategies as s on t.strategyid = :id;", nativeQuery = true)
    Collection<Trade> getAllByStrategy_Id(@Param("id") int id);

    @Query(value = "select * from trades as t inner join strategies as s on t.strategyid = :id and t.tradedate > :time", nativeQuery = true)
    Collection<Trade> getAllByStrategy_IdAnAndTradeDate(@Param("id") int id, @Param("time") Timestamp time);
}