package com.apollo.services;

import com.apollo.Constants;
import com.apollo.algos.BollingerBandsRunner;
import com.apollo.algos.IAlgoRunner;
import com.apollo.entities.Strategy;
import com.apollo.entities.Trade;
import com.apollo.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlgoDispatchService {

    private static final Logger log = LoggerFactory.getLogger(FeedPollService.class);

    private TickerHistoryService store;

    private ApplicationContext context;

    @Autowired
    public AlgoDispatchService(TickerHistoryService store, ApplicationContext context) {
        this.store = store;
        this.context = context;
    }

    @Scheduled(fixedRate = Constants.ALGO_INTERVAL)
    public void runDispatch() {
        //get active algos
        Strategy strat = new Strategy("ballingerbands", true, 100, "msft", 10.0, 10.0, 2.0, 10, new User("Ketan"));
        List<Strategy> strategies = new ArrayList<>();
        strategies.add(strat);
        // lambda for run
        strategies.parallelStream().forEach((Strategy s) -> {
            // Determine which strategy to run
            IAlgoRunner runner;
            if (s.getStrategyName() == Constants.ALGO_BOLLINGERBANDS) {
                runner = new BollingerBandsRunner(s, store);
            } else {
                log.warn("Unknown strategy type " + s.getStrategyName());
                return;
            }
            // Get strategy result
            Trade t = runner.run();
            if (t == null) {
                log.warn("Unable to strategy " + s.getStrategyName());
                return;
            }

            // add trade to db
            log.info("Make trade on " + (t.getBuy() ? "Buy": "Sell") + " side");
            //send trade

        });
    }
}
