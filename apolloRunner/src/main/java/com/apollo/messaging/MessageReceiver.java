package com.apollo.messaging;

import com.apollo.entities.Trade;
import com.apollo.objects.Order;
import com.apollo.objects.Quote;
import com.apollo.services.db.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

@Component
public class MessageReceiver {

    /**
     * Get a copy of the application context
     */
    @Autowired
    ConfigurableApplicationContext context;

    @Autowired
    TradeService tradeService;

    /**
     * When you receive a message, print it out, then shut down the application.
     * Finally, clean up any ActiveMQ server stuff.
     **/
    @JmsListener(destination = "OrderBroker_Reply", containerFactory = "myJmsContainerFactory")
    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        Order result = new Order(message);
        Trade t = tradeService.getTradeById(Integer.parseInt(result.getId()));
        t.setState(result.getState().toLowerCase());
        tradeService.updateTrade(t);
    }
 }
