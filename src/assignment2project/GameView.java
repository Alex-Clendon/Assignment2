/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class GameView extends JFrame {

    private JLabel questionLabel;
    private JLabel scoreValueLabel;
    private JRadioButton option1;
    private JRadioButton option2;
    private JRadioButton option3;
    private JRadioButton option4;
    private ButtonGroup optionsGroup;
    private JButton submitButton;
    private JButton quitButton;

    public GameView() {
        setTitle("Who Wants to Be a Millionaire - Game");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        questionLabel = new JLabel("", SwingConstants.CENTER);
        scoreValueLabel = new JLabel("", SwingConstants.CENTER);
        option1 = new JRadioButton("");
        option2 = new JRadioButton("");
        option3 = new JRadioButton("");
        option4 = new JRadioButton("");
        optionsGroup = new ButtonGroup();
        optionsGroup.add(option1);
        optionsGroup.add(option2);
        optionsGroup.add(option3);
        optionsGroup.add(option4);
        submitButton = new JButton("Submit");
        quitButton = new JButton("Quit");

        setLayout(new GridLayout(8, 1));
        add(questionLabel);
        add(scoreValueLabel);
        add(option1);
        add(option2);
        add(option3);
        add(option4);
        add(submitButton);
        add(quitButton);
    }

    public void setQuestion(String question) {
        questionLabel.setText(question);
    }


    public void setScoreValue(Integer score){
        scoreValueLabel.setText("Question is worth: "+score.toString()+" points.");
    }

    public void setOptions(List<String> options) {
        option1.setText(options.get(0));
        option2.setText(options.get(1));
        option3.setText(options.get(2));
        option4.setText(options.get(3));
        optionsGroup.clearSelection();
    }

    public String getSelectedOption() {
        if (option1.isSelected()) {
            return "a";
        } else if (option2.isSelected()) {
            return "b";
        } else if (option3.isSelected()) {
            return "c";
        } else if (option4.isSelected()) {
            return "d";
        }
        return null;
    }
    
    //Necessary for test class to set the desired button option
    public void setSelectedOption(String option){
        switch (option) {
            case "a":
                option1.setSelected(true);
                break;
            case "b":
                option2.setSelected(true);
                break;
            case "c":
                option3.setSelected(true);
                break;
            case "d":
                option4.setSelected(true);
                break;
            default:
                break;
        }
    }

    public void HandleWrongAnswer(int questionsAnswered, int score) {
        JOptionPane.showMessageDialog(this,
                "Incorrect! Thank you for playing, better Luck next time :)\n"
                + "You answered a total of: " + questionsAnswered + " questions\n"
                + "Your total score was: " + score + " points.");
    }

    public void HandleEmptyAnswer() {
        JOptionPane.showMessageDialog(this,
                "No answer selected!\nPlease select and answer and press Submit");
    }

    public void HandleGameWin() {
        JOptionPane.showMessageDialog(this,
                "Congratulations! You are a millionare!\n"
                + "You answered all 10 questions correctly, thank you for playing.");
    }

    public void setSubmitButtonListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }

    public void setQuitButtonListener(ActionListener listener) {
        quitButton.addActionListener(listener);
    }
}
