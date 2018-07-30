package com.apollo.services;

import com.apollo.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class AlgoDispatchService {

    private static final Logger log = LoggerFactory.getLogger(FeedPollService.class);

    private TickerHistoryService store;

    @Autowired
    public AlgoDispatchService(TickerHistoryService store) {
        this.store = store;
    }

    @Scheduled(fixedRate = Constants.ALGO_INTERVAL)
    public void runDispatch(){
        log.info(store.getTickerSymbols().toString());
        store.getHistorySlice("apl",0,1);

    }
}
