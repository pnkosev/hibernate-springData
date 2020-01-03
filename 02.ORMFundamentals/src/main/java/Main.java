import entities.User;
import orm.Connector;
import orm.EntityManager;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        Connector.createConnect("root", "root", "test");

        Connection connection = Connector.getConnection();

        EntityManager<User> userManager = new EntityManager<>(connection);

        User user = new User("Nikolay2", "1234", 31, Date.valueOf("1988-02-17"));

//         userManager.persist(user);
//        user.setId(1);
//        userManager.persist(user);

        User found = userManager.findFirst(User.class, "id = 2");

        System.out.println(found);
    }
}
