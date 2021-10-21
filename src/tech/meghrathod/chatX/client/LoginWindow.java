package tech.meghrathod.chatX.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginWindow implements ActionListener{

    static String currentUser;

    JTextField userText;
    JPasswordField passText;
    JLabel heading;
    JFrame loginWindow;

    public LoginWindow() {
        loginWindow = new JFrame("Login");

        JPanel login = new JPanel();
        loginWindow.setSize(300, 170);

        JPanel username = new JPanel();
        username.setLayout(new BoxLayout(username, BoxLayout.X_AXIS));
        JLabel userLabel = new JLabel();
        userLabel.setText("Username:  ");
        userText = new JTextField(15);
        username.add(BorderLayout.WEST, userLabel);
        username.add(BorderLayout.EAST, userText);

        JPanel password = new JPanel();
        password.setLayout(new BoxLayout(password, BoxLayout.X_AXIS));
        JLabel passLabel = new JLabel();
        passLabel.setText("Password:   ");
        passText = new JPasswordField(15);
        password.add(BorderLayout.WEST, passLabel);
        password.add(BorderLayout.EAST, passText);

        JPanel credentials = new JPanel();
        credentials.setLayout(new BoxLayout(credentials, BoxLayout.Y_AXIS));
        credentials.add(username);
        credentials.add(password);

        JButton submit = new JButton("Login");
        submit.setSize(300, 0);
        heading = new JLabel();
        heading.setText("Enter credentials");

        userText.addActionListener(this);
        passText.addActionListener(this);
        submit.addActionListener(this);

        // login.setLayout(new BoxLayout(login, BoxLayout.Y_AXIS));
        login.add(heading, BorderLayout.NORTH);
        login.add(credentials, BorderLayout.CENTER);
        login.add(submit, BorderLayout.SOUTH);
        loginWindow.add(login, BorderLayout.CENTER);
        
    }

    synchronized public String login(){
        loginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginWindow.setVisible(true);
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return currentUser;
        
    }

    synchronized public void authenticateUser() {
        notify(); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = userText.getText();
        String pass = String.valueOf(passText.getPassword());

        if(tech.meghrathod.chatX.server.pubServer.checkUser(user, pass) == 1){
            authenticateUser();
            currentUser = user;
            loginWindow.dispose();
        } else {
            passText.setText("");
            heading.setText("<html><font color='red'>Invalid Credentials!</font></html>");
        }
    }


}
