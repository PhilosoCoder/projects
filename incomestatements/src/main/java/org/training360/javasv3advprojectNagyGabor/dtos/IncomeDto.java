package org.training360.javasv3advprojectNagyGabor.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.training360.javasv3advprojectNagyGabor.model.IncomeType;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IncomeDto {

    @Schema(description = "Id of the income", example = "13")
    private Long id;

    @Schema(description = "name of the income", example = "lottery win")
    private String title;

    @Schema(description = "amount of the income", example = "30_000")
    private int amount;

    @Schema(description = "type of the income", example = "family inheritance")
    private IncomeType incomeType;

    @Schema(description = "date of the income", example = "2022-09-11")
    private LocalDate date;
}
