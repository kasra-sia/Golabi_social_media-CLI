package models.logic;

import models.pages.*;
import models.pages.PersonalPage;
import models.User.User;
import models.tweet.TweetAndComment;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.*;
import java.util.*;


public class Logic {
    private String tempUsername;
    private static LinkedList<User> users = new LinkedList<>();
    private static LinkedList<TweetAndComment> randTweets = new LinkedList<>();
    private static User currentUser;
    private static Page currentPage;
    public static final Logger logger = LogManager.getLogger(Logic.class);
    private LinkedList<Page> pages;
    private MainPage mainPage = new MainPage();
    private PersonalPage personalPage = new PersonalPage();
    private MessagingPage messagingPage = new MessagingPage();
    private SettingsPage settingsPage = new SettingsPage();
    private ExplorerPage explorerPage = new ExplorerPage();
    public OthersTweets othersTweets = new OthersTweets();
    private TimelinePage timelinePage = new TimelinePage();
    private LoginPage loginPage;
    public static ObjectMapper objectMapper = new ObjectMapper();
    public static Scanner scanner = new Scanner(System.in);

    public Logic() {
        loadUsers();
        logger.info("program started");
        System.out.println(models.pages.ConsoleColors.YELLOW + "\n\n\n\n\t\t\t\t\tHello" +
                " , welcome to Golabi the worlds greatest social media \n\n\n" + models.pages.ConsoleColors.RESET);
        loginPage = new LoginPage();
        UserInteraction();
        saveUsers();
    }

    public static LinkedList<User> getUsers() {
        return users;
    }

    private void loadUsers() {
        try {
            File file = new File("resources/Data base/ID manager.txt");
            Scanner fileReader = new Scanner(file);
            User.setLastID(fileReader.nextLong());
            fileReader.close();
            fileReader.reset();
        } catch (NoSuchElementException | FileNotFoundException e) {
        }
        try {
            File file = new File("resources/Data base/Users.json");
            if (file.exists())
            users = objectMapper.readValue(new FileReader("resources/Data base/Users.json"), new TypeReference<LinkedList<User>>() {
            });
        } catch ( IOException e) {
            e.printStackTrace();
        }


    }

