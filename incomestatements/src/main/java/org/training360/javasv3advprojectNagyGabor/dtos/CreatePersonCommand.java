package org.training360.javasv3advprojectNagyGabor.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreatePersonCommand {

    @NotNull(message = "Name cannot be null!")
    @NotBlank(message = "Name cannot be empty!")
    @Schema(description = "Name of the Person", example = "John Doe")
    private String name;
}
