package models.pages;

import models.logic.Logic;
import models.tweet.TweetAndComment;

import java.util.LinkedList;

public class TweetsPage extends Page{
    protected int tweetsOrCommentsNum = 0;
    protected TweetAndComment currentTweetAndComment;
    protected LinkedList<TweetAndComment> currentList = new LinkedList<>();
    public TweetsPage(){
        this.commands = new LinkedList<>();
        this.commands.add("next");
        this.commands.add("previous");
        this.commands.add("like");
        this.commands.add("disLike");
        this.commands.add("addComment");
        this.commands.add("openComments");
        this.commands.add("saveThisTweet");
        this.commands.add("forward");
        this.commands.add("back");
        this.commands.add("exit");
    }
    @Override
    public LinkedList<String> getCommands() {
        return this.commands;
    }
    @Override
    public void openPage(){}

    public TweetAndComment getCurrentTweetAndComment() {
        return currentTweetAndComment;
    }

    public void openPage(LinkedList<TweetAndComment> tweetAndComments) {
        tweetsOrCommentsNum = 0;
        currentTweetAndComment =null;
        currentList.clear();
        currentList.addAll(tweetAndComments) ;
        try {
            System.out.println("\n" + tweetAndComments.getFirst().toString());
            currentTweetAndComment = tweetAndComments.getFirst();
        } catch (Exception e) {
            System.out.println("it's empty");
        }
        Logic.setCurrentPage(this);
    }
    public void nextTweetOrComment() {
        if (!currentList.isEmpty()) {
            try {
                tweetsOrCommentsNum++;
                currentTweetAndComment = currentList.get(tweetsOrCommentsNum);
                System.out.println("\n" + currentList.get(tweetsOrCommentsNum).toString());
            } catch (Exception e) {
                System.out.println(ConsoleColors.RED + "there is no next" + ConsoleColors.RESET);
//            reqNum--;
                previousTweetOrComment();
            }
        } else System.out.println("it's empty");

    }
    public void previousTweetOrComment() {
        if (!currentList.isEmpty()) {
            try {
                tweetsOrCommentsNum--;
                currentTweetAndComment = currentList.get(tweetsOrCommentsNum);
                System.out.println("\n" + currentList.get(tweetsOrCommentsNum).toString());
            } catch (Exception e) {
                System.out.println(ConsoleColors.RED + "there is no previous" + ConsoleColors.RESET);
                nextTweetOrComment();
//            reqNum++;
            }
        } else System.out.println("it's empty");
    }
    public void like(){
        Logic.getCurrentUser().likeTweetAndComment(currentTweetAndComment);
        Logic.logger.info("@"+Logic.getCurrentUser()+" liked "+currentTweetAndComment.getUsername()+"'s tweet");
    }
    public void disLike(){
        Logic.getCurrentUser().disLikeTweetAndComment(currentTweetAndComment);
        Logic.logger.info("@"+Logic.getCurrentUser()+" disliked "+currentTweetAndComment.getUsername()+"'s tweet");
    }
    public void reTweet(){
        Logic.getCurrentUser().reTweet(currentTweetAndComment);
        Logic.logger.info("@"+Logic.getCurrentUser()+" retweeted " +currentTweetAndComment.getUsername()+"'s tweet");
    }
    public void addComment(String text){
        Logic.getCurrentUser().addComment(text,currentTweetAndComment);
        Logic.logger.info("@"+Logic.getCurrentUser()+" added comment to " +currentTweetAndComment.getUsername()+"'s tweet");
//        currentTweetAndComment.getReplies().add()
    }
    public void openComments(){
        tweetsOrCommentsNum =0;
        openPage(currentTweetAndComment.getReplies());
    }
    public void saveTweet(){
        if (!Logic.getCurrentUser().getSavedTweets().contains(currentTweetAndComment))
        Logic.getCurrentUser().getSavedTweets().add(currentTweetAndComment);
        Logic.logger.info("@"+Logic.getCurrentUser()+" saved " +currentTweetAndComment.getUsername()+"'s tweet");
    }

}
