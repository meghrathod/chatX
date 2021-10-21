package tech.meghrathod.chatX.client;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws Exception {
        System.out.println("Client Signing on");
        try {
            Socket soc = new Socket("127.0.0.1", 9085);
            BufferedReader nis = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            ObjectOutputStream oos = new ObjectOutputStream(soc.getOutputStream());
            LoginWindow user = new LoginWindow();
            String loggedInName = user.login();
            ChatWindow chat = new ChatWindow(nis, loggedInName, oos);
            chat.start();
        } catch (ConnectException e) {
            System.out.println(e);
            
        }
    }

}