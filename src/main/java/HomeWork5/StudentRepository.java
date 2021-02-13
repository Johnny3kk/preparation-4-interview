package HomeWork5;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class StudentRepository {

    SessionFactory sessionFactory;

    public StudentRepository() {
        sessionFactory = SessionFactoryGetter.getSessionFactory();
    }

    public Student save(Student student){
        Session session = sessionFactory.openSession();
        Long id = (Long) session.save(student);
        session.close();
        return new Student(id, student.getName(), student.getMark());
    }

    public List<Student> findAll(){
        Session session = sessionFactory.openSession();
        List<Student> students = (List<Student>)session.createQuery("from Student").list();
        session.close();
        return students;
    }

    public Optional<Student> findById(Long id){
        Session session = sessionFactory.openSession();
        Student student = session.find(Student.class, id);
        session.close();
        return Optional.ofNullable(student);
    }

    public void delete(Student student){
        Session session = sessionFactory.openSession();
        Transaction t = session.getTransaction();
        t.begin();
        session.remove(student);
        session.flush();
        t.commit();
        session.close();
    }

    public void update(Student student){
        Session session = sessionFactory.openSession();
        Transaction t = session.getTransaction();
        t.begin();
        session.update(student);
        session.flush();
        t.commit();
        session.close();
    }
}
