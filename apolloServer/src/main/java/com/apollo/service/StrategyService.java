package com.apollo.service;

import com.apollo.entities.Strategy;

import java.util.ArrayList;
import java.util.Collection;

public interface StrategyService {
    //only at the Service level
    Collection<Strategy> getActive();
    Collection<String> getTickers();

    // at the /strategy endpoint
    Collection<Strategy> getAll();
    String createOrUpdate(Strategy s);

    // at the /strategy/{id} endpoint
    Strategy getById(int id);
    String deleteById(int id);
}
