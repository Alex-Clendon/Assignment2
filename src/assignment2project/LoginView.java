/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JTextField usernameField; //Username input
    private JPasswordField passwordField; //Password input
    private JButton loginButton;

    //Set up the elements of the panel
    public LoginView() {
        setTitle("Who Wants to Be a Millionaire - Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        //Labels for naming the username and password fields
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        loginButton = new JButton("Login");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(loginButton);

        add(panel);
    }
    //Get the user's input username
    public String getUsername() {
        return usernameField.getText();
    }
    //get the user's input password
    public String getPassword() {
        return new String(passwordField.getPassword());
    }
    
    //Setter for testing methods
    public void setUsername(String username){
        usernameField.setText(username);
    }
    //Setter for testing methods
    public void setPassword (String password){
        passwordField.setText(password);
    }
    //Set login button action listener
    public void setLoginButtonListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }
}
