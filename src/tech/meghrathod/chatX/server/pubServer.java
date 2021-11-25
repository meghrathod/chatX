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
            csvReader = new BufferedReader(new FileReader("src/tech/meghrathod/chatX/server/users.csv"));
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

    public static int authenticate(ObjectInputStream ois){
        try {
            String received = (String) ois.readObject();
            String[] separate = received.split(",");
            User check = new User(separate[0],separate[1] );
            if(pubServer.checkUser(check.username, check.password) == 0){
                return 0;
            } else {
                return 1;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }



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
                System.out.println(ex);
            }
        }
        return al.remove(0);
    }
}