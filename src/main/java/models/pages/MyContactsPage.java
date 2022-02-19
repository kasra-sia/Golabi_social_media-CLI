package models.pages;

import models.logic.Logic;

import java.util.LinkedList;

public class MyContactsPage extends Page{
  private LinkedList<String> currentList;
    public MyContactsPage(){
        this.commands = new LinkedList<>();
        this.commands.add("openProfile");
        this.commands.add("back to personalPage");
        this.commands.add("exit");
    }
    @Override
    public LinkedList<String> getCommands() {
       return this.commands;
    }

    @Override
    public void openPage() {
    }
    public void openPage(LinkedList<String> contacts){
        this.currentList = contacts;
        Logic.setCurrentPage(this);
        CPrint();
    }
    public void CPrint(){
        System.out.println("users : ");
      if (!currentList.isEmpty())
          currentList.forEach(System.out::println);
      else System.out.println("it's empty");
    }
}
