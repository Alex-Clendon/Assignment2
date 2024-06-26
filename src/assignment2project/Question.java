/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2project;

import java.util.List;

public class Question {
    private String text;
    private List<String> options;
    private String correctAnswer;
    private int points;

    //Structure of question based upon how questions are stored in database.
    //question|answer1|answer2|answer3|answer4|correct answer|point value
    public Question(String text, List<String> options, String correctAnswer, int points) {
        this.text = text;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.points = points;
    }

    //Getters for the different parts of each question
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
