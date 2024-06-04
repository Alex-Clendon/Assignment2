/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class MainMenuController {
    private MainMenuView mainMenuView;
    private HighScoresView highScoresView;
    private LoginView loginView;
    private GameView gameView;

    public MainMenuController(MainMenuView mainMenuView, HighScoresView highScoresView, LoginView loginView, GameView gameView) {
        this.mainMenuView = mainMenuView;
        this.highScoresView = highScoresView;
        this.loginView = loginView;
        this.gameView = gameView;

        this.mainMenuView.setViewHighScoresButtonListener(new ViewHighScoresButtonListener());
        this.mainMenuView.setLoginButtonListener(new LoginButtonListener());
        this.mainMenuView.setRemoveHighScoreButtonListener(new RemoveHighScoreButtonListener());
    }

    class ViewHighScoresButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            highScoresView.setHighScores(DatabaseManager.loadHighScores());
            highScoresView.setVisible(true);
        }
    }

    class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            loginView.setVisible(true);
        }
    }

    class RemoveHighScoreButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = JOptionPane.showInputDialog(mainMenuView, "Enter the username to remove the high score:");
            DatabaseManager.removeHighScore(username);
        }
    }
}