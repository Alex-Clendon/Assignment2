/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment1project;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alexa
 */

public class Millionare extends Game {
    //Main class controlling the function of the Millionare type game.
    private QuestionBank questionBank;
    private int currentQuestionIndex;
    private List<Question> questionList;

    //Has a a QuestionBank which stores the game's questions.
    public Millionare(QuestionBank questionBank, Player player, ArrayList<String> highScores){
        this.questionBank = questionBank;
        this.player = player;
    }

    @Override
    //Gets a list of questions from the question bank.
    public void initialiseGame() {
        questionList = questionBank.getQuestions();
    }

    @Override
    //Asks questions and gets answers from player until they have reached the last
    //question, or they enter a wrong answer.
    public void startGame() { 
        UserInterface ui = new UserInterface();
        
        while (currentQuestionIndex < questionList.size()) {
            Question currentQuestion = questionList.get(currentQuestionIndex);
            ui.displayQuestion(currentQuestion);
            String playerAnswer = ui.getPlayerInput(currentQuestion);
            if (playerAnswer.equalsIgnoreCase(currentQuestion.getCorrectAnswer())) {
                score = currentQuestion.getPoints();
                ui.displayMessage("\nCorrect! Your current ammount won is: "+score+
                "\n------------------------------");
                currentQuestionIndex++; 
            } else if (playerAnswer.equalsIgnoreCase("x")) {  // Exit code logic
                endGame();
                System.exit(0);
            }else {
                handleWrongAnswer(ui);
                break;
            }
        }
        endGame();
    }

    @Override
    //Save the user's high score, and ask them if they would like to quit or
    //continue playing.
    public void endGame() {      
        FileIO.saveHighScore(player.getName(), score);
        UserInterface ui = new UserInterface();
        ui.displayMessage("\nGame Over! Your total winnings are: " + score); 
        ui.displayMessage("\nWould you like to play again? (y/n)"); // Retry code logic
        String input = GameMenu.getUserChoice();
        if (input.equalsIgnoreCase("y")) {
            GameMenu.displayMenu(); //Return to menu
        }
        else if(input.equalsIgnoreCase("n")){
            System.out.println("\nAre you sure you would like to exit the program? (y/n)");
            String choice = GameMenu.getUserChoice();
            if (choice.equalsIgnoreCase("y")) {
                System.exit(0); //Quit game
            } 
            else {
                GameMenu.displayMenu(); //Return to menu
            }
        }
    }
    
    //Called when the players enters a wrong answer to a question.
    private void handleWrongAnswer(UserInterface ui) {  
        ui.displayMessage("Wrong answer! Game over.");   
    }
}
