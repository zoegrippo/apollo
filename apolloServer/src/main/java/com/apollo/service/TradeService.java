package com.apollo.service;

import com.apollo.entities.Trade;

import java.util.Collection;

public interface TradeService {
    Collection<Trade> getTradeById(int id);
}
