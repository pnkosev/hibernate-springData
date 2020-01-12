import entities.User;
import orm.Connector;
import orm.models.EntityManager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException, SQLException {
        Connector.createConnection("root", "root", "test");

        EntityManager<User> userManager = new EntityManager<>(Connector.getConnection());

        User user = new User("Pesho", "123", 123, Date.valueOf("1988-02-17"));

        user.setId(2);
        userManager.persist(user);
//        user.setId(1);
//        userManager.persist(user);

//        User found = userManager.findFirst(User.class, "id = 2");
//
//        System.out.println(found);
    }
}
