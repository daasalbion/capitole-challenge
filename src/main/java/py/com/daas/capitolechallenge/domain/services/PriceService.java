package py.com.daas.capitolechallenge.domain.services;

import py.com.daas.capitolechallenge.domain.dtos.PriceDTO;

public interface PriceService {

    PriceDTO getPvp(Integer brandId, Integer productId, String date);
}
