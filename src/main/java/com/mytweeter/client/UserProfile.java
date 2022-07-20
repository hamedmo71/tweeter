package com.mytweeter.client;

import com.mytweeter.entity.Tweet;
import com.mytweeter.entity.User;
import com.mytweeter.util.ApplicationContext;

import java.util.InputMismatchException;
import java.util.List;

public class UserProfile {

    public static void userProfile(User user){
        Boolean flag = true;

        while (flag) {
            try {
                ApplicationContext.menu.showUserProfileMenu();
                int selectedNumber = ApplicationContext.getNumberScanner().nextInt();

                switch (selectedNumber) {
                    case 1:
                        showProfile(user);
                        break;
                    case 2:
                        deleteAccount(user);
                        break;
                    case 3:
                        editAccount(user);
                        break;
                    case 4:
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
        }
    }



    private static void showProfile(User user) {
        User userProfile = ApplicationContext.getUserService().findById(user.getId());
        System.out.println(userProfile);
    }

    private static void deleteAccount(User user) {

            try {
                ApplicationContext.menu.askForDeletingAccount();
                int selectedNumber = ApplicationContext.getNumberScanner().nextInt();

                switch (selectedNumber) {
                    case 1:
                        deleteMethod(user);
                        break;
                    case 2:
                        System.out.println("Account does not delete.");
                        break;
                    default:
                        ApplicationContext.menu.showEnterValidNumMessage();
                        break;
                }
            } catch (InputMismatchException ex) {
                ApplicationContext.getNumberScanner().next();
                ApplicationContext.menu.showEnterValidNumMessage();

            }
    }

    private static void deleteMethod(User user) {
        List<Tweet> tweetByUsername = ApplicationContext.getTweetService().findTweetByUsername(user.getUsername());
        for (Tweet t:tweetByUsername) {
            ApplicationContext.getCommentService().deleteComment(t.getId(),user.getId());
            ApplicationContext.getLikeService().deleteLike(t.getId(),user.getId());
            ApplicationContext.getTweetService().deleteById(t.getId());
        }
        ApplicationContext.getUserService().deleteById(user.getId());
    }

    private static void editAccount(User user) {
        ApplicationContext.menu.showInputFirstName();
        String firstName=ApplicationContext.getStringScanner().next();
        ApplicationContext.menu.showInputLastName();
        String lastName=ApplicationContext.getStringScanner().next();
        ApplicationContext.menu.showEnterUsernameMessage();
        String username=ApplicationContext.getStringScanner().next();
        ApplicationContext.menu.showEnterPasswordMessage();
        String password=ApplicationContext.getStringScanner().next();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPassword(password);
        ApplicationContext.getUserService().save(user);
    }
}
