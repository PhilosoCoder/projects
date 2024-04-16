package hu.geralt.services.student;

import java.util.List;

import hu.geralt.dtos.student.StudentDto;
import hu.geralt.entities.student.Student;
import hu.geralt.mappers.student.StudentMapper;
import hu.geralt.repositories.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    @Override
    @Transactional
    public Student createStudent(StudentDto studentDto) {
        Student student = studentMapper.studentDtoToStudent(studentDto);
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(String studentId) {
        return studentRepository.findById(studentId).orElseThrow();
    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    @Transactional
    public Student updateStudent(String studentId, StudentDto studentDto) {
        var student = studentRepository.findById(studentId).orElseThrow();
        studentMapper.updateDtoToEntity(student, studentDto);
        return studentRepository.save(student);
    }

    @Override
    public String deleteStudent(String studentId) {
        studentRepository.deleteById(studentId);
        return "student has been deleted";
    }

}
