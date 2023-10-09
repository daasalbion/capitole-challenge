package py.com.daas.capitolechallenge.domain.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import py.com.daas.capitolechallenge.domain.dtos.PriceDTO;
import py.com.daas.capitolechallenge.domain.exceptions.DomainException;
import py.com.daas.capitolechallenge.domain.repositories.PriceRepository;

@Service
public class PriceServiceImpl implements PriceService {
    private final PriceRepository priceRepository;
    private final String dateFormatPattern;

    @Autowired
    public PriceServiceImpl(PriceRepository priceRepository,
            @Value("${date.format.pattern}") String dateFormatPattern) {
        this.priceRepository = priceRepository;
        this.dateFormatPattern = dateFormatPattern;
    }

    @Override
    public PriceDTO getPvp(Integer brandId, Integer productId, String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormatPattern);
        LocalDateTime ldtDate = LocalDateTime.parse(date, formatter);

        return priceRepository.getPvp(brandId, productId, ldtDate)
                .orElseThrow(() -> new DomainException(String.format("Pvp price does not exists for brandId = %d, " +
                        "productId = %d, date = %s", brandId, productId, date)));
    }

}
