package tech.meghrathod.chatX.server;


import java.time.Instant;
import java.io.Serializable;

public class userMessage implements Serializable{
    String username;
    String message;
    Instant instant;

    public userMessage(String username, String message, Instant instant) {
        this.username = username;
        this.message = message;
        this.instant = instant == null ? Instant.now() : instant;
    }
}