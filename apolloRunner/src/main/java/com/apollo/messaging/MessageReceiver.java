package com.apollo.messaging;

import com.apollo.entities.Trade;
import com.apollo.objects.Order;
import com.apollo.objects.OrderReply;
import com.apollo.objects.Quote;
import com.apollo.services.db.TradeService;
import com.apollo.utilities.XMLConverter;
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
    final
    ConfigurableApplicationContext context;

    final
    TradeService tradeService;

    final
    XMLConverter<OrderReply> converter;

    @Autowired
    public MessageReceiver(ConfigurableApplicationContext context, TradeService tradeService, XMLConverter<OrderReply> converter) {
        this.context = context;
        this.tradeService = tradeService;
        this.converter = converter;
    }

    /**
     * When you receive a message, print it out, then shut down the application.
     * Finally, clean up any ActiveMQ server stuff.
     **/
    @JmsListener(destination = "OrderBroker_Reply", containerFactory = "myJmsContainerFactory")
    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        message = message.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "");
        OrderReply result = converter.jaxbXMLToObject(message, OrderReply.class);
        System.out.println("Recieved reply for trade id " + result.getId());
        Trade t = tradeService.getTradeById(Integer.parseInt(result.getId()));
        //System.out.println("RESULT: " + result.getResult());
        t.setState(result.getResult().toLowerCase());
        tradeService.updateTrade(t);
    }
 }
