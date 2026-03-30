/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pd1_test_plehanovs;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
 
/**
 * Sistēmas galvenā klase, kas pārvalda jautājumu sarakstu un testa norisi.
 * Jautājumi tiek ielādēti no datubāzes pēc testa ID.
 */
public class Test {
 
    private List<User> users = new ArrayList<>();
    private List<Jautajumi> questions = new ArrayList<>();
    private boolean allowRetake = true;
    private boolean isActive = false;
    private int testId;
    private String testName;
 
    /**
     * Pārbauda lietotāja pieteikšanās datus datubāzē.
     *
     * @param lietotajvards ievadītais lietotājvārds
     * @param parole ievadītā parole
     * @return true, ja lietotājs ar šādiem datiem eksistē datubāzē
     */
    public boolean login(String lietotajvards, String parole) {
        if (lietotajvards == null || lietotajvards.trim().isEmpty() ||
            parole == null || parole.trim().isEmpty()) {
            return false;
        }
 
        String sql = "SELECT * FROM APP.LIETOTAJI WHERE LIETOTAJVARDS = ? AND PAROLE = ?";
 
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
 
            ps.setString(1, lietotajvards.trim());
            ps.setString(2, parole.trim());
 
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // true ja lietotājs atrasts
            }
 
        } catch (Exception ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, "Kļūda ielogojoties", ex);
        }
        return false;
    }
 
    /**
     * Saglabā studenta testa rezultātu datubāzē.
     *
     * @param lietotajvards studenta lietotājvārds
     * @param pareizasAtbildes pareizo atbilžu skaits
     * @param kopskaits kopējais jautājumu skaits
     */
    public void saveResult(String lietotajvards, int pareizasAtbildes, int kopskaits) {
        if (kopskaits == 0) return;
        int procenti = (int) Math.round((double) pareizasAtbildes / kopskaits * 100);
 
        String sql = "INSERT INTO APP.REZULTATI (LIETOTAJVARDS, PAREIZAS, KOPSKAITS, PROCENTI) VALUES (?, ?, ?, ?)";
 
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
 
            ps.setString(1, lietotajvards);
            ps.setInt(2, pareizasAtbildes);
            ps.setInt(3, kopskaits);
            ps.setInt(4, procenti);
            ps.executeUpdate();
 
        } catch (Exception ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, "Kļūda saglabājot rezultātu", ex);
        }
    }

 
    /**
     * Ielādē testa nosaukumu no datubāzes pēc ID.
     *
     * @param testId testa identifikators
     * @return testa nosaukums vai "Nav nosaukuma" kļūdas gadījumā
     */
    public String loadTestNameFromDB(int testId) {
        String sql = "SELECT NAME FROM APP.TESTS WHERE ID = ?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
 
            ps.setInt(1, testId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    testName = rs.getString("NAME");
                    return testName;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, "Kļūda ielādējot testa nosaukumu", ex);
        }
        return "Nav nosaukuma";
    }
 
    /**
     * Atgriež visu pieejamo testu sarakstu no datubāzes. Katrs elements formātā
     * "ID: Nosaukums".
     *
     * @return testu saraksts
     */
    public static List<String> getAllTestsFromDB() {
        List<String> list = new ArrayList<>();
        String sql = "SELECT ID, NAME FROM APP.TESTS";
 
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
 
            while (rs.next()) {
                list.add(rs.getInt("ID") + ": " + rs.getString("NAME"));
            }
 
        } catch (Exception ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, "Kļūda ielādējot testus", ex);
        }
        return list;
    }
 
    /**
     * Pievieno jaunu jautājumu testam atmiņā.
     *
     * @param text jautājuma teksts
     * @param answer pareizā atbilde
     * @param answerOptions atbilžu varianti
     */
    public void addQuestion(String text, String answer, List<String> answerOptions) {
        questions.add(new Jautajumi(text, answer, answerOptions));
    }
 
    /**
     * Pievieno jaunu studentu sistēmai atmiņā.
     *
     * @param name vārds
     * @param login pieteikšanās vārds
     * @param password parole
     * @param repetition paroles atkārtojums
     * @throws IllegalArgumentException ja paroles nesakrīt vai lietotājvārds
     * aizņemts
     */
    public void addUser(String name, String login, String password, String repetition) {
        if (!password.equals(repetition)) {
            throw new IllegalArgumentException("Paroles nesakrīt");
        }
        for (User u : users) {
            if (u.getLogin().equals(login)) {
                throw new IllegalArgumentException("Lietotājs ar šādu lietotājvārdu jau eksistē");
            }
        }
        users.add(new Students(name, login, password));
    }
 
    /**
     * Atgriež jautājumu pēc indeksa.
     *
     * @param index jautājuma indekss
     * @return jautājuma objekts
     */
    public Jautajumi getQuestion(int index) {
        return questions.get(index);
    }
 
    /**
     * Atgriež jautājumu kopskaitu.
     *
     * @return jautājumu skaits
     */
    public int getQuestionCount() {
        return questions.size();
    }
 
    /**
     * Meklē lietotāju atmiņā pēc login un paroles.
     *
     * @param login pieteikšanās vārds
     * @param password parole
     * @return lietotāja objekts vai {@code null}
     */
    public User findUser(String login, String password) {
        for (User u : users) {
            if (u.getLogin().equals(login) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }
 
    /**
     * Aprēķina vidējo rezultātu procentos visiem studentiem.
     *
     * @return vidējais procents vai 0.0
     */
    public float showAverageScore() {
        int total = 0, count = 0;
        for (User u : users) {
            if (u instanceof Students) {
                Students s = (Students) u;
                if (s.getQuestionsCount() > 0) {
                    total += s.getPercent();
                    count++;
                }
            }
        }
        return count == 0 ? 0.0f : (float) total / count;
    }
 
    /**
     * Atgriež rezultātu sarakstu visiem studentiem.
     *
     * @return saraksts "login: X% (atzīme N)"
     */
    public List<String> getResults() {
        List<String> results = new ArrayList<>();
        for (User u : users) {
            if (u instanceof Students) {
                Students s = (Students) u;
                results.add(s.getLogin() + ": " + s.getPercent() + "% (atzīme " + s.getGrade() + ")");
            }
        }
        return results;
    }
 
    /**
     * @return true, ja atkārtošana atļauta
     */
    public boolean isAllowRetake() {
        return allowRetake;
    }
 
    /**
     * @param allowRetake false lai aizliegtu atkārtošanu
     */
    public void setAllowRetake(boolean allowRetake) {
        this.allowRetake = allowRetake;
    }
 
    /**
     * @return true, ja tests ir aktīvs
     */
    public boolean isActive() {
        return isActive;
    }
 
    /**
     * @param active true lai aktivizētu testu
     */
    public void setActive(boolean active) {
        this.isActive = active;
    }
 
    /**
     * Saglabā datus (rezervēts).
     */
    private void save() {
    }
 
    /**
     * Ielādē datus (rezervēts).
     */
    private void load() {
    }
    public void loadQuestionsFromDB(int testId) {
    questions.clear();

    String sql = "SELECT JAUTAJUMS, ATBILDE, OPTION1, OPTION2, OPTION3 FROM APP.JAUTAJUMI";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            String text   = rs.getString("JAUTAJUMS");
            String answer = rs.getString("ATBILDE");
            java.util.List<String> options = new java.util.ArrayList<>();
            options.add(rs.getString("OPTION1"));
            options.add(rs.getString("OPTION2"));
            options.add(rs.getString("OPTION3"));
            questions.add(new Jautajumi(text, answer, options));
        }

    } catch (Exception ex) {
        java.util.logging.Logger.getLogger(Test.class.getName())
            .log(java.util.logging.Level.SEVERE, "Kļūda ielādējot jautājumus", ex);
    }
}
 
}