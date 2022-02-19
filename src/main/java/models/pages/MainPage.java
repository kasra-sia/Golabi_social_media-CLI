package models.pages;

import models.logic.Logic;

import java.util.LinkedList;

public class MainPage extends Page{

    public MainPage() {
        commands = new LinkedList<>();
        this.commands.add("explorerPage");
        this.commands.add("messagingPage");
        this.commands.add("personalPage");
        this.commands.add("timelinePage");
        this.commands.add("settingsPage");
        this.commands.add("exit");

    }

    @Override
    public LinkedList<String> getCommands() {
        return this.commands;
    }

    @Override
    public void openPage() {
        Logic.setCurrentPage(this);
    }
}


