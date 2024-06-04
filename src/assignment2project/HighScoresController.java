/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2project;

public class HighScoresController {
    private HighScoresView highScoresView;

    public HighScoresController(HighScoresView highScoresView) {
        this.highScoresView = highScoresView;
    }

    public void showHighScores() {
        highScoresView.setHighScores(DatabaseManager.loadHighScores());
        highScoresView.setVisible(true);
    }
}

