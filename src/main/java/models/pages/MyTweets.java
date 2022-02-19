package models.pages;

import models.logic.Logic;

public class MyTweets extends TweetsPage {
    public MyTweets() {
        super();
        this.commands.add(3,"deleteTweet");
    }

    public void deleteTweet() {
            Logic.getCurrentUser().getTweets().remove(this.currentTweetAndComment);
        Logic.logger.info("@"+Logic.getCurrentUser()+" deleted a tweet");
            this.openPage(Logic.getCurrentUser().getTweets());
    }
}
