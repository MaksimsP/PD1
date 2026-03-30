/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pd1_test_plehanovs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.FileInputStream;

/**
 * Klase nodrošina savienojumu ar Derby datu bāzi. Konfigurācija tiek nolasīta
 * no config.properties faila.
 *
 * @author Maksims Pļehanovs
 */
public class DBConnection {

    /**
     * Izveido un atgriež savienojumu ar datu bāzi. Nolasa parametrus (url,
     * lietotājs, parole) no config.properties.
     *
     * @return Connection objekts vai null, ja savienojums neizdevās
     */
    public static Connection getConnection() {
        Connection conn = null;

        try {
            Properties props = new Properties();
            props.load(DBConnection.class.getClassLoader().getResourceAsStream("pd1_test_plehanovs/config.properties"));

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");

            conn = DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }
}
