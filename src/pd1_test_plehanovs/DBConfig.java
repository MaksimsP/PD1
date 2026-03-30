/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pd1_test_plehanovs;

import java.io.InputStream;
import java.util.Properties;

/**
 * Klase nodrošina datu bāzes konfigurācijas parametru nolasīšanu un izvadīšanu
 * no config.properties faila.
 *
 * @author Maksims Pļehanovs
 */
public class DBConfig {

    /**
     * Ielādē konfigurācijas parametrus no config.properties faila.
     *
     * @return Properties objekts ar DB parametriem, vai null ja fails nav
     * atrasts
     */
    public static Properties load() {
        Properties prop = new Properties();

        /**
         * Izvada datu bāzes konfigurācijas parametrus konsolē. Izmanto load()
         * metodi parametru iegūšanai.
         */
        try {
            InputStream input = DBConfig.class.getClassLoader()
                    .getResourceAsStream("config.properties");

            if (input == null) {
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
        if (p != null) {
            System.out.println("---- DB PARAMETRI ----");
            System.out.println("URL: " + p.getProperty("db.url"));
            System.out.println("USER: " + p.getProperty("db.user"));
            System.out.println("PASSWORD: " + p.getProperty("db.password"));
            System.out.println("----------------------");
        }
    }
}
