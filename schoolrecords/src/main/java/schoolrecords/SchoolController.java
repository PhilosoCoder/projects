package schoolrecords;

import java.nio.file.Path;
import java.util.Random;
import java.util.Scanner;

public class SchoolController {

    private ClassRecords classRecords;

    private School school;

    private Scanner sc = new Scanner(System.in);

    private static final int MENU_EXIT = 9;

    String nameS = "student name:";
    String subjectS = "Subject name:";

    public void initClass() {
        classRecords.addStudent("Kovács Rita");
        classRecords.addStudent("Nagy Béla");
        classRecords.addStudent("Varga Márton");
        Student firstStudent = classRecords.findStudentByName("Kovács Rita");
        firstStudent.addGrading(new Mark(MarkType.A, school.findSubjectByName("földrajz"), school.findTutorByName("Dienes Irén")));
        firstStudent.addGrading(new Mark(MarkType.C, school.findSubjectByName("matematika"), school.findTutorByName("Szabó László")));
        firstStudent.addGrading(new Mark(MarkType.D, school.findSubjectByName("földrajz"), school.findTutorByName("Dienes Irén")));
        Student secondStudent = classRecords.findStudentByName("Nagy Béla");
        secondStudent.addGrading(new Mark(MarkType.A, school.findSubjectByName("biológia"), school.findTutorByName("Dienes Irén")));
        secondStudent.addGrading(new Mark(MarkType.C, school.findSubjectByName("matematika"), school.findTutorByName("Tóth Ilona")));
        secondStudent.addGrading(new Mark(MarkType.D, school.findSubjectByName("ének-zene"), school.findTutorByName("Németh Lili")));
        Student thirdStudent = classRecords.findStudentByName("Varga Márton");
        thirdStudent.addGrading(new Mark(MarkType.A, school.findSubjectByName("fizika"), school.findTutorByName("Kiss József")));
        thirdStudent.addGrading(new Mark(MarkType.C, school.findSubjectByName("kémia"), school.findTutorByName("Kiss József")));
        thirdStudent.addGrading(new Mark(MarkType.D, school.findSubjectByName("földrajz"), school.findTutorByName("Tóth Ilona")));
    }

    private void runMenu() {
        int menuNumber = 0;
        while (menuNumber != MENU_EXIT) {
            printMenu();
            System.out.println("\n");
            System.out.println("Please give the number of the menu!");
            try {
                menuNumber = Integer.parseInt(sc.nextLine());
                executeMenu(menuNumber);
        } catch (NumberFormatException nfe) {
                System.out.println("Not a number!");
            }
    }


    }

    private void printMenu() {
        System.out.println();
        System.out.println(
                "1. Diákok nevének listázása\n" +
                "2. Diák név alapján keresése\n" +
                "3. Diák létrehozása\n" +
                "4. Diák név alapján törlése\n" +
                "5. Diák feleltetése\n" +
                "6. Tantárgyi osztályátlag kiszámolása\n" +
                "7. Diákok átlagának listázása egy tantárgyból\n" +
                "8. Egy diák egy tantárgyhoz tartozó átlagának kiszámolása\n" +
                "9. Kilépés"
        );
    }

    private void executeMenu(int choosenNumber) {
        switch (choosenNumber) {
            case 1:
                System.out.println("Students:");
                System.out.println(classRecords.listStudentNames());
                return;
            case 2:
                System.out.println(nameS);
                String nameToFind = sc.nextLine();
                System.out.println(classRecords.findStudentByName(nameToFind));
                return;
            case 3:
                System.out.println(nameS);
                String nameToAdd = sc.nextLine();
                classRecords.addStudent(nameToAdd);
                return;
            case 4:
                System.out.println(nameS);
                String nameToRemove = sc.nextLine();
                classRecords.removeStudent(nameToRemove);
                return;
            case 5:
                studentRepetition();
                return;
            case 6:
                System.out.println(subjectS);
                String nameOfSubject = sc.nextLine();
                System.out.println(classRecords.calculateClassAverageBySubject(nameOfSubject));
                return;
            case 7:
                System.out.println(subjectS);
                String nameOfSubject2 = sc.nextLine();
                System.out.println(classRecords.listSubjectResults(nameOfSubject2));
                return;
            case 8:
                System.out.println(nameS);
                System.out.println(subjectS);
                String nameOfStudent = sc.nextLine();
                String nameOfSubject3 = sc.nextLine();
                System.out.println(classRecords.findStudentByName(nameOfStudent).calculateSubjectAverage(nameOfSubject3));
                return;
            case 9:
                System.out.println("Good bye!");
                return;
            default:
                System.out.println("This menu point is not exist!");
        }
    }

    private void studentRepetition() {
        Student student = classRecords.repetition();
        System.out.println("The lucky winner is: " + student.getName());
        System.out.println("Give me the mark!");
        int mark = Integer.parseInt(sc.nextLine());
        System.out.println(subjectS);
        String subjectName = sc.nextLine();
        System.out.println("Name of tutor:");
        String tutorName = sc.nextLine();

        Mark actualMark = new Mark(findMarkTypeByValue(mark), school.findSubjectByName(subjectName), school.findTutorByName(tutorName));
        student.addGrading(actualMark);
    }

    private MarkType findMarkTypeByValue(int value) {
        for (MarkType markType : MarkType.values()) {
            if (markType.getValue() == value) {
                return markType;
            }
        }
        throw new IllegalArgumentException("Cannot find mark woth this value");
    }

    public static void main(String[] args) {
        SchoolController schoolController = new SchoolController();
        schoolController.school = new School(Path.of("src/test/resources/school.csv"));
        schoolController.classRecords = new ClassRecords("9.A", new Random());
        schoolController.initClass();
        schoolController.runMenu();
    }
}
