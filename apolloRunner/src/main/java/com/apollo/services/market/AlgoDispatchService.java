package com.apollo.services.market;

import com.apollo.objects.Order;
import com.apollo.objects.Quote;
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
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

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
            // Determine which strategy to run
            IAlgoRunner runner;
            if (Constants.ALGO_BOLLINGERBANDS.equals(s.getStrategyName())) {
                runner = new BollingerBandsRunner(s, store);
            } else {
                log.warn("Unknown strategy type " + s.getStrategyName());
                return;
            }
            // check if exit condition is met
            Collection<Trade> trades = tradeService.getTradeByStrategy(s.getId());
            List<Trade> tradeList = new ArrayList<>(trades);
            if (tradeList.size() > 2) {
                tradeList.sort(Comparator.comparing(Trade::getTradeDate));
                Trade firsTrade = tradeList.get(0);
                double sum = 0.0;
                for (Trade t : tradeList) {
                    if (t.getState().equals("filled")) sum += t.getSize() * t.getPrice() * (t.getBuy() ? -1 : 1);
                }
                double plp = (sum / (firsTrade.getSize() * firsTrade.getPrice()));
                log.info("PL % for strat "+ s.getId() + ": "+ plp);
;                if (plp > s.getExitProfitPercent() || plp < -1 * s.getExitLossPercent()) {
                    log.info("Strategy " + s.getId() + " hit exit condition");
                    s.setOnoff(false);
                    return;
                }
            }
            // Get strategy result
            try {
                Trade t = runner.run();
                if (t == null) {
                    log.info("Strategy " + s.getId() +  " made no trade");
                    return;
                }

                // add trade to db
                log.info("Strat "+s.getId()+" make trade on " + (t.getBuy() ? "Buy" : "Sell") + " side, " + s.getStock() + " at " +t.getPrice());
                log.info(t.toString());
                tradeService.create(t);
                //send trade
                // Send a message
                MessageCreator messageCreator = session -> session.createTextMessage(new Order(t).toString());
                JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
                jmsTemplate.send("OrderBroker", messageCreator);
            } catch (TickerHistoryException e) {
                log.error(e.getMessage());
            }
        });
    }
}
