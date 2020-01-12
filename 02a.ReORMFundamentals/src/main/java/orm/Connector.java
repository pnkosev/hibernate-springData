package orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {
    private static Connection connection;

    public static void createConnection(String username, String password, String dbName) throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", username);
        props.setProperty("password", password);
        props.setProperty("useSSL", "false");
        props.setProperty("autoReconnect", "true");

        String timeZone = "?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName + timeZone, props);
    }

    public static Connection getConnection() {
        return connection;
    }
}