    private void saveUsers() {
        currentUser.setLastSeen();
        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new FileWriter("resources/Data base/Users.json",false), users);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter fileWriter = new FileWriter("resources/Data base/ID manager.txt", false);
            fileWriter.write(String.valueOf(User.getLastID()));
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void printCommands(Page page) {
        System.out.println(ConsoleColors.CYAN + "valid commands :" + ConsoleColors.RESET);
        for (String x : page.getCommands()) {
            System.out.println(ConsoleColors.YELLOW + "\t" + x + ConsoleColors.RESET);
        }
    }
    public static void setCurrentUser(User currentUser) {
        Logic.currentUser = currentUser;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentPage(Page currentPage) {
        Logic.currentPage = currentPage;
    }

    public static void loadRandTweets(){
        randTweets .clear();
        for (User user:users) {
            if (!currentUser.getMuteList().contains(user.getUsername())&&!user.isPrivate()&&!currentUser.getBlacklist().contains(user.getUsername())
            &&!user.getBlacklist().contains(currentUser.getUsername())){
               randTweets.addAll(user.getTweets());
            }
        }
        Collections.shuffle(randTweets,new Random());
    }

    public static Page getCurrentPage() {
        return currentPage;
    }

    public static User searchUser(String username) {
        for (User x:users)
            if (username.equals(x.getUsername()))return x;
        return null;
    }

    private void forward(){
        if (currentPage == timelinePage) {
            messagingPage.openChat(scanner.nextLine());
            messagingPage.currentChatRoom.newMessage(timelinePage.getCurrentTweetAndComment().toString());
            timelinePage.openPage();
        }
        if (currentPage == othersTweets) {
            messagingPage.openChat(scanner.nextLine());
            messagingPage.currentChatRoom.newMessage(othersTweets.getCurrentTweetAndComment().toString());
            othersTweets.openPage();
        }
        if (currentPage == othersTweets) {
            messagingPage.openChat(scanner.nextLine());
            messagingPage.currentChatRoom.newMessage(othersTweets.getCurrentTweetAndComment().toString());
            othersTweets.openPage();
        }
        if (currentPage == personalPage.myTweets) {
            messagingPage.openChat(scanner.nextLine());
            messagingPage.currentChatRoom.newMessage(personalPage.myTweets.getCurrentTweetAndComment().toString());
            personalPage.myTweets.openPage();
        }
    }

    private void UserInteraction() {
        String ans = "";
        First:
        while (true) {
            Second:
            while (true) {
                System.out.println(models.pages.ConsoleColors.PURPLE + "Have you already sign up in Golabi ?(Y/N)" +
                        "\nPress e to exit" + models.pages.ConsoleColors.RESET);
                ans = scanner.nextLine();
                switch (ans) {
                    case "n":
                    case "N":
                        if (loginPage.signUp()){
                            saveUsers();
                            logger.info("@"+currentUser.getUsername()+" signedUp");
                            break Second;}
                        else continue;
                    case "Y":
                    case "y":
                        if (loginPage.signIn()) {
                            logger.info("@"+currentUser.getUsername()+" signedIn");
                            break Second;
                        }
                        else continue;
                    case "e":
                    case "E":
                        System.exit(0);
                    default:
                        System.out.println(models.pages.ConsoleColors.RED + "WRONG FORMAT !");
                }
            }
            loadRandTweets();
//***************************************************************************************************
            mainPage.openPage();
            String command;
            Third:
            while (true) {
                printCommands(currentPage);
                command = scanner.nextLine();
                if (currentPage.getCommands().contains(command)) {
                    switch (command) {
                        case "explorerPage":
                        case "back to explorerPage":
                            explorerPage.openPage();
                            break;
                        case "find":
                            System.out.println("write the username : ");
                            tempUsername = scanner.nextLine();
                            if(explorerPage.find(tempUsername)){
                                explorerPage.foundUser.openPage();
                            }else {
                                System.out.println(models.logic.ConsoleColors.RED + "No result" + ConsoleColors.RESET);
                                explorerPage.openPage();
                            }
                            break ;
                        case "randomTweets":
                            loadRandTweets();
                            othersTweets.openPage(randTweets);
                            break;
                        case "follow":
                            currentUser.follow(searchUser(tempUsername));
                            explorerPage.openPage();
                            break ;
                        case "Unfollow":
                            currentUser.Unfollow(searchUser(tempUsername));
                            explorerPage.openPage();
                            break;
                        case "Block":
                            if (currentPage == explorerPage.foundUser)
                                currentUser.block(searchUser(tempUsername));
                            System.out.println("done");
                            explorerPage.openPage();
                            break ;
                        case "UnBlock":
                            currentUser.unBlock(searchUser(tempUsername));
                            explorerPage.openPage();
                            break ;
                        case "reportUser":
                            explorerPage.reportUser(tempUsername);
                            logger.info("@"+currentUser+" reported "+"@"+tempUsername);
                            break ;
                        case "showTweets":
                            User temp0 =searchUser(tempUsername);
                            if (!temp0.isPrivate() || currentUser.getFollowing().contains(temp0.getUsername()) )
                            othersTweets.openPage(searchUser(tempUsername).getTweets());
                            else System.out.println("this. account is private");
                            break ;
                        case"openChatroom":
                            messagingPage.openPage();
                            messagingPage.openChat(tempUsername);
                            break ;
//-----------------------------------------------------------------
                        case "messagingPage":
                        case "backToMessagingPage":
                            messagingPage.openPage();
                            break;
                        case "openChatRoom":
                            System.out.println("which user ? ");
                            messagingPage.openChat(scanner.nextLine());
//                            messagingPage.currentChatRoom.openPage();
                            break;
                        case "new message":
                            messagingPage.currentChatRoom.newMessage(scanner.nextLine());
// log
                            messagingPage.currentChatRoom.openPage();
                            break ;
                        case "creatGroup":
//log
                            System.out.println("enter the group name :");
                            messagingPage.creatGroup(scanner.nextLine());
                            messagingPage.openPage();
                            break ;
                        case "renameGroup":
//log
                            System.out.println("which group :");
                            String groupName = scanner.nextLine();
                            System.out.println("new name :");
                            String newName = scanner.nextLine();
                           if( !messagingPage.renameGroup(groupName,newName))
                               System.out.println(ConsoleColors.RED+"group '"+groupName+"' not found or invalid newName"+ConsoleColors.RESET);
                            messagingPage.openPage();
                           break ;
                        case "deleteGroup":
//log
                            System.out.println("which group ?");
                            String gp = scanner.nextLine();
                            if (messagingPage.deleteGroup(gp)){
                                logger.info("@"+currentUser.getUsername()+" deleted the group gp");
                                System.out.println("done");}
                            else System.out.println("not found");
                            messagingPage.openPage();
                            break ;
                        case "addMembersToGroup":
                            System.out.println("which group ?");
                            String gpName = scanner.nextLine();
                            System.out.println("which user ?");
                            String username = scanner.nextLine();
                            if (messagingPage.addMemberToGroup(username,gpName)){
                                logger.info("@"+username+" added to group "+gpName);
                                System.out.println("done");
                            }
                            else System.out.println("not found");
                            messagingPage.openPage();
                            break ;
                        case "removeMemberFromGroup":
                            System.out.println("which group ?");
                            String gpName1 = scanner.nextLine();
                            System.out.println("which user ?");
                            String username1 = scanner.nextLine();
                            if (messagingPage.removeMember(username1,gpName1)) {
                                logger.info("@"+username1+" removed from group "+gpName1);
                                System.out.println("done");
                            }
                            else System.out.println("not found");
                            messagingPage.openPage();
                            break ;
                        case "sendMessageToGroup":
                            System.out.println("which group ?");
                            String gpName2 = scanner.nextLine();
                            System.out.println("write your message :");
                            if (messagingPage.sendMessageToGroup(gpName2,scanner.nextLine())){
                                logger.info("@"+currentUser.getUsername()+" sent a message to group "+gpName2);
                                System.out.println("done");
                            }
                            else System.out.println("group not found ");
                            messagingPage.openPage();
                            break ;
//-----------------------------------------------------------------
                        case "personalPage":
                        case "back to personalPage":
                            personalPage.openPage();
                            break;
                        case "info":
                            currentUser.showInfo();
                            break;
                        case "notifications":
                        case "back to notificationPage":
                            personalPage.notificationsPage.openPage();
                            break;
                        case "delete all notifications":
                            currentUser.getSystemNotifications().clear();
                            logger.info("@"+currentUser+" removed notifications ");
                            break;
                        case "systemNotifications":
                            personalPage.notificationsPage.printNotifications();
                            break;
                        case "sentRequests":
                            personalPage.notificationsPage.sentRequests();
                            break ;
                        case "requestsPage":
                            personalPage.requestsPage.openPage();
                            break;
                        case "accept":
                            try {
                                personalPage.requestsPage.accept();
//log
                            }catch (Exception ignored){
                                System.out.println("RequestList is empty");
                            }
                            break;
                        case "decline with notify":
//log
                            try {
                                personalPage.requestsPage.declineWithNotify();
                            }catch (Exception ignored){
                                System.out.println("RequestList is empty");
                            }
                            break;
                        case "decline without notify":
//log
                            try {
                                personalPage.requestsPage.declineWithoutNotify();
                            }catch (Exception ignored){
                                System.out.println("RequestList is empty");
                            }
                            break ;
                        case "nextRequest":
                            personalPage.requestsPage.next();
                            break ;
                        case "previousRequest":
                            personalPage.requestsPage.previous();
                             break ;
                        case "myFollowers":
                            personalPage.myContactsPage.openPage(currentUser.getFollowers());
                            break ;
                        case "myFollowings":
                            personalPage.myContactsPage.openPage(currentUser.getFollowing());
                            break ;
                        case "openProfile":
                            System.out.println("which user ? ");
                            String temp2 = scanner.nextLine();
                            if (currentUser.getFollowing().contains(temp2)) {
                                explorerPage.find(temp2);
                                explorerPage.foundUser.openPage();
                            }else System.out.println("not found");
//                            personalPage.myContactsPage.openPage(currentUser.getFollowing());
                            break ;
                        case "ShowSavedTweets":
                            othersTweets.openPage(currentUser.getSavedTweets());
                            break ;
//----------------------------------------------------------------------------
                        case "editPage":
                            personalPage.editPage.openPage();
                            break ;
                        case "changeFirstName":

                            personalPage.editPage.changeFirstName(scanner);
                                logger.info("@"+currentUser.getUsername()+" changed his firstname ");
                                personalPage.editPage.openPage();
                            break ;
                        case "changeLastName":
                            personalPage.editPage.changeLastName(scanner);
                            logger.info("@"+currentUser.getUsername()+" changed his lastname ");
                            personalPage.editPage.openPage();
                            break ;
//                        case "changeUserName":
//                            personalPage.editPage.changeUsername(scanner);
//                            personalPage.editPage.openPage();
//                            break ;
                        case "changePhoneNumber":
                            personalPage.editPage.changePhoneNumber(scanner);
                            logger.info("@"+currentUser.getUsername()+" changed his phoneNumber ");
                            personalPage.editPage.openPage();
                            break ;
                        case "changeEmail":
                            personalPage.editPage.changeEmail(scanner);
                            logger.info("@"+currentUser.getUsername()+" changed his email ");
                            personalPage.editPage.openPage();
                            break ;
                        case "changeBio":
                            personalPage.editPage.changeBio(scanner);
                            logger.info("@"+currentUser.getUsername()+" changed his bio ");
                            personalPage.editPage.openPage();
                            break ;
                        case "changeBirthdate":
                            personalPage.editPage.changeBirthDate(scanner);
                            logger.info("@"+currentUser.getUsername()+" changed his birthdate ");
                            personalPage.editPage.openPage();
                            break ;
                        case "ChangePassword":
                            personalPage.editPage.ChangePassword(scanner);
                            logger.info("@"+currentUser.getUsername()+" changed his password ");
                            personalPage.editPage.openPage();
                            break ;
//----------------------------------------------------------------------------
                        case "newTweet":
                            System.out.println("what's on your mind : (write CANCEL to cancel tweeting)");
                            String temp =scanner.nextLine();
                            if (!temp.equals("CANCEL")) {
                                currentUser.addTweet(temp);
                                logger.info("@"+currentUser.getUsername()+" add new tweet ");
                            }
                            else personalPage.openPage();
                              break;
                        case "myTweets":
                            personalPage.myTweets.openPage(currentUser.getTweets());
                            break ;
                        case "previous":
                            if (currentPage == personalPage.myTweets)
                            personalPage.myTweets.previousTweetOrComment();
                            if (currentPage == othersTweets)
                                othersTweets.previousTweetOrComment();
                            if (currentPage == timelinePage)
                                timelinePage.previousTweetOrComment();
                            break ;
                        case "next":
                            if (currentPage == personalPage.myTweets)
                            personalPage.myTweets.nextTweetOrComment();
                            if (currentPage == othersTweets)
                             othersTweets.nextTweetOrComment();
                            if (currentPage == timelinePage)
                                timelinePage.nextTweetOrComment();
                            break ;
                        case "like":
                            if (currentPage == personalPage.myTweets)
                                personalPage.myTweets.like();
                            if (currentPage == othersTweets)
                                othersTweets.like();
                            if (currentPage == timelinePage)
                                timelinePage.like();
//log
                            System.out.println("done");
                            break ;
                        case "disLike":
//log
                            if (currentPage == personalPage.myTweets)
                                personalPage.myTweets.disLike();
                            if (currentPage == othersTweets)
                                othersTweets.disLike();
                            if (currentPage == timelinePage)
                                timelinePage.disLike();
                            System.out.println("done");
                            break;
                        case "reTweet":
//log
                            if (currentPage == personalPage.myTweets)
                            personalPage.myTweets.reTweet();
                            if (currentPage == othersTweets)
                                othersTweets.reTweet();
                            if (currentPage == timelinePage)
                                timelinePage.reTweet();
                            System.out.println("done");
                            break ;
                        case "addComment":
//log
                            System.out.println("your comment : ");
                            if (currentPage == personalPage.myTweets)
                            personalPage.myTweets.addComment(scanner.nextLine());
                            if (currentPage == othersTweets)
                                othersTweets.addComment(scanner.nextLine());
                            if (currentPage == timelinePage)
                                timelinePage.addComment(scanner.nextLine());
                            System.out.println("done");
                            break;
                        case "deleteTweet":
//log
                            personalPage.myTweets.deleteTweet();
                            System.out.println("done");
                            break ;
                        case "back":
                            if (currentPage==personalPage.myTweets)
                                personalPage.openPage();
                            if (currentPage == othersTweets)
                                explorerPage.openPage();
                            if (currentPage == timelinePage)
                                mainPage.openPage();
                            break ;
                        case "openComments":
                            if (currentPage==personalPage.myTweets)
                                personalPage.myTweets.openComments();
                            if (currentPage == othersTweets)
                                othersTweets.openComments();
                            if (currentPage == timelinePage)
                                timelinePage.openComments();
                            break ;
                        case "blockUser":
//log
                            if (currentPage == othersTweets)
                            othersTweets.blockUser();
                            if (currentPage == timelinePage)
                                timelinePage.blockUser();
                            break ;
                        case "muteUser":
                            if (currentPage == othersTweets)
                            othersTweets.muteUser();
                            if (currentPage == timelinePage)
                                timelinePage.muteUser();
                            break ;
                        case "report":
                            if (currentPage == othersTweets) {
                                othersTweets.reportTweet();
//log                                othersTweets.blockUser();
                            }
                            if (currentPage == timelinePage) {
                                timelinePage.reportTweet();
//                                timelinePage.blockUser();
                            }

                            break ;
                        case "forward":
//log
                            System.out.println("write user name : ");
                            try {
                                forward();
                                System.out.println("done");
                            }catch (Exception ignore){
                                System.out.println("not found in your chatList ");
                            }
                            timelinePage.openPage();
                            break ;
                        case "back to mainPage":
                            mainPage.openPage();
                            break ;
                        case "saveThisTweet":
//log
                            if (currentPage == othersTweets)
                                othersTweets.saveTweet();
                            if (currentPage == timelinePage)
                                timelinePage.saveTweet();
                            System.out.println("done");
                            break ;
//-----------------------------------------------------------------
                        case "timelinePage":
                            LinkedList<TweetAndComment> temp1 = new LinkedList<>();
                            currentUser.getFollowing().forEach(following->temp1.addAll(searchUser(following).getTweets()));
                            timelinePage.openPage(temp1);
                            break;
//-----------------------------------------------------------------
                        case "settingsPage":
                            settingsPage.openPage();
                            break;
                        case "setAccountPrivate":
                            currentUser.setPrivate(true);
                            logger.info("@"+currentUser+" set their account private");
                            break ;
                        case "setAccountPublic":
                            currentUser.setPrivate(false);
                            logger.info("@"+currentUser+" set their account public");
                            break ;
                        case "everyBodyCanSeeYourLastSeen":
                            currentUser.setLastSeenRecently(false);
                            logger.info("@"+currentUser+" set their last seen recently");
                            break ;
                        case "noBodyCanSeeYourLastSeen":
                            currentUser.setLastSeenRecently(true);
                            logger.info("@"+currentUser+" set their last seen online");
                            break;
                        case "deleteAccount":
                            currentUser.deleteAccount();
                            Logic.logger.info("account : "+"@"+Logic.getCurrentUser().getUsername()+" has been deleted ");
                            break ;
                        case "logout":
                            saveUsers();
                            logger.info("@"+currentUser.getUsername()+" loggedOut from golabi");
                            currentUser = null;
                            continue First;
//-----------------------------------------------------------------
                        case "exit":
                            saveUsers();
                            logger.info("program stopped");
                            currentUser.setLastSeen();
                            System.exit(0);
                            break;
                        default:
                            System.out.println(ConsoleColors.RED + "Undefined Command !" + ConsoleColors.RESET);
                    }
                } else System.out.println(ConsoleColors.RED + "Undefined Command !" + ConsoleColors.RESET);
            }
        }
    }
}

