package py.com.daas.capitolechallenge.domain.services;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static py.com.daas.capitolechallenge.factories.PriceTestFactory.SEARCHED_BRAND_ID;
import static py.com.daas.capitolechallenge.factories.PriceTestFactory.SEARCHED_PRODUCT_ID;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import py.com.daas.capitolechallenge.domain.dtos.PriceDTO;
import py.com.daas.capitolechallenge.domain.exceptions.DomainException;
import py.com.daas.capitolechallenge.domain.repositories.PriceRepository;
import py.com.daas.capitolechallenge.factories.PriceTestFactory;

class PriceServiceImplTests {
    private final PriceRepository priceRepository = mock(PriceRepository.class);
    private final String dateFormatPattern = "yyyy-MM-dd-HH.mm.ss";
    private final PriceService priceService = new PriceServiceImpl(priceRepository, dateFormatPattern);
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

    @Test
    void testPriceDoesNotExist() {
        String requestedDate = "2020-06-14-10.00.00";
        LocalDateTime ldtSearchedDate = PriceTestFactory.string2LocalDatetime(requestedDate, formatter);

        when(priceRepository.getPvp(SEARCHED_BRAND_ID, SEARCHED_PRODUCT_ID, ldtSearchedDate))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> priceService.getPvp(SEARCHED_BRAND_ID, SEARCHED_PRODUCT_ID, requestedDate))
                .isInstanceOf(DomainException.class);
    }

    private void doTest(String requestedDate, Integer targetPriceList) {
        LocalDateTime ldtSearchedDate = PriceTestFactory.string2LocalDatetime(requestedDate, formatter);
        PriceDTO expectedPriceDTO = PriceTestFactory.getPriceDTO(targetPriceList);

        when(priceRepository.getPvp(SEARCHED_BRAND_ID, SEARCHED_PRODUCT_ID, ldtSearchedDate))
                .thenReturn(Optional.of(expectedPriceDTO));

        PriceDTO price = priceService.getPvp(SEARCHED_BRAND_ID, SEARCHED_PRODUCT_ID, requestedDate);

        Assertions.assertNotNull(price);

        Assertions.assertEquals(expectedPriceDTO.productId(), price.productId());
        Assertions.assertEquals(expectedPriceDTO.brandId(), price.brandId());
        Assertions.assertEquals(expectedPriceDTO.priceList(), price.priceList());
        Assertions.assertEquals(expectedPriceDTO.price(), price.price());
        Assertions.assertEquals(expectedPriceDTO.startDate(), price.startDate());
        Assertions.assertEquals(expectedPriceDTO.endDate(), price.endDate());
        Assertions.assertEquals(expectedPriceDTO.currency(), price.currency());
    }

}
