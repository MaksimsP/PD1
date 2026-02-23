/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pd1_test_plehanovs;
import java.io.InputStream;
import java.util.Properties;
/**
 *
 * @author Maksims Pļehanovs
 */

public class DBConfig {

    public static Properties load() {
        Properties prop = new Properties();

        try {
            InputStream input = DBConfig.class.getClassLoader()
                    .getResourceAsStream("config.properties");

            if(input == null) {
                System.out.println("config.properties nav atrasts!");
                return null;
            }

            prop.load(input);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return prop;
    }

    public static void printConfig() {
        Properties p = load();
        if(p != null) {
            System.out.println("---- DB PARAMETRI ----");
            System.out.println("URL: " + p.getProperty("db.url"));
            System.out.println("USER: " + p.getProperty("db.user"));
            System.out.println("PASSWORD: " + p.getProperty("db.password"));
            System.out.println("----------------------");
        }
    }
}

