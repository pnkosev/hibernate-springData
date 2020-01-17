import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate_exercises");

        EntityManager em = emf.createEntityManager();

        executeTransaction(em,
                () -> print("yoyo"));

        em.close();
    }

    private static void executeTransaction(EntityManager em, Runnable runnable) {
        em.getTransaction().begin();
        runnable.run();
        em.getTransaction().commit();
    }

    private static void print(String msg) {
        System.out.println(msg);
    }
}
