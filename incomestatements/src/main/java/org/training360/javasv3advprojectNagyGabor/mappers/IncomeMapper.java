package org.training360.javasv3advprojectNagyGabor.mappers;

import org.mapstruct.Mapper;
import org.training360.javasv3advprojectNagyGabor.dtos.IncomeDto;
import org.training360.javasv3advprojectNagyGabor.model.Income;

import java.util.List;

@Mapper (componentModel = "spring")
public interface IncomeMapper {

    List<IncomeDto> toDtos(List<Income> incomes);

    IncomeDto toDto(Income income);
}
