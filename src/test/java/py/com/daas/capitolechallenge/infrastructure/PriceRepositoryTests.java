package py.com.daas.capitolechallenge.infrastructure;

import static org.junit.Assert.assertEquals;
import static py.com.daas.capitolechallenge.factories.PriceTestFactory.SEARCHED_BRAND_ID;
import static py.com.daas.capitolechallenge.factories.PriceTestFactory.SEARCHED_PRODUCT_ID;

import java.time.LocalDateTime;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import py.com.daas.capitolechallenge.domain.dtos.PriceDTO;
import py.com.daas.capitolechallenge.domain.repositories.PriceRepository;
import py.com.daas.capitolechallenge.factories.PriceTestFactory;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class PriceRepositoryTests {

    private final PriceRepository priceRepository;

    @Autowired
    PriceRepositoryTests(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @ParameterizedTest
    @CsvSource({
        "2020-06-14T10:00:00, 1",
        "2020-06-14T16:00:00, 2",
        "2020-06-14T21:00:00, 1",
        "2020-06-15T10:00:00, 3",
        "2020-06-16T21:00:00, 4",
    })
    void doTest(String searchedDate, Integer targetPriceList) {
        LocalDateTime ldtSearchedDate = PriceTestFactory.string2LocalDatetime(searchedDate);
        PriceDTO expectedPriceDTO = PriceTestFactory.getPriceDTO(targetPriceList);

        priceRepository.getPvp(SEARCHED_BRAND_ID, SEARCHED_PRODUCT_ID, ldtSearchedDate)
                .ifPresent(price -> {
                    assertEquals(expectedPriceDTO.productId(), price.productId());
                    assertEquals(expectedPriceDTO.brandId(), price.brandId());
                    assertEquals(expectedPriceDTO.priceList(), price.priceList());
                    assertEquals(expectedPriceDTO.price(), price.price());
                    assertEquals(expectedPriceDTO.startDate(), price.startDate());
                    assertEquals(expectedPriceDTO.endDate(), price.endDate());
                    assertEquals(expectedPriceDTO.currency(), price.currency());
                });
    }
}
