import entities.Student;
import org.hibernate.Session;
import utils.HibernateUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
//        WITHOUT JPA
//        Session session = HibernateUtils.getSession();
//        session.beginTransaction();

//        NEW REGISTRY
//        Student student = new Student();
//        student.setName("MK");
//        student.setRegistrationDate(new Date());
//
//        session.save(student);

//        GET ONE REGISTRY
//        Student student = session.get(Student.class, 1);
//        System.out.println(student);

//        GET MULTIPLE REGISTRIES USING HQL!!!
//        session.createQuery("FROM Student").list() // CAN ADD WHERE CLAUSE
//                .forEach(System.out::println);

//        GET MULTIPLE REGISTRIES USING CRITERIA BUILDER!!!
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery query = builder.createQuery(Student.class);
//
//        Root<Student> studentRoot = query.from(Student.class);
//
//        query.select(studentRoot).where(builder.like(studentRoot.get("name"), "M%"));
//
//        session.createQuery(query)
//                .getResultList()
//                .forEach(System.out::println);
//
//        session.getTransaction().commit();
//        session.close();


//        WITH JPA IMPLEMENTATION
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("school");
//        EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin();
//        Student student = new Student();
//        student.setName("MK");
//        student.setRegistrationDate(new Date());
//        em.persist(student);
//        em.getTransaction().commit();
    }

}
