package com.apollo.services.market;

import com.apollo.services.db.StrategyService;
import com.apollo.services.db.TradeService;
import com.apollo.services.market.FeedPollService;
import com.apollo.services.market.TickerHistoryService;
import com.apollo.utilities.Constants;
import com.apollo.algos.BollingerBandsRunner;
import com.apollo.algos.IAlgoRunner;
import com.apollo.entities.Strategy;
import com.apollo.entities.Trade;
import com.apollo.utilities.TickerHistoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AlgoDispatchService {

    private static final Logger log = LoggerFactory.getLogger(FeedPollService.class);

    private TickerHistoryService store;

    private ApplicationContext context;

    private TradeService tradeService;

    private StrategyService strategyService;

    @Autowired
    public AlgoDispatchService(TickerHistoryService store, ApplicationContext context, TradeService tradeService, StrategyService strategyService) {
        this.store = store;
        this.context = context;
        this.tradeService = tradeService;
        this.strategyService = strategyService;
    }

    @Scheduled(fixedRate = Constants.ALGO_INTERVAL)
    public void runDispatch() {
        //get active algos
        Collection<Strategy> strategies = strategyService.getActive();
        // lambda for run
        strategies.parallelStream().forEach((Strategy s) -> {
            log.info("ID " + s.getId());
            // Determine which strategy to run
            IAlgoRunner runner;
            if (Constants.ALGO_BOLLINGERBANDS.equals(s.getStrategyName())) {
                runner = new BollingerBandsRunner(s, store);
            } else {
                log.warn("Unknown strategy type " + s.getStrategyName());
                return;
            }
            // Get strategy result
            try {
                Trade t = runner.run();
                if (t == null) {
                    log.info("Strategy " + s.getId() +  " made no trade");
                    return;
                }

                // add trade to db
                log.info("Make trade on " + (t.getBuy() ? "Buy" : "Sell") + " side");
                log.info(t.toString());
                tradeService.addNewTrade(t);

                //send trade
            } catch (TickerHistoryException e) {
                log.error(e.getMessage());
            }
        });
    }
}
