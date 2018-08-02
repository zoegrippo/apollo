package com.apollo.services.db;

import com.apollo.entities.Trade;

import java.util.Collection;

public interface TradeService {
    Trade getTradeById(int id);

    void addNewTrade(Trade t);

    void updateTrade(Trade t);

    Collection<Trade> getTradeByStrategy(int id);

}
