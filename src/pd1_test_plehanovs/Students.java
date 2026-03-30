/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pd1_test_plehanovs;

/**
 * Students, kurš aizpilda testus un saglabā savus rezultātus.
 */
public class Students extends User {

    private int questionsCount = 0;
    private int rightAnswers = 0;

    /**
     * Studenta konstruktors.
     *
     * @param name     studenta vārds
     * @param login    pieteikšanās vārds
     * @param password parole
     */
    public Students(String name, String login, String password) {
        super(name, login, password);
    }

    Students() {
        super("", "", ""); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    /**
     * Apstrādā studenta atbildi uz jautājumu.
     * Palielina jautājumu skaitītāju un, ja atbilde ir pareiza, palielina
     * pareizo atbilžu skaitītāju.
     *
     * @param question jautājuma objekts
     * @param answer   studenta ievadītā atbilde
     */
    public void getAnswers(Jautajumi question, String answer) {
        questionsCount++;
        if (question.isCorrect(answer)) {
            rightAnswers++;
        }
    }

    /**
     * Uzsāk testa kārtošanu — notīra iepriekšējos rezultātus.
     *
     * @param test testa objekts
     */
    public void beginTest(Test test) {
        clear();
    }

    /**
     * Notīra studenta pašreizējos rezultātus.
     * Atiestata jautājumu skaitu un pareizo atbilžu skaitu uz nulli.
     */
    public void clear() {
        questionsCount = 0;
        rightAnswers = 0;
    }

    /**
     * Atgriež pareizo atbilžu skaitu.
     *
     * @return pareizo atbilžu skaits
     */
    public int getRightAnswers() {
        return rightAnswers;
    }

    /**
     * Atgriež kopējo atbildēto jautājumu skaitu.
     *
     * @return jautājumu skaits
     */
    public int getQuestionsCount() {
        return questionsCount;
    }

    /**
     * Aprēķina rezultātu procentos.
     * Ja nav atbildēts neviens jautājums, atgriež 0.
     *
     * @return rezultāts procentos (0–100)
     */
    public int getPercent() {
        if (questionsCount == 0) return 0;
        return (int) Math.round((double) rightAnswers / questionsCount * 100);
    }

    /**
     * Pārvērš procentuālo rezultātu atzīmē pēc latvijas 10-baļļu skalas.
     *
     * @return atzīme kā vesels skaitlis (1–10)
     */
    public int getGrade() {
        int percent = getPercent();
        if (percent >= 95) return 10;
        if (percent >= 85) return 9;
        if (percent >= 75) return 8;
        if (percent >= 65) return 7;
        if (percent >= 55) return 6;
        if (percent >= 45) return 5;
        if (percent >= 35) return 4;
        if (percent >= 25) return 3;
        if (percent >= 10) return 2;
        return 1;
    }
}
