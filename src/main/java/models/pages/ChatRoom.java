package models.pages;

import models.logic.Logic;
import models.message.Message;
import java.util.LinkedList;

public class ChatRoom extends Page{
    private String username1;
    private String username2;
    private LinkedList<Message> allChats = new LinkedList<>();
    private LinkedList<Message> notSeen = new LinkedList<>();
    public ChatRoom(String username1, String username2) {
        this.username1 = username1;
        this.username2 = username2;
        this.commands = new LinkedList<>();
        this.commands.add("backToMessagingPage");
        this.commands.add("new message");
        this.commands.add("exit");

    }

    public ChatRoom() {
    }

    public String getUsername1() {
        return username1;
    }

    public void setUsername1(String username1) {
        this.username1 = username1;
    }

    public String getUsername2() {
        return username2;
    }

    public void setUsername2(String username2) {
        this.username2 = username2;
    }

    public LinkedList<Message> getNotSeen() {
        return notSeen;
    }

    public void setNotSeen(LinkedList<Message> notSeen) {
        this.notSeen = notSeen;
    }

    public LinkedList<Message> getAllChats() {
        return allChats;
    }

    public void setAllChats(LinkedList<Message> allChats) {
        this.allChats = allChats;
    }


    @Override
    public LinkedList<String> getCommands() {
        return this.commands;
    }

    @Override
    public void openPage() {
        notSeen.clear();
        allChats.forEach(chat -> System.out.println(chat.toString()));
        Logic.setCurrentPage(this);
    }
    public void newMessage(String text){
        Message message = new Message(Logic.getCurrentUser().getUsername(),text);
        allChats.add(message);
        Logic.logger.info("@"+Logic.getCurrentUser().getUsername()+" sent message to @"+username2);
        Logic.searchUser(username2).findChatRoom(Logic.getCurrentUser().getUsername()).allChats.clear();
        Logic.searchUser(username2).findChatRoom(Logic.getCurrentUser().getUsername()).allChats.addAll(allChats);
        Logic.searchUser(username2).findChatRoom(Logic.getCurrentUser().getUsername()).notSeen.add(message);
    }
}
