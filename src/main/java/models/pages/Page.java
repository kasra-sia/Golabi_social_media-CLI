package models.pages;

import models.Main;
import models.User.User;
import models.logic.Logic;

import java.util.LinkedList;

public abstract class Page {
    protected LinkedList<String> commands;
    protected Logic logic = Main.logic;
    protected User user;
    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public abstract LinkedList<String> getCommands() ;

    public abstract void openPage();
}
