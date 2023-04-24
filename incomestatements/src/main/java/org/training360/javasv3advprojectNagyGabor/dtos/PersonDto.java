package org.training360.javasv3advprojectNagyGabor.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonDto {

    @Schema(description = "Id of the income", example = "13")
    private Long id;

    @Schema(description = "name of the person", example = "John Doe")
    private String name;
}
