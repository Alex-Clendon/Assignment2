/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2project;

public class Main {
    public static void main(String[] args) {
        
        //Loading derby driver class into memory
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        //Setup / create the database if not already created
        DatabaseSetup.setupDatabase();
        
        //Initialise the embedded derby DB
        DatabaseManager.initializeDatabase();

        //Initialising the view classes for the different sections
        MainMenuView mainMenuView = new MainMenuView();
        GameView gameView = new GameView();
        HighScoresView highScoresView = new HighScoresView();
        LoginView loginView = new LoginView();

        //Initialise the main menu controller
        MainMenuController mainMenuController = new MainMenuController(mainMenuView, highScoresView, loginView, gameView);
        
        //Initialise the login controller (BAD, move to mainMenuController)
        LoginController loginController = new LoginController(loginView, mainMenuView);

        mainMenuView.setVisible(true);
        
    }
}
