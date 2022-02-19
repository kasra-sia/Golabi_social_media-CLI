package models.pages;

import models.logic.Logic;
import models.tweet.TweetAndComment;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;

public class TimelinePage extends TweetsPage{
    public TimelinePage() {
        super();
        this.commands.add(3,"blockUser");
        this.commands.add(3,"muteUser");
        this.commands.add(3,"report");
    }

    @Override
    public LinkedList<String> getCommands() {
        return this.commands;
    }

    @Override
    public void openPage() {

    }
    @Override
    public void openPage(LinkedList<TweetAndComment> tweetAndComments) {

        tweetsOrCommentsNum = 0;
        currentTweetAndComment =null;
//        currentList = new LinkedList<>();
        currentList.clear();
        currentList.addAll(tweetAndComments);
        currentList.addAll(Logic.getCurrentUser().getRetweets());
        currentList.addAll(Logic.getCurrentUser().getLikedTweetsAndComments());

        HashSet<TweetAndComment> temp = new HashSet<>();
        temp .addAll(currentList);
        temp.clear();
        currentList.addAll(temp);
        try {
            System.out.println("\n" + tweetAndComments.getFirst().toString());
            currentTweetAndComment = tweetAndComments.getFirst();
        } catch (Exception e) {
            System.out.println("it's empty");
        }
        Logic.setCurrentPage(this);
    }

    public void blockUser(){
        Logic.getCurrentUser().block(Logic.searchUser(currentTweetAndComment.getUsername()));
        Logic.loadRandTweets();
        this.openPage(currentList);
    }
    public void reportTweet(){
        try {
            Logic.objectMapper.writeValue(new FileWriter("resources/Data base/reported tweets.json",true), currentTweetAndComment);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void muteUser(){
        Logic.getCurrentUser().muteUser(Logic.searchUser(currentTweetAndComment.getUsername()));
        Logic.loadRandTweets();
        this.openPage(currentList);
    }



}
