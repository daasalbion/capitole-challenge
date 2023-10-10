package py.com.daas.capitolechallenge.application;

import static org.assertj.core.api.Assertions.assertThat;
import static py.com.daas.capitolechallenge.factories.PriceTestFactory.JSONKEY_BRAND_ID;
import static py.com.daas.capitolechallenge.factories.PriceTestFactory.JSONKEY_CURRENCY;
import static py.com.daas.capitolechallenge.factories.PriceTestFactory.JSONKEY_END_DATE;
import static py.com.daas.capitolechallenge.factories.PriceTestFactory.JSONKEY_PRICE;
import static py.com.daas.capitolechallenge.factories.PriceTestFactory.JSONKEY_PRICE_LIST;
import static py.com.daas.capitolechallenge.factories.PriceTestFactory.JSONKEY_PRODUCT_ID;
import static py.com.daas.capitolechallenge.factories.PriceTestFactory.JSONKEY_START_DATE;
import static py.com.daas.capitolechallenge.factories.PriceTestFactory.SEARCHED_BRAND_ID;
import static py.com.daas.capitolechallenge.factories.PriceTestFactory.SEARCHED_PRODUCT_ID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import py.com.daas.capitolechallenge.application.controllers.PriceController;
import py.com.daas.capitolechallenge.domain.dtos.PriceDTO;
import py.com.daas.capitolechallenge.factories.PriceTestFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerTests {

    private static final String REST_ENDPOINT = "/prices/pvp/{brandId}/{productId}/{date}";
    private final PriceController priceController;
    private final MockMvc mockMvc;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    PriceControllerTests(PriceController priceController, MockMvc mockMvc) {
        this.priceController = priceController;
        this.mockMvc = mockMvc;
    }

    @Test
    void controllerNotNullOk() {
        assertThat(priceController).isNotNull();
    }

    @ParameterizedTest
    @CsvSource({
            "2020-06-14-10.00.00, 1",
            "2020-06-14-16.00.00, 2",
            "2020-06-14-10.00.00, 1",
            "2020-06-15-10.00.00, 3",
            "2020-06-16-21.00.00, 4",
    })
    void doTest(String requestedDate, Integer targetPriceList) throws Exception {
        PriceDTO expectedPriceDTO = PriceTestFactory.getPriceDTO(targetPriceList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(REST_ENDPOINT, SEARCHED_BRAND_ID, SEARCHED_PRODUCT_ID, requestedDate)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$." + JSONKEY_PRODUCT_ID).value(expectedPriceDTO.productId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$." + JSONKEY_BRAND_ID).value(expectedPriceDTO.brandId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$." + JSONKEY_PRICE_LIST).value(expectedPriceDTO.priceList()))
                .andExpect(MockMvcResultMatchers.jsonPath("$." + JSONKEY_START_DATE).isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$." + JSONKEY_END_DATE).isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$." + JSONKEY_PRICE).value(expectedPriceDTO.price()))
                .andExpect(MockMvcResultMatchers.jsonPath("$." + JSONKEY_CURRENCY).value(expectedPriceDTO.currency()));
    }

    @Test
    void testDateStringFormatNotAccepted() throws Exception {
        String requestedDate = "2020-06-16-21.00-00";

        mockMvc.perform(MockMvcRequestBuilders
                        .get(REST_ENDPOINT, SEARCHED_BRAND_ID, SEARCHED_PRODUCT_ID, requestedDate)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void testPricePvpNotFound() throws Exception {
        String requestedDate = "2020-06-16-21.00.00";

        mockMvc.perform(MockMvcRequestBuilders
                        .get(REST_ENDPOINT, 3, SEARCHED_PRODUCT_ID, requestedDate)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
