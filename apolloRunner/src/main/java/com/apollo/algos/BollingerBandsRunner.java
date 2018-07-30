package com.apollo.algos;

import com.apollo.Constants;
import com.apollo.objects.Quote;
import com.apollo.services.FeedPollService;
import com.apollo.services.TickerHistoryService;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.List;

public class BollingerBandsRunner implements IAlgoRunner {


    private static final Logger log = LoggerFactory.getLogger(FeedPollService.class);

    private Strategy strategy;

    private TickerHistoryService store;

    public BollingerBandsRunner(Strategy strategy, TickerHistoryService store) {
        this.strategy = strategy;
        this.store = store;
    }

    public Trade run(){
        //get applicable trade history
        int maxBucket = strategy.shortTime /(1000* Constants.POLLING_INTERVAL);
        List<Quote> history = store.getHistorySlice(strategy.stock, 0, maxBucket);

        if (history == null) {
            log.error("Error: Unable to run Strategy " + strategy.name + " due to insufficient market data");
            return;
        }

        //calculate std div over period
        double sum = 0.0;

        for(Quote q : history) {
            sum += q.getPrice();
        }
        double movingAvg = sum/history.size();
        double standardDeviation = 0.0;

        for(Quote q : history) {
            standardDeviation += Math.pow(q.getPrice() - movingAvg, 2);
        }

        standardDeviation = Math.sqrt(standardDeviation/history.size());

        //make buy or sell

        if ( history.get(0) >= movingAvg + standardDeviation * strategy.stdDevs) {
            // short
            return new Trade();
        } else if (history.get(0) <= movingAvg - standardDeviation * strategy.stdDevs) {
            // long
            return new Trade();
        } else {
            return null;
        }

    }
}
