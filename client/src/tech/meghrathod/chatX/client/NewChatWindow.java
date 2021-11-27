package tech.meghrathod.chatX.client;


import tech.meghrathod.userMessage;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.Instant;

public class NewChatWindow extends JFrame {

    BufferedReader nis;
    ObjectOutputStream oos;
    NewChatArea ta;
    JTextField tf;
    static String username;
    public ChatHandler ch;


    NewChatWindow(BufferedReader input, String user, ObjectOutputStream output) {
        nis = input;
        oos = output;
        username = user;
        initComponents();
        new ChatHandler(nis, username, oos, ta).start();
    }

    private void initComponents() {
        logoutBtn = new JButton();
        status = new JLabel();
        jScrollPane1 = new JScrollPane();
        ta = new NewChatArea();
        ta.setContentType("text/html");
        tf = new JTextField();
        send = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 204, 204));

        NewChatListener l1 = new NewChatListener(tf, oos, username);

        logoutBtn.setFont(new java.awt.Font("Rockwell", Font.PLAIN, 14)); // NOI18N
        logoutBtn.setText("Logout");
        logoutBtn.addActionListener(e -> {
            try {
                oos.writeObject(new userMessage(username, "End", Instant.now()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        status.setFont(new java.awt.Font("Rockwell", Font.PLAIN, 14)); // NOI18N
        status.setForeground(new java.awt.Color(0, 204, 0));
        status.setText("chatX: Connected ");

        ta.setEditable(false);
        ta.setFont(new java.awt.Font("Segoe UI Emoji", Font.PLAIN, 12)); // NOI18N
        jScrollPane1.setViewportView(ta);

        tf.setFont(new java.awt.Font("Segoe UI Emoji", Font.PLAIN, 12)); // NOI18N
        tf.setToolTipText("Type your message here");

        send.setFont(new java.awt.Font("Rockwell", Font.PLAIN, 14)); // NOI18N
        send.setText("Send");

        send.addActionListener(l1);
        tf.addActionListener(l1);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(21, 21, 21)
                                                .addComponent(status)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(logoutBtn))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jScrollPane1))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(tf, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(send)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(logoutBtn)
                                        .addComponent(status))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 329, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(tf, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(send))
                                .addGap(14, 14, 14))
        );

        WindowAdapter adapter = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    oos.writeObject(new userMessage(username, "End", Instant.now()));
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                try {
                    System.out.println("Window Closed");

                    Thread.sleep(1000);
                    System.exit(0);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        };

        pack();
    }


    // Variables declaration - do not modify
    private JLabel status;
    private JScrollPane jScrollPane1;
    private JButton logoutBtn;
    private JButton send;


    // End of variables declaration
}

class NewChatListener implements ActionListener {
    JTextField tf;
    String username;
    ObjectOutputStream oos;

    NewChatListener(JTextField tf, ObjectOutputStream oos, String username) {
        this.tf = tf;
        this.oos = oos;
        this.username = username;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String str = tf.getText();
        try {
            oos.writeObject(new userMessage(username, str, Instant.now()));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        tf.setText("");
    }
}




class ChatHandler extends Thread{
    BufferedReader nis;
    ObjectOutputStream oos;
    String username;
    NewChatArea ta;


    ChatHandler(BufferedReader nis, String username, ObjectOutputStream oos, NewChatArea ta){
        this.nis = nis;
        this.oos = oos;
        this.username = username;
        this.ta = ta;
    }

    @Override
    public void run() {
        String str;
        try {
            str = nis.readLine();
            while (!str.equals("End")) {
                ta.append(str);
                str = nis.readLine();
            }
            ta.append(username + " Signing Off");
            oos.writeObject(new userMessage(username, username + "has left the chat", Instant.now()));
            Thread.sleep(1000);
            System.exit(0);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class NewChatArea extends JTextPane{
    void append(String text){

        HTMLDocument doc = (HTMLDocument) this.getStyledDocument();
        try {
            doc.insertAfterEnd(doc.getCharacterElement(doc.getLength()), text);
            this.setDocument(doc);
        } catch (BadLocationException | IOException e) {
            e.printStackTrace();
        }
    }
}

