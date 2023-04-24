package org.training360.javasv3advprojectNagyGabor.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.PositiveOrZero;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateIncomeCommand {

    @PositiveOrZero(message = "Amount can not be negative!")
    @Schema(description = "The new amount of the income", example = "35_000")
    private int amount;
}
