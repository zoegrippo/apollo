import com.apollo.entities.Strategy;
import com.apollo.entities.Trade;
import com.apollo.entities.User;
import com.apollo.service.TradeService;
import com.apollo.repos.TradeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created by Nick Todd on 30/08/2017.
 */
@RunWith(SpringRunner.class)
@DataJpaTest // use an in memory database
@SpringBootTest(classes={com.apollo.AppConfig.class})
@TestPropertySource(locations = "classpath:application-test.properties") // this is only needed because swagger breaks tests
public class TestTradeRepository {

    @Autowired
    private TestEntityManager manager;

    @Autowired // this is a mock which is injected because of the @DataJpaTest
    private TradeRepository repo;

    @Autowired
    TradeService service;

    @Test
    public void canGetTradeById() {
        User user = new User("me");
        Trade t = new Trade(true, 99.99, 100, "AAPL", new Timestamp(0), "Completed", new Strategy(), user);
        t.setId(2);
        manager.persist(t);

        Trade tt = repo.findOne(2);
        assertEquals(java.util.Optional.ofNullable(tt.getId()), 2);
    }
}
