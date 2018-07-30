package com.apollo.services;

import com.apollo.Constants;
import javafx.application.Application;
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
    public void runDispatch(){
        log.info(store.getTickerSymbols().toString());
        store.getHistorySlice("apl",0,1);

        // add trade to db

        //send trade
        MessageCreator messageCreator = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("ping!");
            }
        };
        JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
        log.info("Sending a new message.");
        jmsTemplate.send("dynamicQueues/OrderBroker", messageCreator);


    }
}
