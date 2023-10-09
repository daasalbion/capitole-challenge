package py.com.daas.capitolechallenge.application.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import py.com.daas.capitolechallenge.domain.dtos.PriceDTO;
import py.com.daas.capitolechallenge.domain.services.PriceService;

@RestController
@RequestMapping("prices")
public class PriceController implements PriceApi {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PriceService priceService;

    @Autowired
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping(value = "pvp/{brandId}/{productId}/{date}")
    public PriceDTO getPvp(
            @PathVariable(value = "brandId") Integer brandId,
            @PathVariable(value = "productId") Integer productId,
            @PathVariable(value = "date") String date) {
        logger.info("getPvp method invoked");
        logger.info("parameters received => \"brandId\" = {}, \"productId\" = {}, \"date\" = {}", brandId, productId,
                date);
        return priceService.getPvp(brandId, productId, date);
    }

}
