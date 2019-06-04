package ru.keepdoing.Repository;

import org.hibernate.Session;
import ru.keepdoing.Entity.Student;
import ru.keepdoing.HibernateUtil;

import java.util.List;

public class StudentRepository {

    public static void insert(Student student){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        session.persist(student);
        session.getTransaction().commit();
    }

    public static void update(Student student){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        session.saveOrUpdate(student);
        session.getTransaction().commit();
    }

    public static void delete(int id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        Student student = session.get(Student.class, id);
        session.delete(student);
        session.getTransaction().commit();
    }

    public static Student get(int id){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        Student student = session.get(Student.class, id);
        session.getTransaction().commit();
        return student;
    }

    public static List<Student> getAll(){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
        List students = session.createQuery("from Student").list();
        session.getTransaction().commit();
        return students;
    }

}
