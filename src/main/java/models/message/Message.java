package models.message;

import models.ConsoleColors;
import models.CurrentTimeToString;

public class Message {
    private String username;
    private String text;
    private String time;

    public Message(String username, String text) {
        this.username = username;
        this.text = text;
        this.time = CurrentTimeToString.get();
    }

    public Message() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString(){
        return ConsoleColors.CYAN +username+" : "+ConsoleColors.RESET+text +" "+ ConsoleColors.BLACK_BRIGHT+time+ConsoleColors.RESET;
    }
}
