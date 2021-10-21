package tech.meghrathod.chatX.server;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class pubServer {
    public static MessageQueue<userMessage> q = new MessageQueue<>();
    public static ArrayList<PrintWriter> noslist = new ArrayList<>();
    public static ArrayList<User> Userdb = new ArrayList<User>();

    public static void main(String[] args) throws Exception {
        System.out.println("Server signing On");
        ServerSocket ss = new ServerSocket(9085);
        MessageDispatcher md = new MessageDispatcher();
        md.setDaemon(true);
        md.start();
        for (int i = 0; i < 10; i++)
        {
            Socket soc = ss.accept();
            System.out.println("Connection established");
            Conversation c = new Conversation(soc);
            c.start();
        }
        System.out.println("Server signing Off");
    }

    public static int checkUser(String enteredUsername, String enteredPassword) {
        int exists = 0;

        String row;

        BufferedReader csvReader;
        try {
            csvReader = new BufferedReader(new FileReader("D:\\CSE\\Projects\\chatX\\src\\tech\\meghrathod\\chatX\\server\\users.csv"));
            while ((row = csvReader.readLine()) != null) {
                String[] user = row.split(",");
                Userdb.add(new User(user[0], user[1]));
            }
            csvReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (User user : Userdb) {
            if (user.username.equals(enteredUsername) && user.password.equals(enteredPassword)) {
                exists = 1;
            }
        }
        return exists;
    }
}


class MessageQueue<T> {
    ArrayList<T> al = new ArrayList<>();
    synchronized public void enqueue(T i) {
        al.add(i);
        notify();
    }
    synchronized public T dequeue() {
        if (al.isEmpty()) {
            try {
                wait();
            } catch (Exception ex) {
            }
        }
        return al.remove(0);
    }
    // synchronized public void print() {
    //     for (T i : al) {
    //         System.out.println(i);
    //     }
    // }
    // @Override
    // synchronized public String toString() {
    //     String str = null;
    //     for (T s : al) {
    //         str += "::" + s;
    //     }
    //     return str;
    // }
}