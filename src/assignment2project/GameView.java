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

    private JLabel questionLabel; //Question text
    private JLabel scoreValueLabel; //Score value text
    private JRadioButton option1; //1st option text
    private JRadioButton option2; //2nd option text
    private JRadioButton option3; //3rd option text
    private JRadioButton option4; //4th option text
    private ButtonGroup optionsGroup;
    private JButton submitButton;
    private JButton quitButton;

    //Initialise all of the components of the frame (Questions, buttons, etc)
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
        
        //8x1 grid layout
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

    //Sets the question text for the current question
    public void setQuestion(String question) {
        questionLabel.setText(question);
    }

    //Sets the score value associated with the current question
    public void setScoreValue(Integer score){
        scoreValueLabel.setText("Question is worth: "+score.toString()+" points.");
    }

    //Sets the text for the options associated with the current question
    public void setOptions(List<String> options) {
        option1.setText(options.get(0));
        option2.setText(options.get(1));
        option3.setText(options.get(2));
        option4.setText(options.get(3));
        optionsGroup.clearSelection();
    }

    //Returns the user's selected option (a, b, c, or d)
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
        return null; //Returns null if no option selected (blank answer)
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

    //If wrong answer input, Display a game over pop up
    public void HandleWrongAnswer(int questionsAnswered, int score) {
        JOptionPane.showMessageDialog(this,
                "Incorrect! Thank you for playing, better Luck next time :)\n"
                + "You answered a total of: " + questionsAnswered + " questions\n"
                + "Your total score was: " + score + " points.");
    }

    //If empty answer (nothing selected), display error pop up
    public void HandleEmptyAnswer() {
        JOptionPane.showMessageDialog(this,
                "No answer selected!\nPlease select and answer and press Submit");
    }

    //If game has been won, display win pop up.
    public void HandleGameWin() {
        JOptionPane.showMessageDialog(this,
                "Congratulations! You are a millionare!\n"
                + "You answered all 10 questions correctly, thank you for playing.");
    }

    //Set the submit button listener
    public void setSubmitButtonListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }
    
    //Set the quit button listener
    public void setQuitButtonListener(ActionListener listener) {
        quitButton.addActionListener(listener);
    }
}
