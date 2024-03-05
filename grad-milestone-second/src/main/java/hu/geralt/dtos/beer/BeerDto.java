package hu.geralt.dtos.beer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import hu.geralt.entities.beer.BeerStyle;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BeerDto {

    private UUID id;

    private Integer version;

    private String beerName;

    private BeerStyle beerStyle;

    private String upc;

    private Integer quantityOnHand;

    private BigDecimal price;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
