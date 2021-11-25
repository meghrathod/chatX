package tech.meghrathod.server;

import tech.meghrathod.userMessage;

import java.io.*;
import java.net.Socket;
import java.time.Instant;

public class Conversation extends Thread {
    Socket soc;
    Conversation(Socket soc) {
        this.soc = soc;
    }
    @Override
    public void run() {
        try {

            PrintWriter nos = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    soc.getOutputStream()
                            )
                    ), true
            );


            ObjectInputStream ois = new ObjectInputStream(soc.getInputStream());
            while(pubServer.authenticate(ois)==0){
                nos.println("fail");
            }
            nos.println("success");

            pubServer.noslist.add(nos);
            userMessage msg = (userMessage) ois.readObject();
            while (!msg.message.equals("End")) {
                pubServer.q.enqueue(msg);
                System.out.println("Server Received " + msg.message + " from " + msg.username);
                msg = (userMessage) ois.readObject();
            }
            nos.println("End");
            pubServer.q.enqueue(new userMessage(msg.username, "Goodbye! I have left the chat", Instant.now()));
            pubServer.noslist.remove(nos);
            System.out.println(
                    "Connection with " + msg.username + " at " + 
                            soc.getInetAddress().getHostAddress() +
                            " Terminated");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}