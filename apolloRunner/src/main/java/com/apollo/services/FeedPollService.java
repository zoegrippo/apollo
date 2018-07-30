package com.apollo.services;


import com.apollo.Constants;
import com.apollo.objects.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.client.RestTemplate;

@Component
public class FeedPollService {

    private final TickerHistoryService store;

    private static final Logger log = LoggerFactory.getLogger(FeedPollService.class);

    @Autowired
    public FeedPollService(TickerHistoryService store) {
        this.store = store;
    }

    @Scheduled(fixedRate = Constants.POLLING_INTERVAL)
    public void pollMarket() {

            RestTemplate restTemplate = new RestTemplate();
            String[] symbols ={"msft",  "apl"};
            String resp = restTemplate.getForObject("http://feed.conygre.com:8080/MockYahoo/quotes.csv?s="+ String.join(",",symbols)+"&f=s0l1v0", String.class);
            List<Quote> quotes = new ArrayList<>();
            /*
            Have to remove quotes around symbol name
            have to split on new line to get data for each portion of the request
             */
            String[] returns = resp.replace("\"", "").split("\n");
            for (String params : returns){
                Quote q = new Quote(params.split(","));
                log.info("Adding tick for " + q.getSymbol());
                store.addTick(q.getSymbol(),q);
            }
        }
}

