package com.apollo.algos;

import com.apollo.entities.Trade;
import com.apollo.utilities.TickerHistoryException;

public interface IAlgoRunner {

    public Trade run() throws TickerHistoryException;
}
