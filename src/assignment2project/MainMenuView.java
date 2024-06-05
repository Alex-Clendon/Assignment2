/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainMenuView extends JFrame {
    private JButton viewHighScoresButton; //highscores button
    private JButton loginButton; //login button
    private JButton removeHighScoreButton; //remove high scores button

    public MainMenuView() {
        setTitle("Who Wants to Be a Millionaire - Main Menu");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        viewHighScoresButton = new JButton("View High Scores");
        loginButton = new JButton("Login");
        removeHighScoreButton = new JButton("Remove High Score");

        //(3x1 grid layout)
        setLayout(new GridLayout(3, 1));
        add(viewHighScoresButton);
        add(loginButton);
        add(removeHighScoreButton);
    }

    //Set the action listeners for the buttons
    public void setViewHighScoresButtonListener(ActionListener listener) {
        viewHighScoresButton.addActionListener(listener);
    }

    public void setLoginButtonListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    public void setRemoveHighScoreButtonListener(ActionListener listener) {
        removeHighScoreButton.addActionListener(listener);
    }
}
