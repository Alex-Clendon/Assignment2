/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2project;

/**
 *
 * @author alexa
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;

public class UserManager {
    //Class for controlling the user manipulating files. Contains code for
    //handling new and existing users' actions.
    private static final String PLAYER_FILE = "./resources/players.txt";
    private static Map<String, String> userCredentials = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    private static String playername;

    //Calls fileIO to load the players file into memory. 
    public static void loadUsersFromFile() {
        userCredentials = FileIO.loadUserCredentials();
    }

    //Checks if a user's credentials they are loggin in with are valid, and where
    //to put them depending on how this method was called.
    //Returns true if the user is logging in with pre-existing credentials,
    //Returns false if the user is logging in with newly created credentials.
    public static boolean authenticateUser() {
        String username;
        String password;
        boolean validUsername;
        boolean validPassword;

        //Input validation
        do {
            System.out.print("Enter your username: ");
            username = scanner.nextLine();
            validUsername = !username.isEmpty();
            if (!validUsername) {
                System.out.println("Username cannot be empty. Please try again.");
            }
        } 
        while (!validUsername);

        //Input validation
        do {
            System.out.print("Enter your password: ");
            password = scanner.nextLine();
            validPassword = !password.isEmpty();
            if (!validPassword) {
                System.out.println("Password cannot be empty. Please try again.");
            }
        } 
        while (!validPassword);

        //Checks if the passed credentials are in the players hash map.
        //True returned if username and password are correct
        //Recursively calls if the user is correct but the password isn't
        //False returned if the user creates a new user
        //Return values utilised by GameMenu to decide where to place the user.
        if (userCredentials.containsKey(username) && userCredentials.get(username).equals(password)) {
            System.out.println("Login successful!\n");
            playername = username;
            return true;
        } 
        else if(userCredentials.containsKey(username) && !userCredentials.get(username).equals(password)){
            System.out.println("Invalid password, please try again");
            return authenticateUser();
        }
        else { //User not in text file.
            String input;
            boolean validResponse;
            do {
                System.out.println("User not found (name & pswd are case sensitive). Create new player? (y/n)");
                input = scanner.nextLine().toLowerCase();
                validResponse = input.equals("y") || input.equals("n");
                if (!validResponse) {
                    System.out.println("Invalid input. Please enter 'y' or 'n'.");
                }
            } 
            while (!validResponse);

            if (input.equals("y")) {
                registerNewUser();
                return false;
            } 
            else {
                System.out.println("Returning to login screen\n");
                return false;
            }
        }
    }
    
    //Returns this player's name once assigned.
    public static String getPlayerName() {
        return playername;
    }

    //Creates a new user if the passed credentials don't match any in the file
    //and they have answered yes to create a new user.
    private static void registerNewUser() {
        String newUsername;
        String newPassword;
        boolean validUsername;
        boolean validPassword;

        //Input validation
        do {
            System.out.print("Enter a new username: ");
            newUsername = scanner.nextLine();
            validUsername = !newUsername.isEmpty();
            if (!validUsername) {
                System.out.println("Username cannot be empty. Please try again.");
            }
        } 
        while (!validUsername);

        //Input validation
        do {
            System.out.print("Enter a new password: ");
            newPassword = scanner.nextLine();
            validPassword = !newPassword.isEmpty();
            if (!validPassword) {
                System.out.println("Password cannot be empty. Please try again.");
            }
        } 
        while (!validPassword);

        //Save new user to file.
        FileIO.saveUserCredentials(newUsername, newPassword);
        userCredentials.put(newUsername, newPassword);
        System.out.println("User registered successfully!\n");
    }
    
    //Remove the users score if they have chosen to do so from the GameMenu.
    public static void removeUserHighScore(String username) {
        if (userCredentials.containsKey(username)) {
            FileIO.removeHighScore(username);
            System.out.println("High score removed successfully for user: " + username);
        } else {
            System.out.println("User not found or not logged in.");
        }
    }
    
    public static void startMillionaireGame(String playername) {
        //Creates a QuestionBank and list of highscores
        //For the current game and passes them to a new Millionare instance.
        String filePath = "./resources/questions.txt";
        QuestionBank questionBank = new QuestionBank(filePath);
        ArrayList<String> highScores = FileIO.loadHighScores();

        Player player = new Player(playername);
        Millionare game = new Millionare(questionBank, player, highScores);
        //Start the new game.
        game.initialiseGame();
        game.startGame();
    }
}
