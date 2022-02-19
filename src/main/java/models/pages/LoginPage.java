package models.pages;
import models.User.User;
import models.logic.Logic;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Scanner;

public class LoginPage extends Page {
    private Scanner scanner = new Scanner(System.in);
    private LinkedList<User> users;

    public LoginPage() {
        this.users = Logic.getUsers();
    }

    public boolean signUp() {
        String FirstName;
        String LastName;
        String UserName;
        String Password;
        String Email;
        long PhoneNumber;
        String Bio = null;
        while (true) {
            System.out.println(ConsoleColors.PURPLE + "Enter your FirstName :(if you want to cancel signingUp write CANCEL)" + ConsoleColors.RESET);
            FirstName = scanner.nextLine();
            if (FirstName.equals("CANCEL"))return false;
            if (!FirstName.equals("")) break;
            System.out.println(ConsoleColors.RED + "you should fill this part" + ConsoleColors.RESET);
        }
        while (true) {
            System.out.println(ConsoleColors.PURPLE + "Enter your LastName :(if you want to cancel signingUp write CANCEL)" + ConsoleColors.RESET);
            LastName = scanner.nextLine();
            if (LastName.equals("CANCEL"))return false;
            if (!LastName.equals("")) break;
            System.out.println("you should fill this part");
        }
//--------------------------------------------------------------------------------------
        username:
        while (true) {
            System.out.println(ConsoleColors.PURPLE + "Enter your UserName :(if you want to cancel signingUp write CANCEL)" + ConsoleColors.RESET);
            UserName = scanner.nextLine();
            if (UserName.equals("CANCEL"))return false;
            if (!users.isEmpty())
                for (User user : users) {
                    if (user.getUsername().equals(UserName)) {
                        System.out.println(ConsoleColors.RED + "This Username has already been taken" + ConsoleColors.RESET);
                        continue username;
                    }
                }
            if (!UserName.equals("")) break;
            System.out.println(ConsoleColors.RED + "you should fill this part" + ConsoleColors.RESET);
        }
//--------------------------------------------------------------------------------------
        email:
        while (true) {
            System.out.println(ConsoleColors.PURPLE + "Enter your Email Address :" + ConsoleColors.RESET);
            Email = scanner.nextLine();
            if (!users.isEmpty())
                for (User user : users) {
                    if (user.getEmail().equals(Email)) {
                        System.out.println(ConsoleColors.RED + "This Email has already been used" + ConsoleColors.RESET);
                        continue email;
                    }
                }
            if (!Email.equals("")) break;
            System.out.println(ConsoleColors.RED + "you should fill this part" + ConsoleColors.RESET);
        }
        while (true) {
            System.out.println(ConsoleColors.PURPLE + "Enter your Password :(if you want to cancel signingUp write CANCEL)" + ConsoleColors.RESET);
            Password = scanner.nextLine();
            if (Password.equals("CANCEL"))return false;
            if (!Password.equals("")) break;
            System.out.println(ConsoleColors.RED + "you should fill this part" + ConsoleColors.RESET);
        }
        creatUser(FirstName, LastName, UserName, Password, Email);
//--------------------------------------------------------------------------------------
        BirthDate:
        while (true) {
            System.out.println(ConsoleColors.BLUE + "Enter your Birth Date :(year ENTER month ENTER day) \n write SKIP to skip" + ConsoleColors.RESET);
            String birthdate = "";
            try {
                birthdate = scanner.nextLine();

                user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(birthdate));
                System.out.println(ConsoleColors.BLACK + "Your Birth Date  has set to your account " + ConsoleColors.RESET);
                break BirthDate;
            } catch (Exception e) {
                if (!birthdate.equalsIgnoreCase("SKIP"))
                    System.out.println(ConsoleColors.RED + "WRONG FORMAT : pay attention to the given format" + ConsoleColors.RESET);
                else break;
            }
        }
//--------------------------------------------------------------------------------------
        PhoneNumber:
        while (true) {
            System.out.println(ConsoleColors.BLUE + "Enter your phone number :\nwrite SKIP to skip" + ConsoleColors.RESET);
            String phoneNumber = null;
            try {
                phoneNumber = scanner.next();
                PhoneNumber = Long.parseLong(phoneNumber);
                if (!users.isEmpty())
                    for (User user : users) {
                        if (user.getPhoneNumber() == (PhoneNumber)) {
                            System.out.println(ConsoleColors.RED + "This Phone number has already been taken" + ConsoleColors.RESET);
                            continue PhoneNumber;
                        }
                    }
                user.setPhoneNumber(PhoneNumber);
                System.out.println(ConsoleColors.BLACK + "Your phone number has been set to your account " + ConsoleColors.RESET);
                break PhoneNumber;
            } catch (Exception e) {
                if (!phoneNumber.equalsIgnoreCase("SKIP"))
                    System.out.println(ConsoleColors.RED + "WRONG FORMAT : Only numbers are accepted" + ConsoleColors.RESET);
                else break;
            }
        }
//--------------------------------------------------------------------------------------
        Bio:
        while (true) {
            System.out.println(ConsoleColors.BLUE + "Enter your Bio : \n write SKIP to skip" + ConsoleColors.RESET);
            scanner.nextLine();
            Bio = scanner.nextLine();
            if (!Bio.equalsIgnoreCase("SKIP")) {
                user.setBio(Bio);
                System.out.println(ConsoleColors.BLACK + "Your Bio has been set to your account " + ConsoleColors.RESET);
                break;
            } else break;
        }
        Logic.setCurrentUser(user);
        System.out.println("Hi " + user.getFirstName() + "\nYour account has been created successfully , Now you are a new Golabi member =)");
        return true;
    }
    public boolean signIn() {
        String UserName;
        String Password;
        while (true) {
            System.out.println(ConsoleColors.PURPLE + "Enter your UserName :(if you want to cancel signingIn write CANCEL)" + ConsoleColors.RESET);
            UserName = scanner.nextLine();
            if (UserName.equals("")) {
                System.out.println("you should fill this part");
                continue;
            }
            if (UserName.equals("CANCEL"))return false;
                System.out.println(ConsoleColors.PURPLE + "Enter your Password :(if you want to cancel signingIn write CANCEL)" + ConsoleColors.RESET);
                Password = scanner.nextLine();
                if (Password.equals("CANCEL"))return false;
                if (Password.equals("")) {
                    System.out.println("you should fill this part");
                    continue;
                }
                if (isValidUserNameOrPassword(UserName,Password)) {
                    loadUser(UserName);
                    Logic.setCurrentUser(Logic.searchUser(UserName));
                    System.out.println("hi "+user.getFirstName()+", welcome back");
                    break;
                }
                else System.out.println(ConsoleColors.RED+"your username or password is wrong"+ConsoleColors.RESET);
        }
return true;
    }
    private void creatUser(String firstName, String lastname, String username, String password, String Email) {
        this.user = new User(firstName, lastname, username, password, Email);
        Logic.setCurrentUser(this.user);
        users.add(user);

    }
    private void loadUser(String username) {
        User temp = Logic.searchUser(username);
        Logic.setCurrentUser(temp);
        this.user = Logic.getCurrentUser();
    }
    private boolean isValidUserNameOrPassword(String username , String password){
        for (User x:users) {
            User temp = Logic.searchUser(username);
            if (username.equals(x.getUsername()) && password.equals(temp.getPassword())){
                return true;
            }
        }
        return false;
    }
    @Override
    public LinkedList<String> getCommands() {
        return null;
    }
    @Override
    public void openPage() {
    }
}