/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pd1_test_plehanovs;

import java.sql.Connection;

/**
 * Klase DB savienojuma pārbaudei. Izmantojama testēšanas nolūkos.
 *
 * @author Maksims Pļehanovs
 */
public class TestDBConnection {

    /**
     * Galvenā metode — pārbauda savienojumu ar datu bāzi un izvada rezultātu
     * konsolē.
     *
     * @param args komandrindas argumenti (netiek izmantoti)
     */
    public static void main(String[] args) {

        try {
            Connection con = DBConnection.getConnection();

            if (con != null && !con.isClosed()) {
                System.out.println("TESTS IZDEVĀS – DB pieslēgums darbojas!");
            } else {
                System.out.println("TESTS NEIZDEVĀS – nav savienojuma.");
            }

        } catch (Exception e) {
            System.out.println("Kļūda pieslēdzoties DB:");
            e.printStackTrace();
        }
    }
}
