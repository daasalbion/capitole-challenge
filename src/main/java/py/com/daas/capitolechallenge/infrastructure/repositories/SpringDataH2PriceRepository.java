package py.com.daas.capitolechallenge.infrastructure.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import py.com.daas.capitolechallenge.infrastructure.entities.PriceEntity;

@Repository
public interface SpringDataH2PriceRepository extends JpaRepository<PriceEntity, Integer> {
    /*
    select p1_0.id,p1_0.brand_id,p1_0.curr,p1_0.end_date,p1_0.price,p1_0.price_list,p1_0.priority,p1_0.product_id,p1_0.start_date
    from prices p1_0 where p1_0.brand_id=? and p1_0.product_id=? and p1_0.start_date<=? and p1_0.end_date>=? order by p1_0.priority desc
     */
    List<PriceEntity> getPriceEntitiesByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(Integer brandId,
            Integer productId, LocalDateTime startDate, LocalDateTime endDate);
}
