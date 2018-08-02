package com.apollo.services.market;

import com.apollo.utilities.Constants;
import com.apollo.objects.Quote;
import com.apollo.utilities.TickerHistoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

@Scope(value = "singleton")
@Component
public class TickerHistoryService {

    private static final Logger log = LoggerFactory.getLogger(FeedPollService.class);

    /*
    Array list stores ticks for the key which is the stock symbol
    The array list stores the most recent update as the head of the list
     */
    private Map<String, List<Quote>> history = new HashMap<>();

    public void addTick(String symbol, Quote quote) {
        if (!history.keySet().contains(symbol)) {
            history.put(symbol, new ArrayList<>());
        }
        history.get(symbol).add(0, quote);

        //trim list size to prevent runaway memeory usage
        if (history.get(symbol).size() >= Constants.MAX_MARKET_STORE) {
            history.get(symbol).remove(Constants.MAX_MARKET_STORE);
        }
    }

    public Set<String> getTickerSymbols() {
        return history.keySet();
    }

    public List<Quote> getHistorySlice(String symbol, int start, int end) throws TickerHistoryException {
        try {
            return history.get(symbol).subList(start, end);
        } catch (IndexOutOfBoundsException e) {
            throw new TickerHistoryException("Warning: Symbol " + symbol + " does not have enough history to run ");
        } catch (NullPointerException e) {
            throw new TickerHistoryException("Warning: Ticker Symbol " + symbol + " has uninitialized history array");
        }
    }

    public Quote getLatestPrice (String symbol) {
        return history.get(symbol).get(0);
    }

}
