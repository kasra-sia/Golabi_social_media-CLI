package models.group;

import models.User.User;

import java.util.LinkedList;

public class Group {
    private String user1;
    private String name;
    private LinkedList<String> members = new LinkedList<>();
    public Group(String username,String name){
        this.user1 = username;
        this.name = name;
    }

    public Group() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public LinkedList<String> getMembers() {
        return members;
    }

    public void setMembers(LinkedList<String> members) {
        this.members = members;
    }

}
