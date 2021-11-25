package tech.meghrathod;


import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

public class userMessage implements Serializable{
    @Serial
    private static final long serialVersionUID = 6529685098267757690L;
    String username;
    String message;
    Instant instant;

    public userMessage(String username, String message, Instant instant) {
        this.username = username;
        this.message = message;
        this.instant = instant == null ? Instant.now() : instant;
    }
}