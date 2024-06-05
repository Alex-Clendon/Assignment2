/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2project;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseManager {

    //Db URL (embedded)
    private static final String DB_URL = "jdbc:derby:MillionareDB;";
    public static Connection conn;

    //Initialise connection with the DB
    public static void initializeDatabase() {
        try {
            conn = DriverManager.getConnection(DB_URL, "pdc", "pdc");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Get questions from DB
    public static List<Question> loadQuestions() {
        List<Question> questions = new ArrayList<>();
        //Select all questions query
        String query = "SELECT * FROM questions ORDER BY point_value ASC";

        //Create a statement and result set to retrieve all question
        //data from the DB. Table Format:
        //question|answer1|answer2|answer3|answer4|correct_answer|point_value
        try ( Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String text = rs.getString("question");
                List<String> options = new ArrayList<>();
                options.add(rs.getString("answer1"));
                options.add(rs.getString("answer2"));
                options.add(rs.getString("answer3"));
                options.add(rs.getString("answer4"));
                String correctAnswer = rs.getString("correct_answer");
                int points = rs.getInt("point_value");

                //adds retrieved question to questions array
                questions.add(new Question(text, options, correctAnswer, points));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }

    //Get users from DB
    public static Map<String, String> loadUserCredentials() {
        Map<String, String> userCredentials = new HashMap<>();
        //Select username and password for users in user table
        String query = "SELECT username, password FROM users";

        //User table format: username|password
        try ( Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                //Put the users into the map to return
                userCredentials.put(username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userCredentials;
    }

    //Save users to DB
    public static void saveUserCredentials(String username, String password) {
        //User insertion statement
        String insertUser = "INSERT INTO users (username, password) VALUES (?, ?)";

        try ( PreparedStatement pstmt = conn.prepareStatement(insertUser)) {

            pstmt.setString(1, username); //set username of new user
            pstmt.setString(2, password); //set password of new user
            pstmt.executeUpdate(); //execute insertion
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Remove user from DB
    public static void removeUser(String username) {
        //User deletion statement.
        String deleteUser = "DELETE FROM users WHERE username = ?";

        try ( PreparedStatement pstmt = conn.prepareStatement(deleteUser)) {
            pstmt.setString(1, username); //set username to the passed in user
            pstmt.executeUpdate(); //execute deletion
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Save high scores to DB
    public static void saveHighScore(String username, int score) {
        //High score insertion statement
        String insertScore = "INSERT INTO HIGHSCORES (username, highscore) VALUES (?, ?)";

        try ( PreparedStatement pstmt = conn.prepareStatement(insertScore)) {

            pstmt.setString(1, username);
            pstmt.setInt(2, score);
            pstmt.executeUpdate(); //execute insertion
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Get high scores from DB
    public static List<String> loadHighScores() {
        List<String> highScores = new ArrayList<>();
        //Select usernames and highscores from highscores table
        String query = "SELECT username, highscore FROM HIGHSCORES ORDER BY highscore DESC";

        try ( Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                //add the user and their highscores as a list entry
                String entry = rs.getString("username") + ", " + rs.getInt("highscore");
                highScores.add(entry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return highScores;
    }

    //Remove high scores from DB
    public static void removeHighScore(String username) {
        //High score deletion statement
        String deleteScore = "DELETE FROM HIGHSCORES WHERE username = ?";

        try ( PreparedStatement pstmt = conn.prepareStatement(deleteScore)) {

            pstmt.setString(1, username); //set username to passed in user
            pstmt.executeUpdate(); //execute deletion
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
