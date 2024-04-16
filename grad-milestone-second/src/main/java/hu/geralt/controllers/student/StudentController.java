package hu.geralt.controllers.student;

import java.util.List;

import hu.geralt.dtos.student.StudentDto;
import hu.geralt.entities.student.Student;
import hu.geralt.services.student.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public Student createStudent(@RequestBody StudentDto studentDto) {
        return studentService.createStudent(studentDto);
    }

    @GetMapping("/{studentId}")
    public Student getStudentById(@PathVariable String studentId) {
        return studentService.getStudentById(studentId);
    }

    @GetMapping
    public List<Student> getAllStudent() {
        return studentService.getAllStudent();
    }

    @PutMapping("/{studentId}")
    public Student updateStudent(
            @PathVariable String studentId,
            @RequestBody StudentDto studentDto) {
        return studentService.updateStudent(studentId, studentDto);
    }

    @DeleteMapping("/{studentId}")
    public String deleteStudent(@PathVariable String studentId) {
        return studentService.deleteStudent(studentId);
    }

}
