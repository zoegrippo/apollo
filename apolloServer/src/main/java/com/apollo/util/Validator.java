package com.apollo.util;

import com.apollo.entities.Strategy;
import org.springframework.stereotype.Component;

@Component
public class Validator {
    public boolean validateStrategy(Strategy s) {
        return ((s.getStock().length() < 5) && (s.getStartingVol() > 0) && (s.isOnoff() == (true || false)) && (s.getExitLossPercent() > 0) &&
                (s.getExitProfitPercent() > 0) && (s.getShortTime() > 0) && (s.getStdDevs() > 0));
    }

    public boolean validateId(int id) {
        return (id > 0);
    }
}