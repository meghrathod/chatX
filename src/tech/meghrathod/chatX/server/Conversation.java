package tech.meghrathod.chatX.server;

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
        //     BufferedReader nis = new BufferedReader(
        //             new InputStreamReader(
        //                     soc.getInputStream()
        //             )
        //     );
            PrintWriter nos = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    soc.getOutputStream()
                            )
                    ), true
            );


            ObjectInputStream ois = new ObjectInputStream(soc.getInputStream()); 
        //     ObjectOutputStream oos = new ObjectOutputStream(soc.getOutputStream());


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
        }
    }
}