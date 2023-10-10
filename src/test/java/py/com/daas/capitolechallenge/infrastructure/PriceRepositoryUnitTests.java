package py.com.daas.capitolechallenge.infrastructure;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static py.com.daas.capitolechallenge.factories.PriceTestFactory.SEARCHED_BRAND_ID;
import static py.com.daas.capitolechallenge.factories.PriceTestFactory.SEARCHED_PRODUCT_ID;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import py.com.daas.capitolechallenge.domain.dtos.PriceDTO;
import py.com.daas.capitolechallenge.domain.repositories.PriceRepository;
import py.com.daas.capitolechallenge.factories.PriceTestFactory;
import py.com.daas.capitolechallenge.infrastructure.entities.PriceEntity;
import py.com.daas.capitolechallenge.infrastructure.repositories.SpringDataH2PriceRepository;
import py.com.daas.capitolechallenge.infrastructure.repositories.SpringDataH2PriceRepositoryImpl;

class PriceRepositoryUnitTests {
    private final SpringDataH2PriceRepository springDataH2PriceRepository = mock(SpringDataH2PriceRepository.class);
    private final String dateFormatPattern = "yyyy-MM-dd-HH.mm.ss";
    private final PriceRepository priceRepository = new SpringDataH2PriceRepositoryImpl(springDataH2PriceRepository);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormatPattern);

    @Test
    void test1Ok() {
        Integer targetPriceList = 1;
        String requestedDate = "2020-06-14-10.00.00";

        doTest(requestedDate, targetPriceList);
    }

    @Test
    void test2Ok() {
        Integer targetPriceList = 2;
        String requestedDate = "2020-06-14-16.00.00";

        doTest(requestedDate, targetPriceList);
    }

    @Test
    void test3Ok() {
        Integer targetPriceList = 1;
        String requestedDate = "2020-06-14-10.00.00";

        doTest(requestedDate, targetPriceList);
    }

    @Test
    void test4Ok() {
        Integer targetPriceList = 3;
        String requestedDate = "2020-06-15-10.00.00";

        doTest(requestedDate, targetPriceList);
    }

    @Test
    void test5Ok() {
        Integer targetPriceList = 4;
        String requestedDate = "2020-06-16-21.00.00";

        doTest(requestedDate, targetPriceList);
    }

    private void doTest(String requestedDate, Integer targetPriceList) {
        LocalDateTime ldtSearchedDate = PriceTestFactory.string2LocalDatetime(requestedDate, formatter);
        PriceDTO expectedPriceDTO = PriceTestFactory.getPriceDTO(targetPriceList);
        PriceEntity priceEntity = PriceTestFactory.getPriceEntity(targetPriceList);

        when(springDataH2PriceRepository.getPriceEntitiesByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(SEARCHED_BRAND_ID, SEARCHED_PRODUCT_ID, ldtSearchedDate, ldtSearchedDate))
                .thenReturn(Collections.singletonList(priceEntity));

        priceRepository.getPvp(SEARCHED_BRAND_ID, SEARCHED_PRODUCT_ID, ldtSearchedDate)
                .ifPresent(price -> {
                    Assertions.assertEquals(expectedPriceDTO.productId(), price.productId());
                    Assertions.assertEquals(expectedPriceDTO.brandId(), price.brandId());
                    Assertions.assertEquals(expectedPriceDTO.priceList(), price.priceList());
                    Assertions.assertEquals(expectedPriceDTO.price(), price.price());
                    Assertions.assertEquals(expectedPriceDTO.startDate(), price.startDate());
                    Assertions.assertEquals(expectedPriceDTO.endDate(), price.endDate());
                    Assertions.assertEquals(expectedPriceDTO.currency(), price.currency());
                });
    }

}
