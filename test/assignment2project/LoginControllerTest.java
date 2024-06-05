/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package assignment2project;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alexa
 */
public class LoginControllerTest {
    
    private LoginView loginView;
    private LoginController loginController;
    private MainMenuView mainMenuView;
    
    //Valid usernames and passwords must be <= 20 chars
    private static final String INVALID_USERNAME = "aaaaaaaaaaaaaaaaaaaaa"; //21
    private static final String VALID_USERNAME = "aaaaaaaaaaaaaaaaaaaa"; //20
    private static final String INVALID_PASSWORD = "aaaaaaaaaaaaaaaaaaaaa"; //21
    private static final String VALID_PASSWORD = "aaaaaaaaaaaaaaaaaaaa"; //20
    
    @BeforeClass
    public static void setUpClass() {
        DatabaseSetup.setupDatabase();
        DatabaseManager.initializeDatabase();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        loginView = new LoginView();
        mainMenuView = new MainMenuView();
        loginController = new LoginController(loginView, mainMenuView);
        loginView.setVisible(true);
    }
    
    @After
    public void tearDown() {
        DatabaseManager.removeUser(VALID_USERNAME);
    }
    
    @Test
    public void testLoginNewUser(){
        loginView.setUsername(VALID_USERNAME);
        loginView.setPassword(VALID_PASSWORD);
        loginController.new LoginButtonListener().actionPerformed(null);
        assertEquals("New user created!", loginController.getLastMessage());
    }
    
    @Test
    public void testLoginValidNameAndPassword() {
        DatabaseManager.saveUserCredentials(VALID_USERNAME, VALID_PASSWORD);
        loginView.setUsername(VALID_USERNAME);
        loginView.setPassword(VALID_PASSWORD);
        loginController.new LoginButtonListener().actionPerformed(null);
        assertEquals("Login successful!", loginController.getLastMessage());
    }

    @Test
    public void testLoginInvalidName() {
        loginView.setUsername(INVALID_USERNAME);
        loginView.setPassword(VALID_PASSWORD);
        loginController.new LoginButtonListener().actionPerformed(null);
        assertEquals("Username or password cannot be blank or > 20 characters!\nUsernames and Passwords are Case-Sensitive!", loginController.getLastMessage());
    }
    
    @Test
    public void testLoginInvalidPassword() {
        loginView.setUsername(VALID_USERNAME);
        loginView.setPassword(INVALID_PASSWORD);
        loginController.new LoginButtonListener().actionPerformed(null);
        assertEquals("Username or password cannot be blank or > 20 characters!\nUsernames and Passwords are Case-Sensitive!", loginController.getLastMessage());
    }
    
}
