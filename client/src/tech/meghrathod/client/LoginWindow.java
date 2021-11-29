
package tech.meghrathod.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class LoginWindow extends JFrame  implements ActionListener {

    static String currentUser;
    BufferedReader nis;
    ObjectOutputStream oos;
    public LoginWindow(BufferedReader nis, ObjectOutputStream oos) {
        this.nis = nis;
        this.oos = oos;
        initComponents();

    }
    private void initComponents() {

        JLabel jLabel1 = new JLabel();
        JPanel jPanel1 = new JPanel();
        JLabel jLabel2 = new JLabel();
        JPanel jPanel2 = new JPanel();
        JPanel userPanel = new JPanel();
        JLabel userLabel = new JLabel();
        userText = new JTextField();
        JPanel passPanel = new JPanel();
        JLabel passLabel = new JLabel();
        passText = new JPasswordField();
        JButton submit = new JButton();
        heading = new JLabel();

        jLabel1.setFont(new java.awt.Font("Rockwell", Font.PLAIN, 14)); // NOI18N
        jLabel1.setText("Welcome to chatX");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 153, 255));

        jLabel2.setFont(new java.awt.Font("Rockwell", Font.PLAIN, 18)); // NOI18N
        jLabel2.setText("Welcome to chatX");

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(113, 113, 113)
                                .addComponent(jLabel2)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addContainerGap())
        );

        userLabel.setFont(new java.awt.Font("Rockwell", Font.PLAIN, 14)); // NOI18N
        userLabel.setText("Username:");


        userText.setFont(new java.awt.Font("SansSerif", Font.PLAIN, 13)); // NOI18N
        userText.setToolTipText("Username");
        userText.addActionListener(this);

        GroupLayout userPanelLayout = new GroupLayout(userPanel);
        userPanel.setLayout(userPanelLayout);
        userPanelLayout.setHorizontalGroup(
                userPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(userPanelLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(userLabel, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(userPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(GroupLayout.Alignment.TRAILING, userPanelLayout.createSequentialGroup()
                                        .addContainerGap(121, Short.MAX_VALUE)
                                        .addComponent(userText, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap()))
        );
        userPanelLayout.setVerticalGroup(
                userPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(userLabel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                        .addGroup(userPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(GroupLayout.Alignment.TRAILING, userPanelLayout.createSequentialGroup()
                                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(userText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        passLabel.setFont(new java.awt.Font("Rockwell", Font.PLAIN, 14)); // NOI18N
        passLabel.setText("Password:");



        passText.setFont(new java.awt.Font("SansSerif", Font.PLAIN, 13)); // NOI18N
        passText.setToolTipText("Password");
        passText.addActionListener(this);

        GroupLayout passPanelLayout = new GroupLayout(passPanel);
        passPanel.setLayout(passPanelLayout);
        passPanelLayout.setHorizontalGroup(
                passPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(passPanelLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(passLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(passText, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        passPanelLayout.setVerticalGroup(
                passPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(passLabel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(GroupLayout.Alignment.TRAILING, passPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(passText)
                                .addContainerGap())
        );

        submit.setFont(new java.awt.Font("Rockwell", Font.PLAIN, 14)); // NOI18N
        submit.setText("Login");
        submit.addActionListener(this);

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(userPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(passPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(submit, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(userPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(passPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(submit, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        heading.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14)); // NOI18N
        heading.setText("Enter your credentials");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                                .addGap(134, 134, 134)
                                .addComponent(heading)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(heading)
                                .addGap(2, 2, 2)
                                .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
    }

    synchronized public String login(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
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


    // Variables declaration - do not modify
    private JLabel heading;
    private JPasswordField passText;
    private JTextField userText;

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = userText.getText();
        String pass = String.valueOf(passText.getPassword());

        try {
            oos.writeObject(""+user+","+pass);
            if(nis.readLine().equals("success")){
                authenticateUser();
                currentUser = user;
                this.dispose();
            } else {
                passText.setText("");
                heading.setText("<html><font color='red'>Invalid Credentials!</font></html>");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    // End of variables declaration
}
