package assignment2project;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameControllerTest {
    private GameView gameView;
    private Player player;
    private GameController gameController;
    private List<Question> questions;
    
    //Database required for incorrect answer handling (highscore saved)
    @BeforeClass
    public static void setUpClass() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        DatabaseSetup.setupDatabase();
        DatabaseManager.initializeDatabase();
    }

    @Before
    public void setUp() {
        gameView = new GameView();
        questions = new ArrayList<>();
        List<String> options1 = new ArrayList<>();
        List<String> options2 = new ArrayList<>();
        for (char i = 'a'; i <= 'd'; i++) {
            String option = String.valueOf(i);
            options1.add(option);
            options2.add(option);
        }
        questions.add(new Question("Test Question 1 (a)", options1, "a", 100));
        questions.add(new Question("Test Question 2 (b)", options2, "b", 200));
        player = new Player("Test Player");
        gameController = new GameController(gameView, questions, player);
    }

    @Test
    public void testStartGame() {
        gameController.startGame();
        assertTrue(gameView.isVisible());
        assertTrue(gameController.getScore() == 0);
        assertTrue(gameController.getPlayer() == player);
    }

    @Test
    public void testCorrectAnswer() {
        gameController.startGame();
        gameView.setSelectedOption("a"); //correct answer for test question
        gameController.new SubmitButtonListener().actionPerformed(null);
        assertTrue(gameController.getScore() == 100);
    }

    @Test
    public void testIncorrectAnswer() {
        DatabaseManager.saveUserCredentials(player.getName(), "testpassword");
        gameController.startGame();
        gameView.setSelectedOption("b"); //incorrect answer for test question
        gameController.new SubmitButtonListener().actionPerformed(null);
        assertTrue(gameController.getScore() == 0);
        assertFalse(gameView.isVisible());
        DatabaseManager.removeHighScore(player.getName());
        DatabaseManager.removeUser(player.getName());
    }

    @Test
    public void testBlankAnswer() {
        gameController.startGame();
        gameController.new SubmitButtonListener().actionPerformed(null);
        assertTrue(gameController.getScore() == 0);
        assertTrue(gameView.isVisible());
    }

    @After
    public void tearDown() {
        gameView.setVisible(false);
    }
}