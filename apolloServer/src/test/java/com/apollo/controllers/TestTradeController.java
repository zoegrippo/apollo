package com.apollo.controllers;

import com.apollo.entities.Strategy;
import com.apollo.entities.Trade;
import com.apollo.entities.User;
import com.apollo.service.TradeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TradeController.class)
@ContextConfiguration(classes = {com.apollo.AppConfig.class})
@TestPropertySource(locations = "classpath:application-test.properties")
@DataJpaTest
public class TestTradeController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TradeService service;

    @Test
    public void canGetTradeByStrategyId() throws Exception {
//        User zoe = new User("Zoe");
//        Strategy s1 = new Strategy("Bollinger Bands", true, 200, "AAPL", 0.2, 0.1, (double) 2, 20, zoe);
//        s1.setId(2);
//        java.util.Date now = Calendar.getInstance().getTime();
//        java.sql.Timestamp time = new java.sql.Timestamp(now.getTime());
//        Trade t = new Trade(true, 99.99, 100, "AAPL", time, "Completed", s1, zoe);
//
//        List<Trade> allTrades = Arrays.asList(t);
//
//        given(service.getTradeByStrategy(2)).willReturn(allTrades);
//
//        mockMvc.perform(get("/trade/2")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect((ResultMatcher) jsonPath("$[0].stock", is("AAPL")));
    }
}
