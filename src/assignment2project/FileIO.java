/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2project;

/**
 *
 * @author alexa
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FileIO {
    //Resource file locations
    private static final String PLAYER_FILE = "./resources/players.txt";
    private static final String QUESTION_FILE = "./resources/questions.txt";
    private static final String HIGH_SCORE_FILE = "./resources/hiscores.txt";

    //Loads users in from players.txt, returns the list.
    public static Map<String, String> loadUserCredentials() {
        Map<String, String> userCredentials = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PLAYER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String username = parts[0];
                    String password = parts[1];
                    userCredentials.put(username, password);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userCredentials;
    }

    //Loads question in from questions.txt in the right format, returns list.
    public static List<Question> loadQuestions() {
        List<Question> questions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(QUESTION_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 7) {
                    String text = parts[0];
                    List<String> options = new ArrayList<>();
                    options.add(parts[1]);
                    options.add(parts[2]);
                    options.add(parts[3]);
                    options.add(parts[4]);
                    String correctAnswer = parts[5].toLowerCase();
                    int points = Integer.parseInt(parts[6]);
                    Question question = new Question(text, options, correctAnswer, points);
                    questions.add(question);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questions;
    }

    //Saves the user passed in as parameter to players.txt
    public static void saveUserCredentials(String username, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PLAYER_FILE, true))) {
            writer.write(username.trim() + "," + password);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Saves the highscore of the user passed in as parameter to hiscore.txt
    public static void saveHighScore(String username, int score) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HIGH_SCORE_FILE, true))) {
            writer.write(username + "," + score);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Loads the highscores of users from hiscores.txt
    public static ArrayList<String> loadHighScores() {
        ArrayList<String> highScores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(HIGH_SCORE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                highScores.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return highScores;
    }
    
    //Removes all of the highscores of the player passed in from hiscores.txt
    public static void removeHighScore(String username) {
        ArrayList<String> highScores = loadHighScores();
        Iterator<String> iterator = highScores.iterator();
        while(iterator.hasNext()){
            String entry = iterator.next();
            if(entry.startsWith(username + ",")){
                iterator.remove();
            }
        }
        saveHighScoresList(highScores);
    }
    
    //Called by removeHighScore to update the list of high scores once a user's scores are removed.
    public static void saveHighScoresList(ArrayList<String> highScores) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(HIGH_SCORE_FILE))) {
        for (String entry : highScores) {
            writer.write(entry);
            writer.newLine();
        }
    } 
    catch (IOException e) {
        e.printStackTrace();
    }
}
}
