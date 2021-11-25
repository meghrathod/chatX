package tech.meghrathod;


import java.io.Serial;
import java.time.Instant;
import java.io.Serializable;

public class userMessage implements Serializable{
    @Serial
    private static final long serialVersionUID = 6529685098267757690L;
    public String message;
    public String username;
    Instant instant;

    public userMessage(String username, String message, Instant instant) {
        this.username = username;
        this.message = message;
        this.instant = instant == null ? Instant.now() : instant;
    }
}