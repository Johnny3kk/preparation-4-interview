package HomeWork5.Task8;

import HomeWork5.Student;

import java.util.List;
import java.util.Random;

public class MainTestDAO {

    private static List<Student> students;
    private static DAOCacao<Student, Long> daoCacao = new DAOCacao<>();

    private static void fillStudentsTable() {
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            daoCacao.save(new Student(-1L, "Student " + i, r.nextInt(6)));
        }
    }

    public static void main(String[] args) {

        daoCacao.openCurrentSession();
//        fillStudentsTable();
        students = daoCacao.findAll(Student.class);
        daoCacao.closeCurrentSession();
        System.out.println(students);

        daoCacao.openCurrentSessionWithTransaction();
        daoCacao.delete(daoCacao.findById(Student.class, 20L));
        daoCacao.closeCurrentSessionWithTransaction();
    }
}
