package py.com.daas.capitolechallenge.factories;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import py.com.daas.capitolechallenge.domain.dtos.PriceDTO;

public class PriceTestFactory {

    private static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN);

    public static final Integer SEARCHED_BRAND_ID = 1;
    public static final Integer SEARCHED_PRODUCT_ID = 35455;
    public static final String CURRENCY_VALUE = "EUR";

    public static final String START_DATE_PRICELIST1 = "2020-06-14T00:00:00";
    public static final String END_DATE_PRICELIST1 = "2020-12-31T23:59:59";
    public static final Double PRICE_PRICELIST1 = 35.5;

    public static final String START_DATE_PRICELIST2 = "2020-06-14T15:00:00";
    public static final String END_DATE_PRICELIST2 = "2020-06-14T18:30:00";
    public static final Double PRICE_PRICELIST2 = 25.45;

    public static final String START_DATE_PRICELIST3 = "2020-06-15T00:00:00";
    public static final String END_DATE_PRICELIST3 = "2020-06-15T11:00:00";
    public static final Double PRICE_PRICELIST3 = 30.5;

    public static final String START_DATE_PRICELIST4 = "2020-06-15T16:00:00";
    public static final String END_DATE_PRICELIST4 = "2020-12-31T23:59:59";
    public static final Double PRICE_PRICELIST4 = 38.95;

    public static final String JSONKEY_PRICE_LIST = "priceList";
    public static final String JSONKEY_BRAND_ID = "brandId";
    public static final String JSONKEY_PRODUCT_ID = "productId";
    public static final String JSONKEY_START_DATE = "startDate";
    public static final String JSONKEY_END_DATE = "endDate";
    public static final String JSONKEY_PRICE = "price";
    public static final String JSONKEY_CURRENCY = "currency";

    public static PriceDTO getPriceDTO(Integer targetPriceList) {
        return switch (targetPriceList) {
            case 1 -> new PriceDTO(SEARCHED_PRODUCT_ID,
                    SEARCHED_BRAND_ID, targetPriceList, string2LocalDatetime(START_DATE_PRICELIST1),
                    string2LocalDatetime(END_DATE_PRICELIST1), PRICE_PRICELIST1, CURRENCY_VALUE);
            case 2 -> new PriceDTO(SEARCHED_PRODUCT_ID,
                    SEARCHED_BRAND_ID, targetPriceList, string2LocalDatetime(START_DATE_PRICELIST2),
                    string2LocalDatetime(END_DATE_PRICELIST2), PRICE_PRICELIST2, CURRENCY_VALUE);
            case 3 -> new PriceDTO(SEARCHED_PRODUCT_ID,
                    SEARCHED_BRAND_ID, targetPriceList, string2LocalDatetime(START_DATE_PRICELIST3),
                    string2LocalDatetime(END_DATE_PRICELIST3), PRICE_PRICELIST3, CURRENCY_VALUE);
            case 4 -> new PriceDTO(SEARCHED_PRODUCT_ID,
                    SEARCHED_BRAND_ID, targetPriceList, string2LocalDatetime(START_DATE_PRICELIST4),
                    string2LocalDatetime(END_DATE_PRICELIST4), PRICE_PRICELIST4, CURRENCY_VALUE);
            default -> throw new IllegalArgumentException("targetPriceList is not present");
        };
    }

    public static LocalDateTime string2LocalDatetime(String dateStr, DateTimeFormatter formatter) {
        return LocalDateTime.parse(dateStr, formatter);
    }

    public static LocalDateTime string2LocalDatetime(String dateStr) {
        return string2LocalDatetime(dateStr, formatter);
    }

}
