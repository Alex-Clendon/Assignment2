/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment1project;

/**
 *
 * @author alexa
 */
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    //Class is used for player actions within the game
    private Scanner scanner;

    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    //Shows the question passed in to the user.
    public void displayQuestion(Question question) {
        System.out.println(question.getText());
        List<String> options = question.getOptions();
        char option = 'a';
        for (String opt : options) {
            System.out.println(option + ". " + opt);
            option++;
        }
    }

    //Returns the input of the user after they have been given the question.
    public String getPlayerInput(Question question) {
        String playerAnswer;
        List<String> options = question.getOptions();
        boolean validInput = false;

        do {
            System.out.print("Enter your answer: ");
            playerAnswer = scanner.nextLine().toLowerCase();
            switch(playerAnswer){
                case "x": // Exit code logic
                    System.out.println("\nAre you sure you would like to exit the program? (y/n)");
                    playerAnswer = scanner.nextLine().toLowerCase();
                    if (playerAnswer.equalsIgnoreCase("y")) {
                        return "x";
                    }
                    break;            
                case "a":
                case "b":
                case "c":
                case "d":
                    validInput = true;
                    break;
                default:
                    System.out.println("Invalid input. Please enter one of the option letters (a, b, c, or d).");
                    break;
            }
        } 
        while (!validInput);
        
        return playerAnswer;
    }

    //Prompt a player with a message.
    public void displayMessage(String message) {
        System.out.println(message);
    }
}
