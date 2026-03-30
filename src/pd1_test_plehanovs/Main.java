/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pd1_test_plehanovs;

import java.sql.Connection;

/**
 * Galvenā testēšanas klase. Pārbauda DB konfigurāciju un savienojumu ar datu
 * bāzi.
 *
 * @author Maksims Pļehanovs
 */
public class Main {

    /**
     * Galvenā metode — testē Properties nolasīšanu un DB savienojumu. Izvada
     * "Programma strādā!" ja savienojums veiksmīgs, citādi "Programma
     * nestrādā!".
     *
     * @param args komandrindas argumenti (netiek izmantoti)
     * @throws Exception ja rodas kļūda izpildes laikā
     */
    public static void main(String[] args) throws Exception {
        // 1) Testē Properties nolasīšanu
        DBConfig.printConfig();

        // 2) Testē DB savienojumu
        Connection conn = DBConnection.getConnection();
        if (conn != null) {
            System.out.println("Programma strādā!");
        } else {
            System.out.println("Programma nestrādā!");
        }
    }
}
