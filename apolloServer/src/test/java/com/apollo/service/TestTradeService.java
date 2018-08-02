package com.apollo.service;

import com.apollo.entities.Strategy;
import com.apollo.entities.Trade;
import com.apollo.entities.User;
import com.apollo.repos.TradeRepository;
import com.apollo.util.Validator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestTradeService {

    @Mock
    TradeRepository data;

    @Mock
    Validator validator;

    @InjectMocks
    TradeServiceImpl tradeImpl;

    @Test
    public void canGetTradeById() {
        java.util.Date now = Calendar.getInstance().getTime();
        java.sql.Timestamp time = new java.sql.Timestamp(now.getTime());
        Trade t1 = new Trade(true, 99.99, 100, "AAPL", time, "Completed", new Strategy(), new User("Zoe"));
        t1.setId(2);
        Trade t2 = new Trade(true, 99.99, 100, "AAPL", time, "Completed", new Strategy(), new User("Zoe"));
        t2.setId(3);
        List<Trade> l = new ArrayList<>();
        l.add(t1);
        l.add(t2);

        when(validator.validateId(t1.getId())).thenReturn(true);
        when(data.getOne(t1.getId())).thenReturn(t1);

        assertEquals(t1, tradeImpl.getTradeById(t1.getId()));
    }
}
