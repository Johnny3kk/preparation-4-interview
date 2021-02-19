package HomeWork5;

import java.util.List;
import java.util.Random;

public class Main {

    private static List<Student> students;
    private static StudentRepository studentRepository;

    private static void fillStudentsTable() {
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            studentRepository.save(new Student(-1L, "Student " + i, r.nextInt(6)));
        }
    }

    private static void deleteStudents() {
        students = studentRepository.findAll();
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (i%2==0) {
                studentRepository.delete(student);
            }
        }
    }

    private static void updateStudents() {
        students = studentRepository.findAll();
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            if (i%3==0) {
                student.setName("Incognito");
                studentRepository.update(student);
            }
        }
    }

    public static void main(String[] args) {

        studentRepository = new StudentRepository();

        fillStudentsTable();
        deleteStudents();
        updateStudents();
        Student student1 = studentRepository.findById(10355L).orElse(new Student(-1L, "Unknown", 0));
        System.out.println(student1.getName());
        Student student2 = students.get(3);
        System.out.println(student2.getName());
    }
}
