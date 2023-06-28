package airplanes.mappers;

import org.mapstruct.Mapper;
import airplanes.dtos.AirplaneDto;
import airplanes.model.Airplane;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AirplaneMapper {

    AirplaneDto toDto(Airplane airplane);

    List<AirplaneDto> toDto(List<Airplane> airplanes);
}
