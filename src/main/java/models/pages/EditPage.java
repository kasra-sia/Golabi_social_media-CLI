package models.pages;

import models.User.User;
import models.logic.Logic;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Scanner;

public class EditPage extends Page {
    public EditPage() {
        this.commands = new LinkedList<>();
        this.commands.add("changeFirstName");
        this.commands.add("changeLastName");
//        this.commands.add("changeUserName");
        this.commands.add("changePhoneNumber");
        this.commands.add("changeEmail");
        this.commands.add("changeBio");
        this.commands.add("changeBirthdate");
        this.commands.add("changePassword");
        this.commands.add("back to personalPage");
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

    public void changeFirstName(Scanner scanner) {
        while (true) {
            System.out.println(ConsoleColors.PURPLE + "Enter your new FirstName :(if you want to cancel editing write CANCEL)" + ConsoleColors.RESET);
            String FirstName = scanner.nextLine();
            if (FirstName.equals("CANCEL")) return;
            if (!FirstName.equals("")) {
                Logic.getCurrentUser().setFirstName(FirstName);
                System.out.println("your first name has been changed");
                break;
            }
            System.out.println(ConsoleColors.RED + "you should fill this part" + ConsoleColors.RESET);
        }
    }
    public void changeLastName(Scanner scanner) {
        while (true) {
            System.out.println(ConsoleColors.PURPLE + "Enter your LastName :(if you want to cancel editing write CANCEL)" + ConsoleColors.RESET);
            String LastName = scanner.nextLine();
            if (LastName.equals("CANCEL")) return;
            if (!LastName.equals("")) {
                Logic.getCurrentUser().setFirstName(LastName);
                System.out.println("your last name has been changed");
                break;
            }
            System.out.println("you should fill this part");
        }
    }
    public void changeUsername(Scanner scanner) {
        username:
        while (true) {
            System.out.println(ConsoleColors.PURPLE + "Enter your UserName :(if you want to cancel editing write CANCEL)" + ConsoleColors.RESET);
            String UserName = scanner.nextLine();
            if (UserName.equals("CANCEL")) return;
            if (!Logic.getUsers().isEmpty())
                for (User user : Logic.getUsers()) {
                    if (user.getUsername().equals(UserName)) {
                        System.out.println(ConsoleColors.RED + "This Username has already been taken" + ConsoleColors.RESET);
                        continue username;
                    }
                }
            if (!UserName.equals("")) {
                Logic.getCurrentUser().setUsername(UserName);
                System.out.println("your username has been changed");
                break;
            }
            System.out.println(ConsoleColors.RED + "you should fill this part" + ConsoleColors.RESET);
        }
    }
    public void changePhoneNumber(Scanner scanner) {
        PhoneNumber:
        while (true) {
            System.out.println(ConsoleColors.BLUE + "Enter your phone number : (if you want to cancel editing write CANCEL)" + ConsoleColors.RESET);
            String phoneNumber = "";
            try {
                phoneNumber = scanner.nextLine();
                long PhoneNumber = Long.parseLong(phoneNumber);
                if (!Logic.getUsers().isEmpty())
                    for (User user : Logic.getUsers()) {
                        if (user.getPhoneNumber() == (PhoneNumber)) {
                            System.out.println(ConsoleColors.RED + "This Phone number has already been taken" + ConsoleColors.RESET);
                            continue PhoneNumber;
                        }
                    }
                Logic.getCurrentUser().setPhoneNumber(PhoneNumber);
                System.out.println("Your phone number has been changed");
                break PhoneNumber;
            } catch (Exception e) {
                if (!phoneNumber.equals("CANCEL")) {
                    if (!phoneNumber.equals(""))
                        System.out.println(ConsoleColors.RED + "WRONG FORMAT : Only numbers are accepted" + ConsoleColors.RESET);
                    else {
                        Logic.getCurrentUser().setPhoneNumber(0);
                        System.out.println("Your phone number has been changed");
                        break;
                    }
                } else break;
            }
        }
    }
    public void changeEmail(Scanner scanner) {
        email:
        while (true) {
            System.out.println(ConsoleColors.PURPLE + "Enter your Email Address :" + ConsoleColors.RESET);
            String Email = scanner.nextLine();
            if (!Logic.getUsers().isEmpty())
                for (User user : Logic.getUsers()) {
                    if (user.getEmail().equals(Email)) {
                        System.out.println(ConsoleColors.RED + "This Email has already been used" + ConsoleColors.RESET);
                        continue email;
                    }
                }
            if (!Email.equals("")) {
                Logic.getCurrentUser().setEmail(Email);
                System.out.println("Your email has been changed");
                break;
            }
            System.out.println(ConsoleColors.RED + "you should fill this part" + ConsoleColors.RESET);
        }
    }
    public void changeBio(Scanner scanner) {
        Bio:
        while (true) {
            System.out.println(ConsoleColors.BLUE + "Enter your Bio :  (if you want to cancel editing write CANCEL)" + ConsoleColors.RESET);
            String Bio = scanner.nextLine();
            if (!Bio.equals("CANCEL")) {
                if (!Bio.equals("")) {
                    Logic.getCurrentUser().setBio(Bio);
                    System.out.println("Your Bio has been set changed ");
                    break;
                } else {
                    Logic.getCurrentUser().setBio("");
                    System.out.println( "Your Bio has been set changed " );
                    break;
                }
            } else break;

        }
    }
    public void changeBirthDate(Scanner scanner){
        BirthDate:
        while (true) {
            System.out.println(ConsoleColors.BLUE + "Enter your Birth Date (year ENTER month ENTER day) : (if you want to cancel editing write CANCEL)" + ConsoleColors.RESET);
            String birthdate = "";
            try {
                birthdate = scanner.nextLine();

               Logic.getCurrentUser().setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthdate));
                System.out.println( "Your Birth Date  has been changed " );
                break BirthDate;
            } catch (Exception e) {
                if (!birthdate.equals("CANCEL")){
                    if (!birthdate.equals("")) {
                        System.out.println(ConsoleColors.RED + "WRONG FORMAT : pay attention to the given format" + ConsoleColors.RESET);
                    }else {
                        Logic.getCurrentUser().setBirthday(null);
                        System.out.println( "Your Birth Date  has been changed " );
                        break ;
                    }
                }

                else break;
            }
        }
    }
    public void ChangePassword(Scanner scanner){
        while (true) {
            System.out.println(ConsoleColors.PURPLE + "Enter your Password :(if you want to cancel signingUp write CANCEL)" + ConsoleColors.RESET);
             String Password = scanner.nextLine();
            if (Password.equals("CANCEL"))return ;
            if (!Password.equals("")){
                System.out.println("your password has been changed" );
                break;
            }
            System.out.println(ConsoleColors.RED + "you should fill this part" + ConsoleColors.RESET);
        }
    }
}
