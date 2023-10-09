package py.com.daas.capitolechallenge.infrastructure.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import py.com.daas.capitolechallenge.infrastructure.entities.PriceEntity;

@Repository
public interface SpringDataH2PriceRepository extends JpaRepository<PriceEntity, Integer> {
    @Query(value = "SELECT * FROM PRICES where BRAND_ID=:brandId AND PRODUCT_ID=:productId AND :date >= START_DATE " +
            "AND :date <= END_DATE ORDER BY PRIORITY DESC LIMIT 1",
            nativeQuery = true)
    Optional<PriceEntity> getPvp(@Param("brandId") Integer brandId, @Param("productId") Integer productId,
            @Param("date") LocalDateTime date);

    /*
    select p1_0.id,p1_0.brand_id,p1_0.curr,p1_0.end_date,p1_0.price,p1_0.price_list,p1_0.priority,p1_0.product_id,p1_0.start_date from prices p1_0
    where p1_0.brand_id=? and p1_0.product_id=? and p1_0.start_date<=? and p1_0.end_date>=?
     */
    List<PriceEntity> getPriceEntitiesByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(Integer brandId,
            Integer productId, LocalDateTime startDate, LocalDateTime endDate);
}
