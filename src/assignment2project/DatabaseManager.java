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

    private static final String DB_URL = "jdbc:derby:MillionareDB;";
    public static Connection conn;

    public static void initializeDatabase() {
        try {
            conn = DriverManager.getConnection(DB_URL, "pdc", "pdc");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Question> loadQuestions() {
        List<Question> questions = new ArrayList<>();
        String query = "SELECT * FROM questions ORDER BY point_value ASC";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String text = rs.getString("question");
                List<String> options = new ArrayList<>();
                options.add(rs.getString("answer1"));
                options.add(rs.getString("answer2"));
                options.add(rs.getString("answer3"));
                options.add(rs.getString("answer4"));
                String correctAnswer = rs.getString("correct_answer");
                int points = rs.getInt("point_value");

                questions.add(new Question(text, options, correctAnswer, points));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }

    public static Map<String, String> loadUserCredentials() {
        Map<String, String> userCredentials = new HashMap<>();
        String query = "SELECT username, password FROM users";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                userCredentials.put(username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userCredentials;
    }

    public static void saveUserCredentials(String username, String password) {
        String insertUser = "INSERT INTO users (username, password) VALUES (?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(insertUser)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveHighScore(String username, int score) {
        String insertScore = "INSERT INTO HIGHSCORES (username, highscore) VALUES (?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(insertScore)) {

            pstmt.setString(1, username);
            pstmt.setInt(2, score);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String> loadHighScores() {
        List<String> highScores = new ArrayList<>();
        String query = "SELECT username, highscore FROM HIGHSCORES ORDER BY highscore DESC";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String entry = rs.getString("username") + ", " + rs.getInt("highscore");
                highScores.add(entry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return highScores;
    }

    public static void removeHighScore(String username) {
        String deleteScore = "DELETE FROM HIGHSCORES WHERE username = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(deleteScore)) {

            pstmt.setString(1, username);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
