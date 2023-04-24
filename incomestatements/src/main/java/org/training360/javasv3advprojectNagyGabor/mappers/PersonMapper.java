package org.training360.javasv3advprojectNagyGabor.mappers;

import org.mapstruct.Mapper;
import org.training360.javasv3advprojectNagyGabor.dtos.PersonDto;
import org.training360.javasv3advprojectNagyGabor.model.Person;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonDto toDto(Person person);
}
