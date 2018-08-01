package com.apollo.services.db;

import com.apollo.entities.Trade;

public interface TradeService {
    Trade getTradeById(int id);

    void addNewTrade(Trade t);
}
