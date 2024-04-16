package hu.geralt.entities.student;

import java.util.List;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("student")
@Getter
@Setter
public class Student {

    @Id
    private String id;

    private String name;

    private String mail;

    private Department department;

    private List<Subject> subjects;

}
