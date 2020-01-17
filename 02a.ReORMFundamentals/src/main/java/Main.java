import entities.User;
import orm.Connector;
import orm.models.EntityManager;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException, SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Connector.createConnection("root", "root", "test");

        EntityManager<User> userManager = new EntityManager<>(Connector.getConnection());

        User user = new User("MM", "123", 123, Date.valueOf("1988-02-17"));

//        user.setId(4);
//        userManager.persist(user);

//        User first = userManager.findFirst(User.class, "id = 1");
//        System.out.println(first.getUsername() + " is user since " + first.getRegistrationDate());
//
//        List<User> users = new ArrayList<>();
//
//        userManager.find(User.class).forEach(users::add);
//
//        users.forEach(u -> System.out.println(String.format("%d. %s - %d, user since %s",
//                u.getId(),
//                u.getUsername(),
//                u.getAge(),
//                u.getRegistrationDate())));

        userManager.find(User.class, "id = 4");
    }
}
