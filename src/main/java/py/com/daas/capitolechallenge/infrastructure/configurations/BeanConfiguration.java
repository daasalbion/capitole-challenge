package py.com.daas.capitolechallenge.infrastructure.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import py.com.daas.capitolechallenge.CapitoleChallengeApplication;
import py.com.daas.capitolechallenge.domain.repositories.PriceRepository;
import py.com.daas.capitolechallenge.domain.services.PriceService;
import py.com.daas.capitolechallenge.domain.services.PriceServiceImpl;

@Configuration
@ComponentScan(basePackageClasses = CapitoleChallengeApplication.class)
public class BeanConfiguration {

    @Bean
    PriceService priceService(final PriceRepository orderRepository,
            @Value("${date.format.pattern}") String dateFormatPattern) {
        return new PriceServiceImpl(orderRepository, dateFormatPattern);
    }

}
