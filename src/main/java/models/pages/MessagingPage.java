package models.pages;


import models.group.Group;
import models.logic.Logic;


import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class MessagingPage extends Page {
    public ChatRoom currentChatRoom;
    private LinkedList<String> usersCanChat = new LinkedList<>();
    public MessagingPage() {
          this.commands = new LinkedList<>();
          this.commands.add("openChatRoom");
          this.commands.add("back to mainPage");
          this.commands.add("creatGroup");
          this.commands.add("addMembersToGroup");
          this.commands.add("sendMessageToGroup");
          this.commands.add("removeMemberFromGroup");
          this.commands.add("deleteGroup");
          this.commands.add("renameGroup");
          this.commands.add("exit");
    }

    @Override
    public LinkedList<String> getCommands() {
        return this.commands;
    }

    @Override
    public void openPage() {
             currentChatRoom = null;
        if (!Logic.getCurrentUser().getChatRooms().isEmpty())
        for (ChatRoom chatRoom:Logic.getCurrentUser().getChatRooms() ) {
            if (!chatRoom.getNotSeen().isEmpty())
                usersCanChat.addFirst(chatRoom.getUsername2());
            else usersCanChat.addLast(chatRoom.getUsername2());
        }
        for (String username:Logic.getCurrentUser().getFollowers()) {
            if (!usersCanChat.contains(username)) {
                 Logic.getCurrentUser().getChatRooms().add(new ChatRoom(Logic.getCurrentUser().getUsername(),username));
                 Logic.searchUser(username).getChatRooms().add(new ChatRoom(username,Logic.getCurrentUser().getUsername()));
                usersCanChat.add(username);
            }
        }
        for (String username:Logic.getCurrentUser().getFollowing()) {
            if (!usersCanChat.contains(username)){
                    Logic.getCurrentUser().getChatRooms().add(new ChatRoom(Logic.getCurrentUser().getUsername(),username));
                    Logic.searchUser(username).getChatRooms().add(new ChatRoom(username,Logic.getCurrentUser().getUsername()));
                    usersCanChat.add(username);
            }
        }
        LinkedHashSet<String> temp = new LinkedHashSet<>();
        temp.addAll(usersCanChat);
        usersCanChat.clear();
        usersCanChat.addAll(temp);
        uPrint();
        Logic.setCurrentPage(this);
    }
    public void openChat(String username){
        if (Logic.getCurrentUser().findChatRoom(username) != null){
            currentChatRoom = Logic.getCurrentUser().findChatRoom(username);
            Logic.getCurrentUser().findChatRoom(username).openPage();
        }else {
            System.out.println("not found");
            openPage();
        }
    }
    public void creatGroup(String groupName){
        boolean temp = true;
        for (Group group:Logic.getCurrentUser().getGroups()) {
            if (group.getName().equals(groupName)) {
                temp = false;
                break;
            }
        }
        if (temp) {
            Logic.getCurrentUser().getGroups().add(new Group(Logic.getCurrentUser().getUsername(), groupName));
            Logic.logger.info("@"+Logic.getCurrentUser()+" creat a new group("+groupName+")");
        }else System.out.println(ConsoleColors.RED+"this group exists"+ConsoleColors.RESET);
    }
    public void uPrint(){
        if (!usersCanChat.isEmpty())
        for (String username:usersCanChat) {
            System.out.println(username +" "+ Logic.searchUser(username).getLastSeen() +" "
                    +Logic.getCurrentUser().findChatRoom(username).getNotSeen().size());
        }
        System.out.println(ConsoleColors.PURPLE_BRIGHT+"--------------------------------------------------------------------------"+ConsoleColors.RESET);
        System.out.println("groups : ");
        if (!Logic.getCurrentUser().getGroups().isEmpty())
            for (Group group : Logic.getCurrentUser().getGroups()) {
                System.out.println(group.getName()+"--> members : "+group.getMembers());
            }
        System.out.println(ConsoleColors.PURPLE_BRIGHT+"--------------------------------------------------------------------------"+ConsoleColors.RESET);
    }
    public Group findGroup(String groupName){
        if (!Logic.getCurrentUser().getGroups().isEmpty())
        for (Group group:Logic.getCurrentUser().getGroups()) {
            if (group.getName().equals(groupName))return group;
        }
        return null;
    }
    public boolean addMemberToGroup(String username,String group){
        Group temp = findGroup(group);
         if (temp != null) {
             if (!temp.getMembers().contains(Logic.searchUser(username)) && Logic.searchUser(username) != Logic.getCurrentUser()  )
             temp.getMembers().add(username);
             return true;
         }return false;
    }
    public boolean removeMember(String username, String group){
        Group temp = findGroup(group);
        if (temp != null) {
            temp.getMembers().remove(Logic.searchUser(username));
            return true;
        }else return false;
    }
    public boolean deleteGroup(String group) {
        Group temp = findGroup(group);
        if (temp != null) {
            Logic.getCurrentUser().getGroups().remove(findGroup(group));
            return true;
        }return false;
    }
    public boolean renameGroup(String group,String newName){
        Group temp = findGroup(group);
        if (temp != null){
            boolean temp1 = true;
            for (Group group1:Logic.getCurrentUser().getGroups()) {
                if (group1.getName().equals(newName)) {
                    temp1 = false;
                    break;
                }
            }
            if (temp1) {
                temp.setName(newName);
                Logic.logger.info("@"+Logic.getCurrentUser()+" changed group name from "+temp.getName()+" to "+newName);
            }return true;
        }
        return false;
    }
    public boolean sendMessageToGroup(String groupName,String text){
        Group group = findGroup(groupName);
        if (group!=null){
            for (String user:group.getMembers()) {
                Logic.getCurrentUser().findChatRoom(user).newMessage(text);
            }
            return true;
        }
        return false;
    }
}
