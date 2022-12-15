package schoolrecords;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClassRecords {

    private String className;

    private Random random;

    private List <Student> students = new ArrayList<>();

    private static final String EX = "Name must not be empty!";

    public ClassRecords(String className, Random random) {
        if (className.isEmpty() || className == null) {
            throw new IllegalArgumentException(EX);
        }
        this.className = className;
        this.random = random;
    }

    public String getClassName() {
        return className;
    }

    public boolean addStudent(String name) {
        if (name.isEmpty() || name == null) {
            throw new IllegalArgumentException(EX);
        }
        for (Student student : students) {
            if (student.getName().equals(name)) {
                return false;
            }
        }
        return students.add(new Student(name));
    }

    public boolean removeStudent(String name) {
        if (name.isEmpty() || name == null) {
            throw new IllegalArgumentException(EX);
        }
        for (Student student : students) {
            if (student.getName().equals(name)) {
                return students.remove(student);
            }
        }
        return false;
    }

    public double calculateClassAverageBySubject(String subjectName) {
        if (subjectName.isEmpty() || subjectName == null) {
            throw new IllegalArgumentException(EX);
        }
//        int count = 0;
//        double sum = 0;
//        for (Student student : students) {
//            if (student.calculateSubjectAverage(subjectName) != 0.0) {
//                sum += student.calculateSubjectAverage(subjectName);
//                count++;
//            }
//        }
//        return sum / count;

        return students.stream()
                .filter(student -> student.calculateSubjectAverage(subjectName) != 0)
                .mapToDouble(student -> student.calculateSubjectAverage(subjectName))
                .average()
                .orElseThrow(() -> new IllegalArgumentException("No students have average from this subject"));
    }

    public Student findStudentByName(String name) {
        if (name.isEmpty() || name == null) {
            throw new IllegalArgumentException(EX);
        }
//        if (students.isEmpty()) {
//            throw new IllegalArgumentException("No students to search!");
//        }
//        for (Student student : students) {
//            if (student.getName().equals(name)) {
//                return student;
//            }
//        }
//        throw new IllegalArgumentException("No student found with name: " + name);

        return students.stream()
                .filter(student -> student.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No student found with name: " + name));
    }

    public Student repetition() {
        if (students.isEmpty() || students == null) {
            throw new IllegalArgumentException("No students to select for repetition!");
        }
        Student [] studentsArray = students.toArray(new Student[students.size()]);
        return studentsArray[random.nextInt(0, studentsArray.length)];
    }

    public List<SubjectResult> listSubjectResults(String subjectName) {
        if (subjectName.isEmpty() || subjectName == null) {
            throw new IllegalArgumentException(EX);
        }
//        List <SubjectResult> subjectResults = new ArrayList<>();
//        for (Student student : students) {
//            if (student.calculateSubjectAverage(subjectName) != 0) {
//                subjectResults.add(new SubjectResult(student.getName(), student.calculateSubjectAverage(subjectName)));
//            }
//        }
//        return subjectResults;

        return students.stream()
                .filter(student -> student.calculateSubjectAverage(subjectName) != 0)
                .map(student -> new SubjectResult(student.getName(), student.calculateSubjectAverage(subjectName)))
                .toList();
    }

    public String listStudentNames() {
        List <String> studentsNames = new ArrayList<>();
        for (Student student : students) {
            studentsNames.add(student.getName());
        }
        return String.join(", ", studentsNames);
    }
}
