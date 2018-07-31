package com.apollo.service;

import com.apollo.entities.Strategy;
import com.apollo.entities.User;
import com.apollo.repos.StrategyRepository;
import com.apollo.service.StrategyServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestStrategyService {

    @Mock
    StrategyRepository data;

    @InjectMocks
    StrategyServiceImpl strategyImpl;

    @Test
    public void canGetActiveStrategies() {
        Strategy s1 = new Strategy("Bollinger Bands", true, 200, "AAPL", 0.2, 0.1, (double) 2, 20, new User("Zoe"));
        Strategy s2 = new Strategy("Bollinger Bands", false, 200, "TSLA", 0.2, 0.1, (double) 2, 20, new User("Zoe"));
        Collection<Strategy> strats = new ArrayList<>();
        strats.add(s1);
        List<Strategy> allStrats = new ArrayList<>();
        allStrats.add(s1);
        allStrats.add(s1);

        when(data.findAll()).thenReturn(allStrats);
        when(data.findStrategiesByOnoffIs(true)).thenReturn(strats);
        assertEquals(strats, strategyImpl.getActive());
    }

    @Test
    public void canGetActiveTickers() {
        Strategy s1 = new Strategy("Bollinger Bands", true, 200, "AAPL", 0.2, 0.1, (double) 2, 20, new User("Zoe"));
        Strategy s2 = new Strategy("Bollinger Bands", false, 200, "TSLA", 0.2, 0.1, (double) 2, 20, new User("Zoe"));
        List<String> l = new ArrayList<>();
        l.add(s1.getStock());

        when(data.findStockByOnoffIsTrue()).thenReturn(l);
        assertEquals(l, strategyImpl.getTickers());
    }
}
