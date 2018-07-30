import com.apollo.entities.Strategy;
import com.apollo.entities.User;
import com.apollo.service.StrategyService;
import com.apollo.repos.StrategyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Nick Todd on 30/08/2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest // use an in memory database
@SpringBootTest(classes={com.apollo.AppConfig.class})
@TestPropertySource(locations = "classpath:application-test.properties") // this is only needed because swagger breaks tests
public class TestStrategyRepository {

    @Autowired
    private TestEntityManager manager;

    @Autowired // this is a mock which is injected because of the @DataJpaTest
    private StrategyRepository repo;

    @Autowired
    StrategyService stratService;

    @Test
    public void canRetrieveActive() {
        User user = new User("me");
        Strategy s = new Strategy("Bollinger Bands", true, 200, "AAPL", 0.2, 0.1, (double) 2, 20, user);
        //disc.setId(1);
        manager.persist(s);

        Collection<Strategy> ss = repo.findStrategiesByRunningIs(true);
        boolean result = true;
        for (Strategy current : ss) {
            if (current.isRunning() == false) {
                result = false;
                break;
            }
        }
        assertTrue(result);
    }

    @Test
    public void canRetrieveTickers() {
        User user = new User("me");
        Strategy s = new Strategy("Bollinger Bands", true, 200, "AAPL", 0.2, 0.1, (double) 2, 20, user);
        Strategy s1 = new Strategy("Bollinger Bands", true, 200, "TSLA", 0.2, 0.1, (double) 2, 20, user);
        //disc.setId(1);
        manager.persist(s);

        Collection<String> ss = repo.findStockByRunningIsTrue();
        List<String> tickers = new ArrayList<>();

        for (String current : ss) {
            tickers.add(current);
        }
        List<String> test = new ArrayList<>();
        test.add("AAPL"); test.add("TSLA");
        assertEquals(tickers, test);
    }
}
