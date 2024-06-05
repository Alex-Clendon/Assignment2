package assignment2project;

import assignment2project.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class DatabaseManagerTest {
    private static final String TEST_USERNAME = "testuser";
    private static final String TEST_PASSWORD = "testpassword";

    @Before
    public void setUp() {
        DatabaseSetup.setupDatabase();
        DatabaseManager.initializeDatabase();
    }

    @After
    public void tearDown() {
        // Clean up the database after each test
        DatabaseManager.removeHighScore(TEST_USERNAME);
        DatabaseManager.removeUser(TEST_USERNAME);
    }

    @Test
    public void testLoadQuestions() {
        List<Question> questions = DatabaseManager.loadQuestions();
        assertNotNull(questions);
        assertFalse(questions.isEmpty());

        // Verify that the first question has the expected properties
        Question firstQuestion = questions.get(0);
        assertEquals("Who was the star of the 1973 hit film \"Zanjeer\"?", firstQuestion.getText());
        List<String> expectedOptions = new ArrayList<>();
        expectedOptions.add("Amitabh Bachchan");
        expectedOptions.add("Shahrukh Khan");
        expectedOptions.add("Anil Kapoor");
        expectedOptions.add("Madhur Mittal");
        assertEquals(expectedOptions, firstQuestion.getOptions());
        assertEquals("a", firstQuestion.getCorrectAnswer());
        assertEquals(100, firstQuestion.getPoints());
    }

    @Test
    public void testLoadUserCredentials() {
        Map<String, String> userCredentials = DatabaseManager.loadUserCredentials();
        assertNotNull(userCredentials);
    }

    @Test
    public void testSaveUserCredentials() {
        DatabaseManager.saveUserCredentials(TEST_USERNAME, TEST_PASSWORD);
        Map<String, String> userCredentials = DatabaseManager.loadUserCredentials();
        assertTrue(userCredentials.containsKey(TEST_USERNAME));
        assertEquals(TEST_PASSWORD, userCredentials.get(TEST_USERNAME));
    }

    @Test
    public void testSaveHighScore() {
        DatabaseManager.saveUserCredentials(TEST_USERNAME, TEST_PASSWORD);
        DatabaseManager.saveHighScore(TEST_USERNAME, 1000);
        
        List<String> highScores = DatabaseManager.loadHighScores();
        assertNotNull(highScores);
        assertFalse(highScores.isEmpty());
        assertTrue(highScores.get(0).contains(TEST_USERNAME));
        assertTrue(highScores.get(0).contains("1000"));
    }
}