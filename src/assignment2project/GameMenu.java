/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2project;

/**
 *
 * @author alexa
 */
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GameMenu {
    //Handles the users input and decides where user should be placed depending
    //on their inputs to the menu.
    private static Scanner scanner = new Scanner(System.in);

    //Shows the main menu of the program and gets the user's choice.
    public static void displayMenu() {
        System.out.println("\nWelcome to Who Wants to Be a Millionaire!");
        System.out.println("1. View High Scores");
        System.out.println("2. Login (or create new user)");
        System.out.println("3. Remove High Score");
        System.out.println("X to quit at any time");
        System.out.print("Enter your choice: ");
        String choice = getUserChoice();
        
        if (choice.equalsIgnoreCase("x")) { // Exit code logic before player login
            System.out.println("\nAre you sure you would like to exit the program? (y/n)");
            choice = getUserChoice();
            if (choice.equalsIgnoreCase("y")) {
                System.exit(0);
            } else {
                displayMenu();
            }
        }
        
        switch (choice) {
            case "1":
                viewHighScores();
                displayMenu(); // Display the menu again after viewing high scores
                break;
            case "2":
                UserManager.loadUsersFromFile();
                //Start a new millionare game for this current user if the user can be authenticated.
                if (UserManager.authenticateUser()) {
                   UserManager.startMillionaireGame(UserManager.getPlayerName());
                }
                else{
                    displayMenu(); //Return to menu if they can't be authenticated.
                }
            case "3":
                UserManager.loadUsersFromFile();
                //Remove the highscore of the user if they can be authenticated.
                if(UserManager.authenticateUser()){
                    removeHighScore();
                }
                else{
                    displayMenu(); //Return to menu if they can't be authenticated.
                }
                break;
            default:
                System.out.println("Invalid choice. Please try again. (X to quit)\n\n");
                displayMenu();
                break;
        }
    }
    
    //Return the user's choice to a given menu prompt
    public static String getUserChoice() {
        while (true) {
            try {
                return scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number. or X to quit");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }

    //Show all the highscores stored in file to the user
    private static void viewHighScores() {
        ArrayList<String> highScores = FileIO.loadHighScores();
        System.out.println("\nHigh Scores:");
        for (int i = 0; i < highScores.size(); i++) {
            System.out.println(highScores.get(i));
        }
    }
    
    //Remove each high score the user has in the highscores. 
    private static void removeHighScore() {
        System.out.println("Would you like to remove your high score? (y/n)");
        String choice = getUserChoice();
        if (choice.equalsIgnoreCase("y")) {
            UserManager.removeUserHighScore(UserManager.getPlayerName());
        }
        displayMenu();
    }
    
    //Main entry into the program, User's are immediately prompted with the 
    //main menu of the program.
    public static void main(String[] args) {
        displayMenu();
    }
}
