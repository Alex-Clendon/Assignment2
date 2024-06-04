/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2project;

import java.util.ArrayList;
import java.util.List;

public class QuestionBank {
    private List<Question> questions;

    public QuestionBank() {
        questions = new ArrayList<>();
        // Load questions from database or other source
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }
}
