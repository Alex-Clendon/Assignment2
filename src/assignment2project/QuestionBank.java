/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2project;

/**
 *
 * @author alexa
 */

import java.util.List;

public class QuestionBank {
    //Stores a list of all of the questions from the questions file.
    private List<Question> questions;

    //Gets questions
    public QuestionBank(String filePath) {
        questions = FileIO.loadQuestions();
    }

    //Returns list of questions.
    public List<Question> getQuestions() {
        return questions;
    }

}