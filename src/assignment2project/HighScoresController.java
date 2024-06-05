/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2project;

import java.util.List;

public class HighScoresController {
    private HighScoresView highScoresView;

    public HighScoresController(HighScoresView highScoresView) {
        this.highScoresView = highScoresView;
    }

    //Show the highscores in the DB
    public static List<String> getHighScores() {
        List<String> highScores = DatabaseManager.loadHighScores(); //get highscores from DB
        return highScores; //return highscores list
    }
    
    //Remove highscores from DB
    public static void removeHighScores(String username){
        DatabaseManager.removeHighScore(username);
    }
}

