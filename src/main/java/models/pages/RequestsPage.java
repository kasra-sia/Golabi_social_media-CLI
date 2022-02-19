package models.pages;

import models.User.User;
import models.logic.Logic;

import java.util.LinkedList;

public class RequestsPage extends Page {
    private int reqNum = 0;

    public RequestsPage() {
        this.commands = new LinkedList<>();

        this.commands.add("accept");
        this.commands.add("decline with notify");
        this.commands.add("decline without notify");
        this.commands.add("nextRequest");
        this.commands.add("previousRequest");
        this.commands.add("back to notificationPage");
        this.commands.add("exit");
    }

    @Override
    public LinkedList<String> getCommands() {
        return this.commands;
    }

    @Override
    public void openPage() {
//        Logic.getCurrentUser().getRequestList().add(Logic.searchUser("a"));
//        Logic.getCurrentUser().getRequestList().add(Logic.searchUser("b"));
        try {
            System.out.println("\nUser :" + Logic.getCurrentUser().getRequestList().getFirst());
        } catch (Exception e) {
            System.out.println("RequestList is empty");
        }

        Logic.setCurrentPage(this);
    }

    public void next() {
        if (!Logic.getCurrentUser().getRequestList().isEmpty()) {
            try {
                reqNum++;
                System.out.println("\nUser :" + Logic.getCurrentUser().getRequestList().get(reqNum));
            } catch (Exception e) {
                System.out.println(ConsoleColors.RED + "there is no next" + ConsoleColors.RESET);
//            reqNum--;
                previous();
            }
        } else System.out.println("RequestList is empty");

    }

    public void previous() {
        if (!Logic.getCurrentUser().getRequestList().isEmpty()) {
            try {
                reqNum--;
                System.out.println("\nUser :" + Logic.getCurrentUser().getRequestList().get(reqNum));
            } catch (Exception e) {
                System.out.println(ConsoleColors.RED + "there is no previous" + ConsoleColors.RESET);
                next();
//            reqNum++;
            }
        } else System.out.println("RequestList is empty");
    }

    public void accept() {
        User user2 =Logic.searchUser(Logic.getCurrentUser().getRequestList().get(reqNum));
        Logic.getCurrentUser().getFollowers().add(Logic.getCurrentUser().getRequestList().get(reqNum));
        user2.getFollowing().add(Logic.getCurrentUser().getUsername());
        Logic.logger.info("@"+user2+" has accepted "+"@"+Logic.getCurrentUser().getUsername()+"'s follow request ");
        user2.getSystemNotifications().add(Logic.getCurrentUser().getUsername() + " has accepted your request");
        user2.getSentRequests().replace(Logic.getCurrentUser().getUsername(),"accepted");
        Logic.getCurrentUser().getRequestList().remove(reqNum);
        openPage();
        reqNum = 0;
    }

    public void declineWithoutNotify() {
        User user2 =Logic.searchUser(Logic.getCurrentUser().getRequestList().get(reqNum));
        user2.getSentRequests().replace(Logic.getCurrentUser().getUsername(),"declined");
        Logic.logger.info("@"+user2+" has declined without notify "+"@"+Logic.getCurrentUser().getUsername()+"'s follow request ");
        Logic.getCurrentUser().getRequestList().remove(reqNum);

        openPage();
        reqNum = 0;
    }

    public void declineWithNotify() {
        User user2 =Logic.searchUser(Logic.getCurrentUser().getRequestList().get(reqNum));
        user2.getSentRequests().replace(Logic.getCurrentUser().getUsername(),"declined");
        Logic.logger.info("@"+user2+" has declined with notify "+"@"+Logic.getCurrentUser().getUsername()+"'s follow request ");
        Logic.searchUser(Logic.getCurrentUser().getRequestList().get(reqNum)).getSystemNotifications().add(Logic.getCurrentUser().getUsername() + " has declined your request");
        Logic.getCurrentUser().getRequestList().remove(reqNum);
        openPage();
        reqNum = 0;
    }
}
