package org.training360.javasv3advprojectNagyGabor.dtos;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.training360.javasv3advprojectNagyGabor.model.ExpenseType;

import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateExpenseCommand {

    @NotNull(message = "Expense name cannot be null!")
    @NotBlank(message = "Expense name cannot be empty!")
    @Schema(description = "Name of the Expense", example = "house rent")
    private String title;

    @PositiveOrZero(message = "Amount can not be negative!")
    @Schema(description = "The amount of the expense", example = "35_000")
    private int amount;

    @Schema(description = "Type of the expense", example = "RENT")
    private ExpenseType expenseType;

    @Schema(description = "Date of the expense", example = "2023-02-03")
    private LocalDate date;
}
