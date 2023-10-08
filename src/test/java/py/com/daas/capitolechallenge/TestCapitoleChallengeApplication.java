package py.com.daas.capitolechallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestCapitoleChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.from(CapitoleChallengeApplication::main).with(TestCapitoleChallengeApplication.class).run(args);
    }

}
