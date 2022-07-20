package com.mytweeter.client;

import com.mytweeter.entity.Comment;
import com.mytweeter.entity.Tweet;
import com.mytweeter.entity.User;
import com.mytweeter.util.ApplicationContext;
import com.mytweeter.util.TweetSearch;

import java.util.InputMismatchException;
import java.util.List;

public class Login {
    /*Create Method For Logging in*/
    public static void login() {
        ApplicationContext.menu.showEnterUsernameMessage();
        String username = ApplicationContext.getStringScanner().nextLine();
        ApplicationContext.menu.showEnterPasswordMessage();
        String password = ApplicationContext.getStringScanner().nextLine();
        password=ApplicationContext.getEncryptPassword(password);
        User user = ApplicationContext.getUserService().loginWithUsernameAndPassword(username, password);

        if (user != null) {
            System.out.println("Successfully login.");
            redirectUser(user);
        } else System.out.println("Try again");
    }




    /*Create Method For redirecting user*/
    private static void redirectUser(User user) {

        Boolean flag = true;

        while (flag) {
            try {
                ApplicationContext.menu.showUserMenu();
                int selectedNumber = ApplicationContext.getNumberScanner().nextInt();

                switch (selectedNumber) {
                    case 1:
                        UserProfile.userProfile(user);
                        break;
                    case 2:
                        TweetOperations.showMyTweets(user);
                        break;
                    case 3:
                        TweetOperations.tweet(user);
                        break;
                    case 4:
                        TweetOperations.showAllTweets(user);
                        break;
                    case 5:
                        TweetOperations.searchInTweets(user);
                        break;
                    case 6:
                          UserProfile.searchInUsers(user);
                    case 7:
                        flag = false;
                        break;
                    default:
                        ApplicationContext.menu.showEnterValidNumMessage();
                        break;
                }
            } catch (InputMismatchException ex) {
                ApplicationContext.getNumberScanner().next();
                ApplicationContext.menu.showEnterValidNumMessage();

            }
            System.out.println("==============================");
        }
    }






}
