package py.com.daas.capitolechallenge.infrastructure.repositories;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import py.com.daas.capitolechallenge.domain.dtos.PriceDTO;
import py.com.daas.capitolechallenge.domain.repositories.PriceRepository;
import py.com.daas.capitolechallenge.infrastructure.entities.PriceEntity;

@Component
public class SpringDataH2PriceRepositoryImpl implements PriceRepository {

    private final SpringDataH2PriceRepository priceRepository;

    @Autowired
    public SpringDataH2PriceRepositoryImpl(@Lazy final SpringDataH2PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Optional<PriceDTO> getPvp(Integer brandId, Integer productId, LocalDateTime date) {
        var prices = priceRepository.getPriceEntitiesByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(brandId,
                productId, date, date);
        return prices.stream().findFirst().map(this::toPriceDTO);
    }

    private PriceDTO toPriceDTO(PriceEntity price) {
        return new PriceDTO(price.getProductId(),
                price.getBrandId(), price.getPriceList(), price.getStartDate(),
                price.getEndDate(), price.getPrice(), price.getCurr());
    }
}
