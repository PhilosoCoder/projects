package org.training360.javasv3advprojectNagyGabor.mappers;

import org.mapstruct.Mapper;
import org.training360.javasv3advprojectNagyGabor.dtos.ExpenseDto;
import org.training360.javasv3advprojectNagyGabor.model.Expense;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    List<ExpenseDto> toDtos(List<Expense> expenses);

    ExpenseDto toDto(Expense expense);
}
