/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.JOptionPane;

public class LoginController {

    private LoginView loginView;
    private MainMenuView mainMenuView;
    private String lastMessage; // For testing and visbility purposes (login status flag)

    public LoginController(LoginView loginView, MainMenuView mainMenuView) {
        this.loginView = loginView;
        this.mainMenuView = mainMenuView;

        this.loginView.setLoginButtonListener(new LoginButtonListener());
    }
    
    public String getLastMessage() { // Getter for the last message
        return lastMessage;
    }

    class LoginButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername(); //Set the username to the user input
            String password = loginView.getPassword(); //Set password to user input

            Map<String, String> userCredentials = DatabaseManager.loadUserCredentials(); //load users from DB
            if (userCredentials.containsKey(username)) { //If user exists in DB
                if (userCredentials.get(username).equals(password)) { //Valid login
                    lastMessage = "Login successful!";
                    JOptionPane.showMessageDialog(loginView, lastMessage);
                    System.out.println("Login successful for user: " + username);
                    loginView.setVisible(false);

                    //Start new game of who wants to be a millionaire with new player
                    GameView millionareGameView = new GameView();
                    Player player = new Player(username);
                    GameController newGame = new GameController(millionareGameView, DatabaseManager.loadQuestions(), player);  // Initialise the game here
                    newGame.startGame(); //Start the new game
                } 
                else if (!userCredentials.get(username).equals(password)) { //Wrong password or duplicate username
                    lastMessage = "Incorrect password, or a user already exists with this name!\nUsernames and Passwords are Case-Sensitive!";
                    JOptionPane.showMessageDialog(loginView, lastMessage);
                    System.out.println("Incorrect password for user: " + username);
                }
            } 
            //If username or password is blank or greater than DB size limit of 20 chars
            else if (username.equals("") || password.equals("") || username.length() > 20 || password.length() > 20) {
                lastMessage = "Username or password cannot be blank or > 20 characters!\nUsernames and Passwords are Case-Sensitive!";
                JOptionPane.showMessageDialog(loginView, lastMessage);
                System.out.println("Bad username or password entered");
            } 
            //Create a new user with supplied input if no other condition is met
            else {
                DatabaseManager.saveUserCredentials(username, password);
                lastMessage = "New user created!";
                JOptionPane.showMessageDialog(loginView, lastMessage);
                System.out.println("New user created: " + username);
                loginView.setVisible(false);

                //Start new game of who wants to be a millionaire with new player
                GameView millionareGameView = new GameView();
                Player player = new Player(username);
                GameController newGame = new GameController(millionareGameView, DatabaseManager.loadQuestions(), player);  // Initialise the game here for new users
                newGame.startGame();  // Start the game for new users as well
            }
        }
    }
}

