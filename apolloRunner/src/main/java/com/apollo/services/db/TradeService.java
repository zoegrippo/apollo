package com.apollo.services.db;

import com.apollo.entities.Trade;

import java.util.Collection;

public interface TradeService {
    // only in the service level
    Trade getTradeById(int id);

    // at the /trade endpoint
    Collection<Trade> getAll();
    String update(Trade t);
    Trade create(Trade t);

    // at the /trade/{id} endpoint
    Collection<Trade> getTradeByStrategy(int id);

    // at the /trade/{id}/{date} endpoint
    Collection<Trade> getTradeByStrategyAndTradeId(int sid, int tid);
}
