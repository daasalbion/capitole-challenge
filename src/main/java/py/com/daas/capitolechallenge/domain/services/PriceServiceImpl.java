package py.com.daas.capitolechallenge.domain.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import py.com.daas.capitolechallenge.domain.dtos.PriceDTO;
import py.com.daas.capitolechallenge.domain.exceptions.DomainException;
import py.com.daas.capitolechallenge.domain.repositories.PriceRepository;

public class PriceServiceImpl implements PriceService {
    private final PriceRepository priceRepository;
    private final DateTimeFormatter formatter;

    public PriceServiceImpl(PriceRepository priceRepository, String dateFormatPattern) {
        this.priceRepository = priceRepository;
        this.formatter = DateTimeFormatter.ofPattern(dateFormatPattern);
    }

    @Override
    public PriceDTO getPvp(Integer brandId, Integer productId, String date) {
        LocalDateTime ldtDate = LocalDateTime.parse(date, formatter);

        return priceRepository.getPvp(brandId, productId, ldtDate)
                .orElseThrow(() -> new DomainException(String.format("Pvp price does not exists for brandId = %d, " +
                        "productId = %d, date = %s", brandId, productId, date)));
    }

}
