package models.User;

import models.DateToString;
import models.group.Group;
import models.like.Like;
import models.pages.*;
import models.logic.Logic;
import models.tweet.TweetAndComment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class User {
    static long lastID = 1;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String bio = "";
    private Date birthday;
    private long phoneNumber = -1;
    private long ID;

    private boolean isPrivate;
    private boolean isBirthdayPrivate;
    private boolean isEmailPrivate;
    private boolean isPhoneNumberPrivate;
    private boolean isActive;
    private LinkedList<String> following = new LinkedList<>();
    private LinkedList<String> followers = new LinkedList<>();
    private LinkedList<String> blacklist = new LinkedList<>();
    private LinkedList<String> muteList = new LinkedList<>();
    private LinkedList<TweetAndComment> likedTweetsAndComments = new LinkedList<>();
    private LinkedList<TweetAndComment> tweets = new LinkedList<>();
    private LinkedList<TweetAndComment> retweets = new LinkedList<>();
    private LinkedList<ChatRoom> chatRooms = new LinkedList<>();
    private LinkedList <Group> groups  = new LinkedList<>();
    private LinkedList<String> requestList = new LinkedList<>();
    private LinkedHashMap<String,String> sentRequests = new LinkedHashMap<>();
    private LinkedList<String> systemNotifications = new LinkedList<>();
    private LinkedList<TweetAndComment> savedTweets = new LinkedList<>();
    private boolean isLastSeenRecently;
    private String lastSeen;

    public User(String firstName, String lastName, String username, String password, String email) {
        this.ID = lastID;
        lastID++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public void showInfo() {
        System.out.println("ID :" + ID);
        System.out.println(ConsoleColors.PURPLE + "----------------------------------------------" + ConsoleColors.RESET);
        System.out.println("firstName :" + firstName);
        System.out.println(ConsoleColors.PURPLE + "----------------------------------------------" + ConsoleColors.RESET);
        System.out.println("lastName :" + lastName);
        System.out.println(ConsoleColors.PURPLE + "----------------------------------------------" + ConsoleColors.RESET);
        System.out.println("username :" + username);
        System.out.println(ConsoleColors.PURPLE + "----------------------------------------------" + ConsoleColors.RESET);
        if (phoneNumber != -1)
            System.out.println("phoneNumber :" + phoneNumber);
        else System.out.println("phoneNumber : null");
        System.out.println(ConsoleColors.PURPLE + "----------------------------------------------" + ConsoleColors.RESET);
        if (birthday != null)
            System.out.println("birthdate :" + DateToString.convert(birthday));
        else System.out.println("birthdate : null");
        System.out.println(ConsoleColors.PURPLE + "----------------------------------------------" + ConsoleColors.RESET);
        System.out.println("email :" + email);
        System.out.println(ConsoleColors.PURPLE + "----------------------------------------------" + ConsoleColors.RESET);
        if (!bio.equals(null))
            System.out.println("bio :" + bio);
        else System.out.println("bio : null");
        System.out.println(ConsoleColors.PURPLE + "----------------------------------------------" + ConsoleColors.RESET);
        System.out.println("followers :\n");
        if (followers != null)
            followers.forEach(System.out::println);
        System.out.println(ConsoleColors.PURPLE + "----------------------------------------------" + ConsoleColors.RESET);
        System.out.println("following :\n");
        if (following != null)
            following.forEach(System.out::println);
        System.out.println(ConsoleColors.PURPLE + "----------------------------------------------" + ConsoleColors.RESET);
        System.out.println("blacklist :\n");
        if (blacklist != null)
            blacklist.forEach(System.out::println);
        System.out.println(ConsoleColors.PURPLE + "----------------------------------------------" + ConsoleColors.RESET);
        if (isPrivate) {
            System.out.println("this page is private");
        } else System.out.println("this page is public");
        System.out.println(ConsoleColors.PURPLE + "----------------------------------------------" + ConsoleColors.RESET);
    }
    public void sendRequestTo(User user1) {
        user1.getRequestList().add(this.username);
        this.sentRequests.put(user1.getUsername(),"sent");
    }
    public boolean isPrivate() {
        return isPrivate;
    }
    public LinkedList<TweetAndComment> getTweets() {
        return tweets;
    }
    public boolean isActive() {
        return isActive;
    }
    public String getLastSeen() {
        return lastSeen;
    }
    public void setLastSeen() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        this.lastSeen = dateFormat.format(cal.getTime());
    }
    public String getBio() {
        return bio;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public long getID() {
        return ID;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setBirthdayPrivate(boolean birthdayPrivate) {
        isBirthdayPrivate = birthdayPrivate;
    }
    public boolean isBirthdayPrivate() {
        return isBirthdayPrivate;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public void setEmailPrivate(boolean emailPrivate) {
        isEmailPrivate = emailPrivate;
    }
    public boolean isEmailPrivate() {
        return isEmailPrivate;
    }
    public boolean isPhoneNumberPrivate() {
        return isPhoneNumberPrivate;
    }
    public long getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setPhoneNumberPrivate(boolean phoneNumberPrivate) {
        isPhoneNumberPrivate = phoneNumberPrivate;
    }
    public LinkedList<String> getRequestList() {
        return requestList;
    }
    public void setRequestList(LinkedList<String> requestList) {
        this.requestList = requestList;
    }
    public LinkedList<String> getSystemNotifications() {
        return systemNotifications;
    }
    public boolean isLastSeenRecently() {
        return isLastSeenRecently;
    }
    public void setLastSeenRecently(boolean lastSeenRecently) {
        isLastSeenRecently = lastSeenRecently;
    }
    public LinkedList<String> getMuteList() {
        return muteList;
    }
    public void setMuteList(LinkedList<String> muteList) {
        this.muteList = muteList;
    }
    public LinkedList<ChatRoom> getChatRooms() {
        return chatRooms;
    }
    public void setChatRooms(LinkedList<ChatRoom> chatRooms) {
        this.chatRooms = chatRooms;
    }
    public LinkedList<TweetAndComment> getLikedTweetsAndComments() {
        return likedTweetsAndComments;
    }
    public void setLikedTweetsAndComments(LinkedList<TweetAndComment> likedTweetsAndComments) {
        this.likedTweetsAndComments = likedTweetsAndComments;
    }
    public LinkedList<TweetAndComment> getRetweets() {
        return retweets;
    }
    public void setRetweets(LinkedList<TweetAndComment> retweets) {
        this.retweets = retweets;
    }
    public LinkedList<Group> getGroups() {
        return groups;
    }
    public void setGroups(LinkedList<Group> groups) {
        this.groups = groups;
    }
    public LinkedList<TweetAndComment> getSavedTweets() {
        return savedTweets;
    }
    public void setSavedTweets(LinkedList<TweetAndComment> savedTweets) {
        this.savedTweets = savedTweets;
    }
    //*********************************************************************
    public User() {
    }

    public static void setLastID(long lastID) {
        User.lastID = lastID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setFollowing(LinkedList<String> following) {
        this.following = following;
    }

    public void setFollowers(LinkedList<String> followers) {
        this.followers = followers;
    }

    public void setBlacklist(LinkedList<String> blacklist) {
        this.blacklist = blacklist;
    }

    public void setTweets(LinkedList<TweetAndComment> tweetAndComments) {
        this.tweets = tweetAndComments;
    }

    public static long getLastID() {
        return lastID;
    }

    public LinkedList<String> getFollowing() {
        return following;
    }

    public LinkedList<String> getFollowers() {
        return followers;
    }

    public LinkedList<String> getBlacklist() {
        return blacklist;
    }
    public LinkedHashMap<String, String> getSentRequests() {
        return sentRequests;
    }
    public void setSentRequests(LinkedHashMap<String, String> sentRequests) {
        this.sentRequests = sentRequests;
    }

    public void setSystemNotifications(LinkedList<String> systemNotifications) {
        this.systemNotifications = systemNotifications;
    }
    public void setLastSeen(String lastSeen) {
        this.lastSeen = lastSeen;
    }
    public void follow(User user1) {
        if (!user1.username.equals(this.username)) {
            if (!user1.isPrivate) {
                if (!this.following.contains(user1.username)) {
                    this.following.add(user1.username);
                    user1.getFollowers().add(this.username);
                    user1.getSystemNotifications().add(this.username+ " followed you");
                    System.out.println("you are following " + user1.getUsername() + " since now");
                    Logic.logger.info("@"+Logic.getCurrentUser().getUsername()+" followed "+"@"+user1.getUsername());
                } else System.out.println("you have follow " + user1.username + " already");
            } else {
                if (!this.following.contains(user1.username)) {
                    if (!user1.requestList.contains(this.username)) {
                        this.sendRequestTo(user1);
                        System.out.println("your request has been sent to " + user1.getUsername());
                        Logic.logger.info("@"+Logic.getCurrentUser().getUsername()+" sent request to follow "+"@"+user1.getUsername());
                    } else System.out.println("you have already sent request to " + user1.getUsername());
                } else System.out.println("you have follow " + user1.username + " already");
            }
        } else System.out.println(ConsoleColors.RED + "you can't follow your self !!" + ConsoleColors.RESET);
    }
    public void Unfollow(User user1) {
        if (this.getFollowing().contains(user1.username)) {
            this.getFollowing().remove(user1.username);
            user1.getFollowers().remove(this.username);
            user1.getSystemNotifications().add(this.username+" Unfollowed you");
            System.out.println(user1.username + " has been Unfollowed ");
            Logic.logger.info("@"+Logic.getCurrentUser().getUsername()+" unfollowed "+"@"+user1.getUsername());
        } else System.out.println(user1.username + " is not in your followingsList");
    }
    public void block(User user1) {
        if (this.getFollowing().contains(user1.username)) {
            this.getFollowing().remove(user1.username);
            user1.getFollowers().remove(this.username);
        }
        if (user1.getFollowing().contains(this.username)) {
            user1.getFollowing().remove(this.username);
            this.getFollowers().remove(user1.username);
        }
        if (!blacklist.contains(user1.username)) {
            this.blacklist.add(user1.username);
            Logic.logger.info("@" + Logic.getCurrentUser().getUsername() + " blocked " + "@" + user1.getUsername());
        }
    }
    public void unBlock(User user1) {
        if (blacklist.contains(user1.username)) {
            this.blacklist.remove(user1.username);
            Logic.logger.info("@"+Logic.getCurrentUser().getUsername()+" unBlocked "+"@"+user1.getUsername());
        }
    }
    public void deleteAccount() {
        Logic.getUsers().remove(this);
        try {
            this.following.forEach(user1 -> { Logic.searchUser(user1).followers.remove(this.getUsername());});
        } catch (Exception ignore) {
        }
        try {
            this.followers.forEach(user1 -> { Logic.searchUser(user1).following.remove(this.getUsername());});
        } catch (Exception ignore) {}
    }
    public void addTweet(String text){
        TweetAndComment tweetAndComment = new TweetAndComment(text,this);
        this.tweets.add(tweetAndComment);
        System.out.println(tweetAndComment.toString());
    }
    public void addComment(String text,TweetAndComment tweetAndComment){
        TweetAndComment reply = new TweetAndComment(text,this);
        tweetAndComment.getReplies().add(reply);
        System.out.println(tweetAndComment.toString());
    }
    public void likeTweetAndComment(TweetAndComment tweetAndComment) {
        boolean temp = true;
        if (!tweetAndComment.getLikes().isEmpty())
            for (Like like : tweetAndComment.getLikes()) {
                if (like.getUsername().equals(this.username))
                    temp = false;
            }
        if (temp) {
            tweetAndComment.addLike(this.username);
            this.likedTweetsAndComments.add(tweetAndComment);
        }
    }
    public void disLikeTweetAndComment(TweetAndComment tweetAndComment){
//        Like like1 = null;
        for (Like like: tweetAndComment.getLikes()) {
            if (like.getUsername().equals(this.getUsername()))
                tweetAndComment.getLikes().remove(like);
            this.likedTweetsAndComments.add(tweetAndComment);
        }
    }
    public void reTweet(TweetAndComment tweetAndComment){
        this.tweets.add(tweetAndComment);
        this.retweets.add(tweetAndComment);
    }
    public void muteUser(User user1){
        if (muteList.contains(user1.username))
            this.muteList.add(user1.username);
    }
    public ChatRoom findChatRoom(String username){
        for (ChatRoom chatRoom:chatRooms) {
            if (chatRoom.getUsername2().equals(username))
                return chatRoom;
        }
        return null;
    }

    //*****************************************************************************
}
