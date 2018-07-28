package com.apollo.services;


import com.apollo.Constants;
import com.apollo.objects.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.web.client.RestTemplate;

@Component
public class FeedPoll {

    private static final Logger log = LoggerFactory.getLogger(FeedPoll.class);

    @Scheduled(fixedRate = Constants.POLLING_INTERVAL)
    public void pollMarket() {

            RestTemplate restTemplate = new RestTemplate();
            String[] symbols ={"msft",  "apl"};
            String resp = restTemplate.getForObject("http://feed.conygre.com:8080/MockYahoo/quotes.csv?s="+ String.join(",",symbols)+"&f=s0l1v0", String.class);
            List<Quote> quotes = new ArrayList<>();
            String[] returns = resp.split("\n");
            for (String params : returns){
                quotes.add(new Quote(params.split(",")));
            }
            for (Quote quote : quotes) {
                log.info(quote.toString());
            }
        }
}

