/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2project;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class HighScoresView extends JFrame {
    private JTextArea highScoresArea;

    public HighScoresView() {
        setTitle("Who Wants to Be a Millionaire - High Scores");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        highScoresArea = new JTextArea();
        highScoresArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(highScoresArea);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    public void setHighScores(List<String> highScores) {
        StringBuilder sb = new StringBuilder();
        for (String highScore : highScores) {
            sb.append(highScore).append("\n");
        }
        highScoresArea.setText(sb.toString());
    }
}

