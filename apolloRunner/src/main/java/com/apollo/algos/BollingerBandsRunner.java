package com.apollo.algos;

import com.apollo.Constants;
import com.apollo.entities.Strategy;
import com.apollo.entities.Trade;
import com.apollo.objects.Quote;
import com.apollo.services.FeedPollService;
import com.apollo.services.TickerHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

public class BollingerBandsRunner implements IAlgoRunner {


    private static final Logger log = LoggerFactory.getLogger(FeedPollService.class);

    private Strategy strategy;

    private TickerHistoryService store;

    public BollingerBandsRunner(Strategy strategy, TickerHistoryService store) {
        this.strategy = strategy;
        this.store = store;
    }

    public Trade run() {
        //get applicable trade history
        int maxBucket = strategy.getShortTime() / (1000 * Constants.POLLING_INTERVAL);
        List<Quote> history = store.getHistorySlice(strategy.getStock(), 0, maxBucket);

        if (history == null) {
            log.error("Error: Unable to run Strategy " + strategy.getStrategyName() + " due to insufficient market data");
            return null;
        }

        //calculate std div over period
        double sum = 0.0;

        for (Quote q : history) {
            sum += q.getPrice();
        }
        double movingAvg = sum / history.size();
        double standardDeviation = 0.0;

        for (Quote q : history) {
            standardDeviation += Math.pow(q.getPrice() - movingAvg, 2);
        }

        standardDeviation = Math.sqrt(standardDeviation / history.size());

        //make buy or sell
        double lastPrice = history.get(0).getPrice();
        if ( lastPrice>= movingAvg + standardDeviation * strategy.getStdDevs()) {
            // short
            return new Trade(false, lastPrice, strategy.getStartingVol(), strategy.getStock(), Timestamp.from(Instant.now()), "inprogress", strategy, strategy.getUser());
        } else if (lastPrice <= movingAvg - standardDeviation * strategy.getStdDevs()) {
            // long
            return new Trade(true, lastPrice, strategy.getStartingVol(), strategy.getStock(), Timestamp.from(Instant.now()), "inprogress", strategy, strategy.getUser());
        } else {
            return null;
        }

    }
}
