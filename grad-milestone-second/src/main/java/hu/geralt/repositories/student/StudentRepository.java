package hu.geralt.repositories.student;

import hu.geralt.entities.student.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {
}
