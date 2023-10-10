package py.com.daas.capitolechallenge.infrastructure.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "prices")
@NoArgsConstructor
public class PriceEntity {
    @Id
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private Integer id;
    private Integer brandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer priceList;
    private Integer productId;
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private Integer priority;
    private Double price;
    private String curr;

}
