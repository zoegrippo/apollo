package com.apollo.service;

import com.apollo.entities.Trade;

import java.util.Collection;

public interface TradeService {
    // only in the service level
    Trade getTradeById(int id);

    // at the /trade endpoint
    Collection<Trade> getAll();
    String createOrUpdate(Trade t);

    // at the /trade/{id} endpoint
    Collection<Trade> getTradeByStrategy(int id);
}
