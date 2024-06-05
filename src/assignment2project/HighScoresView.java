/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2project;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HighScoresView extends JFrame {
    private JTextArea highScoresArea; //High scores displayed here

    public HighScoresView() {
        setTitle("Who Wants to Be a Millionaire - High Scores");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        highScoresArea = new JTextArea();
        highScoresArea.setEditable(false); //For displaying high scores only
        JScrollPane scrollPane = new JScrollPane(highScoresArea); //If area is large enough, include scrolling

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    //Writes the high scores to the frame
    public void setHighScores() {
        List<String> highScores = HighScoresController.getHighScores();
        StringBuilder sb = new StringBuilder();
        for (String highScore : highScores) {
            sb.append(highScore).append("\n"); //Add each highscore and a line break
        }
        highScoresArea.setText(sb.toString()); //Set the area to the highscores string
    }
}

