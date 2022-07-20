package com.mytweeter.client;

import com.mytweeter.entity.Tweet;
import com.mytweeter.entity.User;
import com.mytweeter.util.ApplicationContext;
import com.mytweeter.util.TweetSearch;
import com.mytweeter.util.UserSearch;

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

        boolean flag=true;

        while(flag){
            ApplicationContext.menu.editUserMenu();
            int selectedNum = ApplicationContext.getNumberScanner().nextInt();
            switch (selectedNum){
                case 1:
                    ApplicationContext.menu.showInputFirstName();
                    String firstName=ApplicationContext.getStringScanner().nextLine();
                    user.setFirstName(firstName);
                    break;
                case 2:
                    ApplicationContext.menu.showInputLastName();
                    String lastName=ApplicationContext.getStringScanner().nextLine();
                    user.setLastName(lastName);
                    break;
                case 3:
                    ApplicationContext.menu.showEnterUsernameMessage();
                    String username=ApplicationContext.getStringScanner().nextLine();
                    user.setUsername(username);
                    break;
                case 4:
                    ApplicationContext.menu.showEnterPasswordMessage();
                    String password=ApplicationContext.getStringScanner().next();
                    user.setPassword(password);
                    break;
                case 5:
                    ApplicationContext.menu.showEnterBioMessage();
                    String bio=ApplicationContext.getStringScanner().nextLine();
                    user.setBio(bio);
                    break;
                case 6:
                    flag=false;
                    break;
                default:
                    System.out.println("Enter valid number.");
                    break;
            }
        }




        ApplicationContext.getUserService().save(user);
    }


    public static void searchInUsers(User user) {
        String firstName = null;
        String lastName = null;
        String username = null;


        Boolean flag = true;

        while (flag) {
            try {
                ApplicationContext.menu.showUserSearchMenu();
                int selectedNumber = ApplicationContext.getNumberScanner().nextInt();

                switch (selectedNumber) {
                    case 1:
                        System.out.print("Search in first name: ");
                        firstName = ApplicationContext.getStringScanner().next();
                        break;
                    case 2:
                        System.out.print("Search in last name: ");
                        lastName = ApplicationContext.getStringScanner().next();
                        break;
                    case 3:
                        System.out.print("Search in username: ");
                        username = ApplicationContext.getStringScanner().next();
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

        UserSearch userSearch=new UserSearch(username,firstName,lastName);
        List<User> userList = ApplicationContext.getUserService().searchUser(userSearch);

        if (userList.size()!=0){
            int i=1;
            for (User u : userList) {

                System.out.println(i + ": " + u.getUsername());
                i++;
            }

            System.out.print("Enter the number of user you want to see: ");
            int userNum = ApplicationContext.getNumberScanner().nextInt() - 1;
            User user1 = userList.get(userNum);
            System.out.println(user1.toString());
            List<Tweet> tweetByUserId = ApplicationContext.getTweetService().findTweetByUserId(user1.getId());

            if (tweetByUserId.size()!=0){
                System.out.println("Tweets ");
                for (Tweet t:tweetByUserId){
                    System.out.println(t.getId() + ": " + t.toString());
                }

                if (tweetByUserId.size() != 0) {
                    TweetOperations.askForShowTweetByDetail(user);
                }
            }else
            System.out.println("This user does not have any tweet.");



        }

    }
}
