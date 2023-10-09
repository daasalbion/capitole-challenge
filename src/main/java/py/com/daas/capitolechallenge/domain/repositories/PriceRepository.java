package py.com.daas.capitolechallenge.domain.repositories;

import java.time.LocalDateTime;
import java.util.Optional;

import py.com.daas.capitolechallenge.domain.dtos.PriceDTO;

public interface PriceRepository {

    Optional<PriceDTO> getPvp(Integer brandId, Integer productId, LocalDateTime date);
}
