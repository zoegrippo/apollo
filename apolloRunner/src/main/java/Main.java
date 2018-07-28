import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
@ComponentScan("com.apollo")
@EnableAutoConfiguration()
public class Main {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class);

    }
}
