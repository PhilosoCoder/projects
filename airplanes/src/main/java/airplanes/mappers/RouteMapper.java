package airplanes.mappers;

import org.mapstruct.Mapper;
import airplanes.dtos.RouteDto;
import airplanes.model.Route;

@Mapper(componentModel = "spring")
public interface RouteMapper {

    RouteDto toDto(Route route);
}
