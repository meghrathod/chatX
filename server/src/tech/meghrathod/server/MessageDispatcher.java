package tech.meghrathod.server;

import tech.meghrathod.userMessage;

import java.io.PrintWriter;

public class MessageDispatcher extends Thread {
    @Override
    public void run() {
        while (true) {
            try {
                userMessage msg = pubServer.q.dequeue();
                for (PrintWriter o : pubServer.noslist) {
                    o.println("<b>" + msg.username + ": </b>" + msg.message + "<br>");
                }
            } catch (Exception e) {
            }
        }
    }
}