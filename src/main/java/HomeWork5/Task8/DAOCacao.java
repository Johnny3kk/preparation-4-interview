package HomeWork5.Task8;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;
import java.util.List;

public class DAOCacao<E, T extends Serializable> {

    private Session currentSession;
    private Transaction currentTransaction;

    public static SessionFactory getSessionFactory() {
        return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public Session openCurrentSessionWithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSessionWithTransaction(){
        currentTransaction.commit();
        currentSession.close();
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public void save(E entity) {
        getCurrentSession().save(entity);
    }

    public void update(E entity) {
        getCurrentSession().update(entity);
    }

    public E findById(Class<E> entity, T id) {
        return getCurrentSession().get(entity, id);
    }

    public List<E> findAll(Class<E> entity){
        return (List<E>)getCurrentSession().createQuery("from " + entity.getSimpleName()).list();
    }

    public void delete(E entity) {
        getCurrentSession().delete(entity);
    }

}
