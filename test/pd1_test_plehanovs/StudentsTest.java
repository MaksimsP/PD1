package pd1_test_plehanovs;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import java.util.Arrays;
import java.util.List;

public class StudentsTest {

    private Students instance;
    private Jautajumi question;

    @Before
    public void setUp() {
        instance = new Students();
        List<String> options = Arrays.asList("Paris", "London", "Berlin", "Madrid");
        question = new Jautajumi("Capital of France?", "Paris", options);
    }

    public StudentsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    // ─── getAnswers ───────────────────────────────────────────────
    @Test
    public void testGetAnswers_correctAnswer_incrementsRightAnswers() {
        instance.getAnswers(question, "Paris");
        assertEquals(1, instance.getRightAnswers());
        assertEquals(1, instance.getQuestionsCount());
    }

    @Test
    public void testGetAnswers_wrongAnswer_doesNotIncrementRightAnswers() {
        instance.getAnswers(question, "Berlin");
        assertEquals(0, instance.getRightAnswers());
        assertEquals(1, instance.getQuestionsCount());
    }

    @Test
    public void testGetAnswers_caseInsensitive() {
        instance.getAnswers(question, "paris");
        assertEquals(1, instance.getRightAnswers());
    }

    @Test
    public void testGetAnswers_multipleQuestions() {
        instance.getAnswers(question, "Paris");  // correct
        instance.getAnswers(question, "Berlin"); // wrong
        instance.getAnswers(question, "Paris");  // correct
        assertEquals(2, instance.getRightAnswers());
        assertEquals(3, instance.getQuestionsCount());
    }

    // ─── beginTest ────────────────────────────────────────────────
    @Test
    public void testBeginTest_resetsResults() {
        instance.getAnswers(question, "Paris");
        instance.getAnswers(question, "Paris");
        assertEquals(2, instance.getRightAnswers());

        instance.beginTest(null); // beginTest just calls clear()

        assertEquals(0, instance.getRightAnswers());
        assertEquals(0, instance.getQuestionsCount());
    }

    // ─── clear ───────────────────────────────────────────────────
    @Test
    public void testClear_resetsToZero() {
        instance.getAnswers(question, "Paris");
        instance.getAnswers(question, "Berlin");
        instance.clear();
        assertEquals(0, instance.getRightAnswers());
        assertEquals(0, instance.getQuestionsCount());
    }

    @Test
    public void testClear_onFreshInstance() {
        instance.clear(); // should not throw
        assertEquals(0, instance.getRightAnswers());
        assertEquals(0, instance.getQuestionsCount());
    }

    // ─── getRightAnswers ─────────────────────────────────────────
    @Test
    public void testGetRightAnswers_initiallyZero() {
        assertEquals(0, instance.getRightAnswers());
    }

    @Test
    public void testGetRightAnswers_afterCorrectAnswers() {
        instance.getAnswers(question, "Paris");
        instance.getAnswers(question, "Paris");
        assertEquals(2, instance.getRightAnswers());
    }

    // ─── getQuestionsCount ───────────────────────────────────────
    @Test
    public void testGetQuestionsCount_initiallyZero() {
        assertEquals(0, instance.getQuestionsCount());
    }

    @Test
    public void testGetQuestionsCount_afterAnswering() {
        instance.getAnswers(question, "Paris");
        instance.getAnswers(question, "Berlin");
        assertEquals(2, instance.getQuestionsCount());
    }

    // ─── getPercent ──────────────────────────────────────────────
    @Test
    public void testGetPercent_noQuestions_returnsZero() {
        assertEquals(0, instance.getPercent());
    }

    @Test
    public void testGetPercent_allCorrect_returns100() {
        instance.getAnswers(question, "Paris");
        instance.getAnswers(question, "Paris");
        assertEquals(100, instance.getPercent());
    }

    @Test
    public void testGetPercent_halfCorrect_returns50() {
        instance.getAnswers(question, "Paris");  // correct
        instance.getAnswers(question, "Berlin"); // wrong
        assertEquals(50, instance.getPercent());
    }

    @Test
    public void testGetPercent_noneCorrect_returnsZero() {
        instance.getAnswers(question, "Berlin");
        instance.getAnswers(question, "Madrid");
        assertEquals(0, instance.getPercent());
    }

    // ─── getGrade ────────────────────────────────────────────────
    @Test
    public void testGetGrade_noQuestions_returns1() {
        assertEquals(1, instance.getGrade());
    }

    @Test
    public void testGetGrade_100percent_returns10() {
        for (int i = 0; i < 10; i++) {
            instance.getAnswers(question, "Paris");
        }
        assertEquals(10, instance.getGrade());
    }

    @Test
    public void testGetGrade_85percent_returns9() {
        // 17 correct out of 20 = 85%
        for (int i = 0; i < 17; i++) {
            instance.getAnswers(question, "Paris");
        }
        for (int i = 0; i < 3; i++) {
            instance.getAnswers(question, "Berlin");
        }
        assertEquals(9, instance.getGrade());
    }

    @Test
    public void testGetGrade_50percent_returns5() {
        // 50% → >= 45 → grade 5
        instance.getAnswers(question, "Paris");
        instance.getAnswers(question, "Berlin");
        assertEquals(5, instance.getGrade());
    }

    @Test
    public void testGetGrade_20percent_returns2() {
        // 1 correct out of 5 = 20% → >= 10 → grade 2
        instance.getAnswers(question, "Paris");
        for (int i = 0; i < 4; i++) {
            instance.getAnswers(question, "Berlin");
        }
        assertEquals(2, instance.getGrade());
    }
}
