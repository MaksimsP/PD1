/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pd1_test_plehanovs;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.InputStream;

/**
 *
 * @author Maksims Pļehanovs
 */
public class DBConnection {

    private static Connection connection;

    public static Connection getConnection() throws Exception {

        if (connection == null || connection.isClosed()) {

            // 1. Ielādē properties failu
            Properties props = new Properties();
            InputStream input = DBConnection.class
                    .getClassLoader()
                    .getResourceAsStream("config.properties");

            if (input == null) {
                throw new RuntimeException("config.properties fails nav atrasts");
            }
            props.load(input);

            // 2. Nolasa parametrus
            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");

            // 3. Izveido savienojumu
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Savienojums ar datu bāzi veiksmīgi izveidots!");
        }
        return connection;
    }
}
