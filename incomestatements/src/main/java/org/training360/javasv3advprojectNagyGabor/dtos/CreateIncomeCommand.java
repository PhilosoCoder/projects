package org.training360.javasv3advprojectNagyGabor.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.training360.javasv3advprojectNagyGabor.model.IncomeType;

import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateIncomeCommand {

    @NotNull(message = "Income name cannot be null!")
    @NotBlank(message = "Income name cannot be empty!")
    @Schema(description = "Name of the Income", example = "house rent")
    private String title;

    @PositiveOrZero(message = "Amount can not be negative!")
    @Schema(description = "The amount of the income", example = "35_000")
    private int amount;

    @Schema(description = "Type of the Income", example = "RENT")
    private IncomeType incomeType;

    @Schema(description = "Date of the Income", example = "2023-02-03")
    private LocalDate date;
}
