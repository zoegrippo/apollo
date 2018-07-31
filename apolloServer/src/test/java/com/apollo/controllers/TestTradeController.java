package com.apollo.controllers;

import com.apollo.entities.Strategy;
import com.apollo.entities.Trade;
import com.apollo.entities.User;
import com.apollo.repos.TradeRepository;
import com.apollo.service.TradeServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class TestTradeController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TradeServiceImpl service;

    @Test
    public void canGetTradeByStrategyId() {
//
//        Trade t = new Trade(true, 99.99, 100, "AAPL", time, "Completed", new Strategy(), new User("Zoe"));
//
//        List<Trade> allTrades = Arrays.asList(t);
//
//        given(service.getTradeByStrategy(2)).willReturn(allTrades);
//
//        mockMvc.perform(get("/api/compactdiscs")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].title", is("Abba Gold")));
//    }
}
