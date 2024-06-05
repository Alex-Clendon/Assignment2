/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2project;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseSetup {

    //URL of embedded DB, create directory if not already created.
    private static final String DATABASE_URL = "jdbc:derby:MillionareDB;create=true";

    //Sets up connection to DB 
    public static void setupDatabase() {
        
        try ( Connection conn = DriverManager.getConnection(DATABASE_URL, "pdc", "pdc")) {
            conn.setAutoCommit(true); // Ensure auto-commit is enabled
            if (isDatabaseEmpty(conn)) { //Create tables and insert data if the DB is empty
                createTables(conn);
                insertData(conn);
                listTables(); //Mainly for debugging / visibility
            }
            else{
                System.out.println("Database already populated, no creation");
                listTables(); //Also mainly for debugging / visibility
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Returns true if the database contains no tables (it is empty)
    private static boolean isDatabaseEmpty(Connection conn) throws SQLException {
        DatabaseMetaData metaData = conn.getMetaData();
        ResultSet tables = metaData.getTables(null, "PDC", null, null); // Use the schema name "PDC"
        return !tables.next();
    }

    //Create the tables QUESTIONS, USER, and HIGHSCORES if the database is empty
    private static void createTables(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();

        // Create QUESTIONS table
        stmt.executeUpdate("CREATE TABLE PDC.QUESTIONS (" // Specify schema "PDC"
                + "question VARCHAR(200) PRIMARY KEY, "
                + "answer1 VARCHAR(30), "
                + "answer2 VARCHAR(30), "
                + "answer3 VARCHAR(30), "
                + "answer4 VARCHAR(30), "
                + "correct_answer CHAR(1), "
                + "point_value INTEGER)");

        // Create USERS table
        stmt.executeUpdate("CREATE TABLE PDC.USERS (" // Specify schema "PDC"
                + "username VARCHAR(20) PRIMARY KEY, "
                + "password VARCHAR(20))");

        // Create HIGHSCORES table
        stmt.executeUpdate("CREATE TABLE PDC.HIGHSCORES (" // Specify schema "PDC"
                + "ID INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY, "
                + "username VARCHAR(20) REFERENCES PDC.USERS(username), "
                + "highscore INTEGER)");

        stmt.close();
    }
    
    //Insert the questions into the question table after creating QUESTIONS table if DB is empty.
    private static void insertData(Connection conn) throws SQLException{
        Statement stmt = conn.createStatement();
        
        //Insert each question in the correct format
        String insertQuestions = "INSERT INTO questions (question, answer1, answer2, answer3, answer4, correct_answer, point_value) VALUES " +
        "('Who was the star of the 1973 hit film \"Zanjeer\"?', 'Amitabh Bachchan', 'Shahrukh Khan', 'Anil Kapoor', 'Madhur Mittal', 'a', 100)," +
        "('A picture of three lions is seen in the national emblem of India. What is written underneath it?', 'The truth alone triumphs', 'Lies alone triumph', 'Money alone triumphs', 'Fashion alone triumphs', 'a', 200)," +
        "('In depictions of God Rama, he is famously holding what in his right hand?', 'A child', 'A flower', 'A bow and arrow', 'A sword', 'c', 500)," +
        "('The song \"Darshan Do Ghanshyam\" was written by which famous Indian poet, according to the movie?', 'Mirabai', 'Kabeer', 'Surdas', 'Tulsidas', 'c', 1000)," +
        "('On an American $100 bill, there is a portrait of which American statesman?', 'Benjamin Franklin', 'Franklin Roosevelt', 'George Washington', 'Abraham Lincoln', 'a', 10000)," +
        "('Who invented the first commercially-successful revolver?', 'Samuel Colt', 'Daniel Wesson', 'Oliver Winchester', 'Thomas Edison', 'a', 50000)," +
        "('Cambridge Circus is in which U.K. city?', 'Oxford', 'Leeds', 'Cambridge', 'London', 'd', 100000)," +
        "('Which cricketer has scored the most first-class centuries?', 'Ricky Ponting', 'Jimmy Bridges', 'Sachin Tendulkar', 'Jack Hobbs', 'd', 250000)," +
        "('In Alexander Dumas'' book \"The Three Musketeers\", two of the musketeers are called Athos and Porthos. What is the name of the third Musketeer?', 'Aramis', 'D''Artagnan', 'Cardinal Richelieu', 'Planchet', 'a', 500000)," +
        "('Who''s picture is on the Indian 1000-Rupee note?', 'Mohandas Gandhi', 'Muhammad Jinnah', 'Jawaharlal Nehru', 'Gopal Gokhale', 'a', 1000000)";
        try {
            stmt.executeUpdate(insertQuestions);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseSetup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Mostly used for debugging / visibility. Will show the tables in the created "PDC"
    //schema when called to ensure that tables have been created. Not strictly necessary.
    public static void listTables() {
        
        try ( Connection conn = DriverManager.getConnection(DATABASE_URL, "pdc", "pdc")) {
            DatabaseMetaData metaData = conn.getMetaData(); //get metadata from DB
            ResultSet tables = metaData.getTables(null, "PDC", null, null); // Use the schema name "PDC"

            System.out.println("Tables in the database:");
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                System.out.println(tableName); //print each table (HIGHSCORES, QUESTIONS, USERS)
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
