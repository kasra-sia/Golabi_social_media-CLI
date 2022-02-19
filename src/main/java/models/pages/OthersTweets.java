package models.pages;

import models.logic.Logic;

import java.io.File;
import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.IOException;

public class OthersTweets extends TweetsPage {
    public OthersTweets() {
        super();
        this.commands.add(3,"blockUser");
        this.commands.add(3,"muteUser");
        this.commands.add(3,"report");
    }
    public void blockUser(){
        Logic.getCurrentUser().block(Logic.searchUser(currentTweetAndComment.getUsername()));
        Logic.loadRandTweets();
        Logic.logger.info("@"+Logic.getCurrentUser()+" blocked "+Logic.searchUser(currentTweetAndComment.getUsername()));
        this.openPage(currentList);
    }
    public void reportTweet(){
        try {
            FileWriter filterWriter = new FileWriter("resources/Data base/reported tweets.json",true);
            filterWriter.write(Logic.getCurrentUser().getUsername()+" reported this tweet : \n");
            Logic.objectMapper.writeValue(filterWriter, currentTweetAndComment);
            Logic.logger.info("@"+Logic.getCurrentUser()+" reported "+Logic.searchUser(currentTweetAndComment.getUsername()));
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
