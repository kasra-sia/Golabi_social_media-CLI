package models.tweet;

import models.CurrentTimeToString;
import models.like.Like;
import models.User.User;

import java.util.LinkedList;

public class TweetAndComment {
    private String text;
    private String username;
    private LinkedList<TweetAndComment> replies = new LinkedList<>();
    private String time;
    private LinkedList<Like> likes = new LinkedList<>();

 public TweetAndComment(String text , User user){
     likes = new LinkedList<>();
         this.time = CurrentTimeToString.get();
         this.username = user.getUsername();
         this.text =text;
 }

    public TweetAndComment() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LinkedList<TweetAndComment> getReplies() {
        return replies;
    }

    public void setReplies(LinkedList<TweetAndComment> replies) {
        this.replies = replies;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public LinkedList<Like> getLikes() {
        return likes;
    }

    public void setLikes(LinkedList<Like> likes) {
        this.likes = likes;
    }
    public void addLike(String username){
     this.likes.add(new Like(username));
    }
    @Override
    public String toString(){

     return time + " @" + username + " said : " + text ;
    }
}
