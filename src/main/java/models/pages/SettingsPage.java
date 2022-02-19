package models.pages;

import models.logic.Logic;

import java.util.LinkedList;

public class SettingsPage extends Page{
    public SettingsPage(){
        commands = new LinkedList<>();
        this.commands.add("logout");
        this.commands.add("back to mainPage");
        this.commands.add("setAccountPrivate");
        this.commands.add("setAccountPublic");
        this.commands.add("everyBodyCanSeeYourLastSeen");
        this.commands.add("noBodyCanSeeYourLastSeen");
        this.commands.add("deleteAccount");
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
