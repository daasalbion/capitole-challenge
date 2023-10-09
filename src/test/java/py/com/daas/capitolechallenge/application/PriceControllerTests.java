package py.com.daas.capitolechallenge.application;

import static org.assertj.core.api.Assertions.assertThat;
import static py.com.daas.capitolechallenge.factories.PriceTestFactory.*;

import org.junit.jupiter.api.Test;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerTests {

    private static final String REST_ENDPOINT = "/prices/pvp/{brandId}/{productId}/{date}";

    @Autowired
    private PriceController priceController;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @Test
    void controllerNotNullOk() {
        assertThat(priceController).isNotNull();
    }

    @Test
    void test1Ok() throws Exception {
        Integer targetPriceList = 1;
        String requestedDate = "2020-06-14-10.00.00";

        mockMvc.perform(MockMvcRequestBuilders
                        .get(REST_ENDPOINT, SEARCHED_BRAND_ID, SEARCHED_PRODUCT_ID, requestedDate)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$." + JSONKEY_PRODUCT_ID).value(SEARCHED_PRODUCT_ID))
                .andExpect(MockMvcResultMatchers.jsonPath("$." + JSONKEY_BRAND_ID).value(SEARCHED_BRAND_ID))
                .andExpect(MockMvcResultMatchers.jsonPath("$." + JSONKEY_PRICE_LIST).value(targetPriceList))
                .andExpect(MockMvcResultMatchers.jsonPath("$." + JSONKEY_START_DATE).value(START_DATE_PRICELIST1))
                .andExpect(MockMvcResultMatchers.jsonPath("$." + JSONKEY_END_DATE).value(END_DATE_PRICELIST1))
                .andExpect(MockMvcResultMatchers.jsonPath("$." + JSONKEY_PRICE).value(PRICE_PRICELIST1))
                .andExpect(MockMvcResultMatchers.jsonPath("$." + JSONKEY_CURRENCY).value(CURRENCY_VALUE));
    }

    @Test
    void test2Ok() throws Exception {
        Integer targetPriceList = 2;
        String requestedDate = "2020-06-14-16.00.00";

        mockMvc.perform(MockMvcRequestBuilders
                        .get(REST_ENDPOINT, SEARCHED_BRAND_ID, SEARCHED_PRODUCT_ID, requestedDate)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$." + JSONKEY_PRODUCT_ID).value(SEARCHED_PRODUCT_ID))
                .andExpect(MockMvcResultMatchers.jsonPath("$." + JSONKEY_BRAND_ID).value(SEARCHED_BRAND_ID))
                .andExpect(MockMvcResultMatchers.jsonPath("$." + JSONKEY_PRICE_LIST).value(targetPriceList))
                .andExpect(MockMvcResultMatchers.jsonPath("$." + JSONKEY_START_DATE).value(START_DATE_PRICELIST2))
                .andExpect(MockMvcResultMatchers.jsonPath("$." + JSONKEY_END_DATE).value(END_DATE_PRICELIST2))
                .andExpect(MockMvcResultMatchers.jsonPath("$." + JSONKEY_PRICE).value(PRICE_PRICELIST2))
                .andExpect(MockMvcResultMatchers.jsonPath("$." + JSONKEY_CURRENCY).value(CURRENCY_VALUE));
    }

    @Test
    void test3Ok() throws Exception {
        Integer targetPriceList = 1;
        String requestedDate = "2020-06-14-10.00.00";

        mockMvc.perform(MockMvcRequestBuilders
                        .get(REST_ENDPOINT, SEARCHED_BRAND_ID, SEARCHED_PRODUCT_ID, requestedDate)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$." + JSONKEY_PRODUCT_ID).value(SEARCHED_PRODUCT_ID))
                .andExpect(MockMvcResultMatchers.jsonPath("$." + JSONKEY_BRAND_ID).value(SEARCHED_BRAND_ID))
                .andExpect(MockMvcResultMatchers.jsonPath("$." + JSONKEY_PRICE_LIST).value(targetPriceList))
                .andExpect(MockMvcResultMatchers.jsonPath("$." + JSONKEY_START_DATE).value(START_DATE_PRICELIST1))
                .andExpect(MockMvcResultMatchers.jsonPath("$." + JSONKEY_END_DATE).value(END_DATE_PRICELIST1))
                .andExpect(MockMvcResultMatchers.jsonPath("$." + JSONKEY_PRICE).value(PRICE_PRICELIST1))
                .andExpect(MockMvcResultMatchers.jsonPath("$." + JSONKEY_CURRENCY).value(CURRENCY_VALUE));
    }

    @Test
    void test4Ok() throws Exception {
        Integer targetPriceList = 3;
        String requestedDate = "2020-06-15-10.00.00";

        mockMvc.perform(MockMvcRequestBuilders
                        .get(REST_ENDPOINT, SEARCHED_BRAND_ID, SEARCHED_PRODUCT_ID, requestedDate)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$." + JSONKEY_PRODUCT_ID).value(SEARCHED_PRODUCT_ID))
                .andExpect(MockMvcResultMatchers.jsonPath("$." + JSONKEY_BRAND_ID).value(SEARCHED_BRAND_ID))
                .andExpect(MockMvcResultMatchers.jsonPath("$." + JSONKEY_PRICE_LIST).value(targetPriceList))
                .andExpect(MockMvcResultMatchers.jsonPath("$." + JSONKEY_START_DATE).value(START_DATE_PRICELIST3))
                .andExpect(MockMvcResultMatchers.jsonPath("$." + JSONKEY_END_DATE).value(END_DATE_PRICELIST3))
                .andExpect(MockMvcResultMatchers.jsonPath("$." + JSONKEY_PRICE).value(PRICE_PRICELIST3))
                .andExpect(MockMvcResultMatchers.jsonPath("$." + JSONKEY_CURRENCY).value(CURRENCY_VALUE));
    }

    @Test
    void test5Ok() throws Exception {
        Integer targetPriceList = 4;
        String requestedDate = "2020-06-16-21.00.00";

        mockMvc.perform(MockMvcRequestBuilders
                        .get(REST_ENDPOINT, SEARCHED_BRAND_ID, SEARCHED_PRODUCT_ID, requestedDate)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$." + JSONKEY_PRODUCT_ID).value(SEARCHED_PRODUCT_ID))
                .andExpect(MockMvcResultMatchers.jsonPath("$." + JSONKEY_BRAND_ID).value(SEARCHED_BRAND_ID))
                .andExpect(MockMvcResultMatchers.jsonPath("$." + JSONKEY_PRICE_LIST).value(targetPriceList))
                .andExpect(MockMvcResultMatchers.jsonPath("$." + JSONKEY_START_DATE).value(START_DATE_PRICELIST4))
                .andExpect(MockMvcResultMatchers.jsonPath("$." + JSONKEY_END_DATE).value(END_DATE_PRICELIST4))
                .andExpect(MockMvcResultMatchers.jsonPath("$." + JSONKEY_PRICE).value(PRICE_PRICELIST4))
                .andExpect(MockMvcResultMatchers.jsonPath("$." + JSONKEY_CURRENCY).value(CURRENCY_VALUE));
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
