/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment1project;

import java.util.List;

/**
 *
 * @author alexa
 */
public class Question {
    private String text;
    private List<String> options;
    private String correctAnswer;
    private int points;

    //Question has text (question), options (a,b,c,d) correct answer, and point value.
    public Question(String text, List<String> options, String correctAnswer, int points) {
        this.text = text;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.points = points;
    }


    public String getText() {
        return text;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public int getPoints() {
        return points;
    }
}
