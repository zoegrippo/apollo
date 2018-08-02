package com.apollo.util;

import com.apollo.entities.Strategy;
import com.apollo.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TestValidator {
    @InjectMocks
    Validator validator;

    @Test
    public void canValidateStrategy_WhenValid() {
        Strategy s = new Strategy("Bollinger Bands", true, 200, "AAPL", 0.2, 0.1, (double) 2, 20, new User("Zoe"));
        assertEquals(true, validator.validateStrategy(s));
    }

    @Test
    public void canValidateStrategy_WhenStartingVolIsInvalid() {
        Strategy s1 = new Strategy("Bollinger Bands", true, 0, "AAPL", -0.2, 0.1, (double) 2, 20, new User("Zoe"));
        assertEquals(false, validator.validateStrategy(s1));

        Strategy s2 = new Strategy("Bollinger Bands", true, -100, "AAPL", 0.2, -0.1, (double) 2, 20, new User("Zoe"));
        assertEquals(false, validator.validateStrategy(s2));
    }

    @Test
    public void canValidateStrategy_WhenExitPercentIsInvalid() {
        Strategy s1 = new Strategy("Bollinger Bands", true, 200, "AAPL", -0.2, 0.1, (double) 2, 20, new User("Zoe"));
        assertEquals(false, validator.validateStrategy(s1));

        Strategy s2 = new Strategy("Bollinger Bands", true, 200, "AAPL", 0.2, -0.1, (double) 2, 20, new User("Zoe"));
        assertEquals(false, validator.validateStrategy(s2));
    }

    @Test
    public void canValidateStrategy_WhenTickerIsInvalid() {
        Strategy s = new Strategy("Bollinger Bands", true, 200, "AAPLL", 0.2, 0.1, (double) 2, 20, new User("Zoe"));
        assertEquals(false, validator.validateStrategy(s));
    }

    @Test
    public void canValidateStrategy_WhenStdDevsIsInvalid() {
        Strategy s = new Strategy("Bollinger Bands", true, 200, "AAPL", 0.2, 0.1, (double) -2, 20, new User("Zoe"));
        assertEquals(false, validator.validateStrategy(s));
    }

    @Test
    public void canValidateStrategy_WhenShortTimeIsInvalid() {
        Strategy s = new Strategy("Bollinger Bands", true, 200, "AAPL", 0.2, 0.1, (double) 2, -20, new User("Zoe"));
        assertEquals(false, validator.validateStrategy(s));
    }

    @Test
    public void canValidateId_WhenInvalid() {
        int id = -1;
        assertEquals(false, validator.validateId(id));

        int id1 = 0;
        assertEquals(false, validator.validateId(id1));
    }
}
