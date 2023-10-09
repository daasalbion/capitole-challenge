package py.com.daas.capitolechallenge.domain.dtos;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

public record PriceDTO(
        @Schema(description = "Id of the productId.",
                example = "35455")
        Integer productId,

        @Schema(description = "Id of the brandId.",
                example = "1")
        Integer brandId,

        @Schema(description = "Id of the priceList.",
                example = "2")
        Integer priceList,

        @Schema(description = "Start date of the priceList.",
                example = "2020-06-14-15.00.00")
        LocalDateTime startDate,

        @Schema(description = "End date of the priceList.",
                example = "2020-12-31-23.59.59")
        LocalDateTime endDate,

        @Schema(description = "Final sale price",
                example = "25.45")
        Double price,

        @Schema(description = "Currency ISO code",
                example = "EUR")
        String currency
) {}
