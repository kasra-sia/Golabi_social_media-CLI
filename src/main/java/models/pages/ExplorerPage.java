package models.pages;

import models.logic.Logic;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;


public class ExplorerPage extends Page {
    public FoundUser foundUser;

    public ExplorerPage() {
        commands = new LinkedList<>();
        this.commands.add("find");
        this.commands.add("back to mainPage");
        this.commands.add("randomTweets");
        this.commands.add("exit");
//        this.commands.add("");
//        this.commands.add("");
//        this.commands.add("");
    }

    @Override
    public LinkedList<String> getCommands() {
        return this.commands;
    }

    @Override
    public void openPage() {
        Logic.setCurrentPage(this);

    }

    public boolean find(String username) {
//        username = scanner.nextLine();
        if (Logic.searchUser(username) != null) {
            if (!Logic.searchUser(username).getBlacklist().contains(Logic.getCurrentUser().getUsername())) {
                this.foundUser = new FoundUser(Logic.searchUser(username));
                this.foundUser.openPage();
                return true;
            }
        }
        return false;
    }
    public void reportUser(String username){
        try {
            FileWriter filterWriter = new FileWriter("resources/Data base/reported users.json",true);
            filterWriter.write("@"+Logic.getCurrentUser().getUsername()+" reported this user : \n");
            Logic.objectMapper.writeValue(filterWriter, username);
            filterWriter.write("-----------------------------");
//            Logic.getCurrentUser().block(Logic.searchUser(username));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
