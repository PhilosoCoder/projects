package hu.geralt.dtos.student;

import java.util.List;

import hu.geralt.entities.student.Department;
import hu.geralt.entities.student.Subject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private String name;

    private String mail;

    private Department department;

    private List<Subject> subjects;

}
