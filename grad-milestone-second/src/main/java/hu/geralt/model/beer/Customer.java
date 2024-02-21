package hu.geralt.model.beer;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private UUID id;

    private String customerName;

    private String version;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

}
