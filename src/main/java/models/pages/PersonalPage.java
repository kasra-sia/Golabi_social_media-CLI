package models.pages;

import models.logic.Logic;
import models.tweet.TweetAndComment;

import java.util.LinkedList;

public class PersonalPage extends Page {
    public NotificationsPage notificationsPage;
    public RequestsPage requestsPage;
    public EditPage editPage;
    public MyTweets myTweets;
    public MyContactsPage myContactsPage;

    public PersonalPage() {
        commands = new LinkedList<>();
        notificationsPage = new NotificationsPage();
        requestsPage = new RequestsPage();
        editPage = new EditPage();
        myTweets = new MyTweets();
        myContactsPage = new MyContactsPage();
        this.commands.add("notifications");
        this.commands.add("newTweet");
        this.commands.add("myTweets");
        this.commands.add("myFollowings");
        this.commands.add("myFollowers");
        this.commands.add("editPage");
        this.commands.add("info");
        this.commands.add("back to mainPage");
        this.commands.add("showSavedTweets");
        this.commands.add("exit");

    }

    @Override
    public LinkedList<String> getCommands() {
        return this.commands;
    }

    public void addTweet(TweetAndComment tweetAndComment) {
        this.user.getTweets().add(tweetAndComment);
    }

    public void deleteTweet(TweetAndComment tweetAndComment) {
        this.user.getTweets().remove(tweetAndComment);
    }

    public void showTweets() {
    }

    public void editTweet(TweetAndComment tweetAndComment) {
    }

    @Override
    public void openPage() {
        Logic.setCurrentPage(this);
    }


}
