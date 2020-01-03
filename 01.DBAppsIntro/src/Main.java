import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws SQLException {
        Properties props = new Properties();

        props.setProperty("user", "root");
        props.setProperty("password", "root");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/soft_uni?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC", props);

        PreparedStatement statement = connection.prepareStatement("SELECT  * FROM employees WHERE salary > ?");
        statement.setDouble(1, 50000);

        ResultSet results = statement.executeQuery();

        while (results.next()) {
            System.out.println(String.format("%s %s %s %s %s",
                    results.getString("employee_id"),
                    results.getString("first_name"),
                    results.getString("last_name"),
                    results.getString("job_title"),
                    results.getString("salary")));
        }
    }
}
