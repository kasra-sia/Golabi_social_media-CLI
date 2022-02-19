package models.pages;

import models.logic.Logic;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public class NotificationsPage extends Page{
    NotificationsPage() {
        this.commands = new LinkedList<>();
        this.commands.add("requestsPage");
        this.commands.add("systemNotifications");
        this.commands.add("delete all notifications");
        this.commands.add("back to personalPage");
        this.commands.add("sentRequests");
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

    public void printNotifications() {
       if (Logic.getCurrentUser().getSystemNotifications().isEmpty())
           System.out.println("there is no notifications");
       else Logic.getCurrentUser().getSystemNotifications().forEach(System.out::println);
    }
    public void sentRequests(){
     LinkedHashMap<String ,String> temp = Logic.getCurrentUser().getSentRequests();
     if (!temp.isEmpty())
        for (String username:temp.keySet()) {
            System.out.println(username+"-->"+temp.get(username));
        }
     else System.out.println("it's empty");
    }
}



