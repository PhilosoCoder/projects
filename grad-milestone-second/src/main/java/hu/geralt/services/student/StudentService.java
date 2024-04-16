package hu.geralt.services.student;

import java.util.List;

import hu.geralt.dtos.student.StudentDto;
import hu.geralt.entities.student.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentService {

    Student createStudent(StudentDto studentDto);

    Student getStudentById(String studentId);

    List<Student> getAllStudent();

    Student updateStudent(String studentId, StudentDto studentDto);

    String deleteStudent(String studentId);

}
