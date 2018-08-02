package com.apollo.services.market;


import com.apollo.services.db.StrategyService;
import com.apollo.utilities.Constants;
import com.apollo.objects.Quote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@Component
public class FeedPollService {

    private static final Logger log = LoggerFactory.getLogger(FeedPollService.class);

    private final TickerHistoryService store;

    private StrategyService strategyService;

    @Autowired
    public FeedPollService(TickerHistoryService store, StrategyService strategyService) {
        this.store = store;
        this.strategyService = strategyService;
    }

    @Scheduled(fixedRate = Constants.POLLING_INTERVAL)
    public void pollMarket() {

        RestTemplate restTemplate = new RestTemplate();
        // String[] symbols = {"msft"};
        Collection<String> symbols = strategyService.getTickers();
        if (symbols.size() == 0 ) {
            log.info("No active strategies to get symbols for");
            return;
        }
        String symbolString = String.join(",", symbols).toLowerCase();
        String resp = restTemplate.getForObject("http://feed.conygre.com:8080/MockYahoo/quotes.csv?s=" + symbolString + "&f=s0l1v0", String.class);
        /*
        Have to remove quotes around symbol name
        have to split on new line to get data for each portion of the request
         */
        String[] returns = resp.replace("\"", "").split("\n");
        for (String params : returns) {
            Quote q = new Quote(params.split(","));
            store.addTick(q.getSymbol(), q);
        }
    }
}

