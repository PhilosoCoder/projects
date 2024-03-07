package hu.geralt.dtos.beer;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private UUID id;

    private String customerName;

    private String version;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
