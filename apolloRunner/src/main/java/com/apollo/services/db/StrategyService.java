package com.apollo.services.db;

import com.apollo.entities.Strategy;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface StrategyService {
    Collection<Strategy> getActive();

    Collection<String> getTickers();
}