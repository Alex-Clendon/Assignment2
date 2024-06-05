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

    public GameController(GameView gameView, List<Question> questions, Player player) {
        this.gameView = gameView;
        this.questions = questions;
        this.player = player;

        this.gameView.setSubmitButtonListener(new SubmitButtonListener());
        this.gameView.setQuitButtonListener(new QuitButtonListener());
    }

    public void startGame() {
        score = 0;
        currentQuestionIndex = 0;
        loadNextQuestion();
        gameView.setVisible(true);
    }

    private void loadNextQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            
            gameView.setQuestion(currentQuestion.getText());
            gameView.setOptions(currentQuestion.getOptions());
            gameView.setScoreValue((Integer)currentQuestion.getPoints()); 
            
            currentQuestionIndex++;
        } else { //Game has been won if player player answers all questions.
            gameView.HandleGameWin();
            gameView.setVisible(false);
            DatabaseManager.saveHighScore(player.getName(), score);
        }
    }
    
    //Getters and setters (only really used by test classes)
    public int getScore(){
        return this.score;
    }
    
    public Player getPlayer(){
        return this.player;
    }

    class SubmitButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedOption = gameView.getSelectedOption();
            Question currentQuestion = questions.get(currentQuestionIndex - 1);
            if (selectedOption != null && selectedOption.equals(currentQuestion.getCorrectAnswer())) {
                score = currentQuestion.getPoints();
                loadNextQuestion();
            } 
            else if (selectedOption != null && !selectedOption.equals(currentQuestion.getCorrectAnswer())) {
                gameView.HandleWrongAnswer(currentQuestionIndex - 1, score);
                gameView.setVisible(false);
                DatabaseManager.saveHighScore(player.getName(), score);
            }
            else{
                gameView.HandleEmptyAnswer();
            }
        }
    }

    class QuitButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            gameView.setVisible(false);
            DatabaseManager.saveHighScore(player.getName(), score);
        }
    }
}
