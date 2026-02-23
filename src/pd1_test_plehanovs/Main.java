/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pd1_test_plehanovs;
import java.sql.Connection;

/**
 *
 * @author Maksims Pļehanovs
 */

public class Main {

    public static void main(String[] args) throws Exception {
        // 1) Testē Properties nolasīšanu
        DBConfig.printConfig();

        // 2) Testē DB savienojumu
        Connection conn = DBConnection.getConnection();
        if(conn != null)
            System.out.println("Programma strādā!");
        else
            System.out.println("Programma nestrādā!");
    }
}

