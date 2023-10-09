package py.com.daas.capitolechallenge.application.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import py.com.daas.capitolechallenge.domain.dtos.PriceDTO;

@Tag(name = "pvp")
public interface PriceApi {

    @Operation(
            summary = "Find Pvp for a product using the brandId, productId and date",
            description = "Find Pvp for a product using the brandId, productId and date",
            tags = {"pvp"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = PriceDTO.class))),
            @ApiResponse(responseCode = "404", description = "pvp not found")})
    @GetMapping(value = "price/pvp/{brandId}/{productId}/{date}")
    PriceDTO getPvp(
            @Parameter(
                    description = "Id of the brand.",
                    example = "1",
                    required = true)
            @PathVariable(value = "brandId") Integer brandId,
            @Parameter(
                    description = "Id of the product.",
                    example = "35455",
                    required = true)
            @PathVariable(value = "productId") Integer productId,
            @Parameter(
                    description = "Date of the request. Format: yyyy-MM-dd-HH.mm.ss",
                    example = "2020-06-14-10.00.00",
                    required = true)
            @PathVariable(value = "date") String date);
}
