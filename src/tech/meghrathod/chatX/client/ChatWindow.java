package tech.meghrathod.chatX.client;

import tech.meghrathod.chatX.server.userMessage;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import java.time.Instant;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ChatWindow extends Thread implements WindowListener  {
    JFrame f1;
    BufferedReader nis;
    ObjectOutputStream oos;
    ChatArea ta;
    JTextField tf;
    String username;

    ChatWindow(BufferedReader nis, String username, ObjectOutputStream oos) {

        //Create the chat window
        this.nis = nis;
        this.oos = oos;
        this.username = username;
        f1 = new JFrame("chatX ");
        JButton b1 = new JButton("Send");
        ta = new ChatArea();
        ta.setContentType("text/html");
        ta.setText("<b>Hello, " + username +"!</b><br>");
        ta.setEditable(false);
        tf = new JTextField(20);
        JPanel p1 = new JPanel();
        p1.add(tf);
        p1.add(b1);
        f1.add(ta);
        f1.add(BorderLayout.SOUTH, p1);
        f1.setSize(400, 400);
        System.out.println("Chat Window is loaded");
        
        ChatListener l1 = new ChatListener(tf, oos, username);
        b1.addActionListener(l1);
        tf.addActionListener(l1);

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


        f1.addWindowListener(adapter);
        f1.setVisible(true);

        

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



    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

}

class ChatArea extends JTextPane{
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

class ChatListener implements ActionListener {
    JTextField tf;
    String username;
    ObjectOutputStream oos;

    ChatListener(JTextField tf, ObjectOutputStream oos, String username) {
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
