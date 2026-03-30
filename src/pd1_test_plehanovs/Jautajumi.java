/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pd1_test_plehanovs;

import java.util.List;

/**
 *
 * @author Maksims Pļehanovs
 */
/**
 * Klase, kas reprezentē vienu testa jautājumu un tā pareizo atbildi.
 */
public class Jautajumi {

    private String text;
    private String answer;
    private List<String> answerOptions;

    /**
     * Jautājuma konstruktors.
     *
     * @param text jautājuma teksts
     * @param answer pareizā atbilde
     * @param answerOptions atbilžu variantu saraksts
     */
    public Jautajumi(String text, String answer, List<String> answerOptions) {
        this.text = text;
        this.answer = answer;
        this.answerOptions = answerOptions;
    }

    public List<String> getAnswerOptions() {
        return answerOptions;
    }

    /**
     * Atgriež jautājuma tekstu.
     *
     * @return jautājuma teksts
     */
    public String getText() {
        return text;
    }

    /**
     * Pārbauda atbildes pareizību.
     *
     * @param answer lietotāja atbilde
     * @return true, ja atbilde ir pareiza
     */
    public boolean isCorrect(String answer) {
        return this.answer.equalsIgnoreCase(answer);
    }
}
