package com.apollo.repos;

import com.apollo.entities.Strategy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface StrategyRepository extends JpaRepository<Strategy, Integer> {
    Collection<Strategy> findStrategiesByOnoffIs(Boolean isOnoff);

    @Query(value = "select distinct stock from strategies where onoff = true", nativeQuery = true)
    Collection<String> findStockByOnoffIsTrue();
}