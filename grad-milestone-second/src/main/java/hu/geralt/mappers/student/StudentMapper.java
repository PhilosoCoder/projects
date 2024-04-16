package hu.geralt.mappers.student;

import hu.geralt.dtos.student.StudentDto;
import hu.geralt.entities.student.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StudentMapper {

   Student studentDtoToStudent(StudentDto studentDto);

   @Mapping(target = "id", ignore = true)
   void updateDtoToEntity(@MappingTarget Student student, StudentDto studentDto);

}
