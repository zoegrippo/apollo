package com.apollo.service;

import com.apollo.entities.Strategy;
import java.util.Collection;

public interface StrategyService {
    Collection<Strategy> getActive();
    Collection<String> getTickers();
}
