package models.pages;

import models.DateToString;
import models.User.User;
import models.logic.Logic;

import java.util.LinkedList;



public class FoundUser extends Page{
   public FoundUser(User user){
        this.commands = new LinkedList<>();
        this.commands.add("follow");
        this.commands.add("Unfollow");
        this.commands.add("Block");
        this.commands.add("UnBlock");
        this.commands.add("back to explorerPage");
        this.commands.add("back to mainPage");
        this.commands.add("showTweets");
        this.commands.add("openChatroom");
        this.commands.add("reportUser");
        this.commands.add("exit");
//        this.commands.add("");
        showInfo(user);
   }
    @Override
    public LinkedList<String> getCommands() {
        return this.commands;
    }

    @Override
    public void openPage() {
        Logic.setCurrentPage(this);
    }
    public void showInfo(User user){
       if (user.isPrivate() && !user.getFollowers().contains(Logic.getCurrentUser().getUsername())) {
           System.out.println("firstName :" + user.getFirstName());
           System.out.println(ConsoleColors.PURPLE + "----------------------------------------------" + ConsoleColors.RESET);
           System.out.println("lastName :" + user.getLastName());
           System.out.println(ConsoleColors.PURPLE + "----------------------------------------------" + ConsoleColors.RESET);
           System.out.println("username :" + user.getUsername());
           System.out.println(ConsoleColors.PURPLE + "----------------------------------------------" + ConsoleColors.RESET);
           System.out.println("bio :" + user.getBio());
           System.out.println(ConsoleColors.PURPLE + "----------------------------------------------" + ConsoleColors.RESET);
       }else{
           System.out.println("firstName :"+user.getFirstName());
           System.out.println(ConsoleColors.PURPLE+"----------------------------------------------"+ConsoleColors.RESET);
           System.out.println("lastName :"+user.getLastName());
           System.out.println(ConsoleColors.PURPLE+"----------------------------------------------"+ConsoleColors.RESET);
           System.out.println("username :"+user.getUsername());
           System.out.println(ConsoleColors.PURPLE+"----------------------------------------------"+ConsoleColors.RESET);
           if (user.getPhoneNumber()!=-1)
           System.out.println("phoneNumber :"+user.getPhoneNumber());
           else System.out.println("phoneNumber : null");
           System.out.println(ConsoleColors.PURPLE+"----------------------------------------------"+ConsoleColors.RESET);
           if (user.getBirthday()!=null)
           System.out.println("birthdate : " + DateToString.convert(user.getBirthday()));
           else System.out.println("birthdate : null");
           System.out.println(ConsoleColors.PURPLE+"----------------------------------------------"+ConsoleColors.RESET);
           if (!user.getBio().equals(""))
           System.out.println("bio :"+user.getBio());
           else System.out.println("bio : null");
           System.out.println(ConsoleColors.PURPLE+"----------------------------------------------"+ConsoleColors.RESET);
           System.out.println("followers :\n");
           if (user.getFollowers() != null)
               user.getFollowers().forEach(System.out::println);
           System.out.println(ConsoleColors.PURPLE+"----------------------------------------------"+ConsoleColors.RESET);
           System.out.println("following :\n");
           if (user.getFollowing() != null)
               user.getFollowing().forEach(System.out::println);
           System.out.println(ConsoleColors.PURPLE+"----------------------------------------------"+ConsoleColors.RESET);
           if (user.isLastSeenRecently()){
               System.out.println("last seen recently");
           }else System.out.println("last seen : "+user.getLastSeen());
           System.out.println(ConsoleColors.PURPLE + "----------------------------------------------" + ConsoleColors.RESET);
           if (user.isPrivate()){
               System.out.println("this page is "+ConsoleColors.RED+ "private " +ConsoleColors.RESET);
           }else System.out.println("this page is" +ConsoleColors.CYAN+" public"+ConsoleColors.RESET);
           System.out.println(ConsoleColors.PURPLE + "----------------------------------------------" + ConsoleColors.RESET);

       }

    }

}
