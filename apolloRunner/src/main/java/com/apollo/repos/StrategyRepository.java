package com.apollo.repos;
import com.apollo.entities.Strategy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface StrategyRepository extends JpaRepository<Strategy, Integer> {
    Collection<Strategy> findStrategiesByRunningIs(Boolean isRunning);
    Collection<String> findStockByRunningIsTrue();
}