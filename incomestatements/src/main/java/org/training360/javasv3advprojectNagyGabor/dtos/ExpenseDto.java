package org.training360.javasv3advprojectNagyGabor.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.training360.javasv3advprojectNagyGabor.model.ExpenseType;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExpenseDto {

    @Schema(description = "Id of the expense", example = "12")
    private Long id;

    @Schema(description = "Name of the expense", example = "pineapple pizza")
    private String title;

    @Schema(description = "The amount of expense", example = "20_000")
    private int amount;

    @Schema(description = "Type of the expense", example = "RENT")
    private ExpenseType expenseType;

    @Schema(description = "Date of the expense", example = "2023-02-03")
    private LocalDate date;
}
