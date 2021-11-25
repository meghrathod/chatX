package tech.meghrathod.chatX.client;

import java.io.Serializable;

public class User implements Serializable {
    String username;
    String password;

    User(String username, String password) {
        this.username = username;
        this.password = password;
    }

}

