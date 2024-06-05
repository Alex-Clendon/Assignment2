/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GameController {

    private GameView gameView;
    private List<Question> questions;
    private Player player;
    private int currentQuestionIndex;
    private int score;

    //initialises the game controller and sets up button action listeners
    public GameController(GameView gameView, List<Question> questions, Player player) {
        this.gameView = gameView;
        this.questions = questions;
        this.player = player;

        this.gameView.setSubmitButtonListener(new SubmitButtonListener());
        this.gameView.setQuitButtonListener(new QuitButtonListener());
    }

    //initalises the game variables and calls the first question to be loaded.
    public void startGame() {
        score = 0;
        currentQuestionIndex = 0;
        loadNextQuestion(); //load the next question of the question list
        gameView.setVisible(true);
    }

    private void loadNextQuestion() {
        if (currentQuestionIndex < questions.size()) { //10 questions in list total.
            Question currentQuestion = questions.get(currentQuestionIndex); //get the current question

            //set the game view to display the question and information
            gameView.setQuestion(currentQuestion.getText());
            gameView.setOptions(currentQuestion.getOptions());
            gameView.setScoreValue((Integer) currentQuestion.getPoints());

            currentQuestionIndex++; //increase the question index
        } else { //Game has been won if player player answers all questions.
            gameView.HandleGameWin();
            gameView.setVisible(false);
            DatabaseManager.saveHighScore(player.getName(), score); //save user's score
        }
    }

    //Getters and setters (only really used by test classes)
    public int getScore() {
        return this.score;
    }

    public Player getPlayer() {
        return this.player;
    }

    //Submit button logic
    class SubmitButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedOption = gameView.getSelectedOption(); //get the user's selected option
            //get the current question (index - 1 since loadNextQuestion increments index)
            Question currentQuestion = questions.get(currentQuestionIndex - 1);
            if (selectedOption != null && selectedOption.equals(currentQuestion.getCorrectAnswer())) { //if option is correct
                score = currentQuestion.getPoints();
                loadNextQuestion();
            } else if (selectedOption != null && !selectedOption.equals(currentQuestion.getCorrectAnswer())) { //if option is incorrect
                gameView.HandleWrongAnswer(currentQuestionIndex - 1, score);
                gameView.setVisible(false);
                DatabaseManager.saveHighScore(player.getName(), score); //save user's score
            } else { //if no option is selected (blank answer)
                gameView.HandleEmptyAnswer(); 
            }
        }
    }

    //Quit button logic
    class QuitButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            gameView.setVisible(false);
            //save users score
            DatabaseManager.saveHighScore(player.getName(), score);
        }
    }
}
