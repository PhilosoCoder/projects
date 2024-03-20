package hu.geralt.dtos.beer;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank
    @NotNull
    private String customerName;

    private String version;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
