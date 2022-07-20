package com.mytweeter.util;

public class Menu {
    public void showWelcomeMessage() {
        System.out.println("Welcome to Tweeter Simulator...");

    }

    public void showEnterUsernameMessage() {
        System.out.print("Username: ");
    }

    public void showEnterPasswordMessage() {
        System.out.print("Password: ");
    }

    public void showPasswordRules() {
        System.out.println("The password must then contain characters from at least 3 of the following 4 rules:\n" +
                "Upper case\n" +
                "Lower case\n" +
                "Numbers\n" +
                "Non-alpha numeric");
    }

    public void showUserMenu() {
        System.out.println("Choose from following option...");
        System.out.println("1. Profile");
        System.out.println("2. Show my tweets");
        System.out.println("3. Tweet");
        System.out.println("4. Show all tweets");
        System.out.println("5. Search in tweets");
        System.out.println("6. Back to previous menu");
    }

    public void showInsertTweetContext() {
        System.out.println("Insert Tweet's Context...");
    }

    public void showInputFirstName() {
        System.out.print("First name: ");
    }
    public void showInputLastName() {
        System.out.print("Last name: ");
    }

    public void showEnterValidNumMessage() {
        System.out.println("Please enter valid number.");
    }

    public void showTweetDetailQuestion() {
        System.out.println("Do you want to see a tweet with details?");
        System.out.println("1. Yes");
        System.out.println("2. No");
    }

    public void showInputTweetId() {
        System.out.print("Enter tweet's id that you want to read: ");
    }

    public void showSearchMenu() {
        System.out.println("Search items:");
        System.out.println("1. First name of user");
        System.out.println("2. Last name of user");
        System.out.println("3. Username of user");
        System.out.println("4. Tweet's context");
        System.out.println("5. Start Searching");


    }

    public void showUserProfileMenu() {
        System.out.println("Choose from following options:");
        System.out.println("1. Show my profile");
        System.out.println("2. Delete account");
        System.out.println("3. Edit account");
        System.out.println("4. Back to previous menu");
    }

    public void askForDeletingAccount() {
        System.out.println("Are you sure you want to delete account. If you delete your account, all of your tweets, comments and likes will delete.");
        System.out.println("1. Yes");
        System.out.println("2. No");
    }

    public void updateOrDeleteTweetMessage() {
        System.out.println("1. Edit tweet");
        System.out.println("2. Delete tweet");
        System.out.println("3. Back to previous menu");
    }

    public void showWarningForDeleteTweet() {
        System.out.println("Are you sure you want to delete tweet?");
        System.out.println("1. Yes");
        System.out.println("2. No");
    }

    public void likeOrCommentOnTweetMenu() {
        System.out.println("1. Comment");
        System.out.println("2. Like");
        System.out.println("3. Back to previous menu");
    }

    public void commentMenu() {
        System.out.println("1. Comment on tweet");
        System.out.println("2. Delete comment");
        System.out.println("3. Edit comment");
        System.out.println("4. Back to previous menu");
    }

    public void likeMenu() {
        System.out.println("1. Like tweet");
        System.out.println("2. Remove like");
        System.out.println("3. Back to previous menu");
    }

    public void showFirstPageMenu() {
        System.out.println("1. Login");
        System.out.println("2. Sign up");
        System.out.println("3. Exit");
    }

    public void editUserMenu() {
        System.out.println("1. Edit first name ");
        System.out.println("2. Edit last name ");
        System.out.println("3. Edit username ");
        System.out.println("4. Edit password ");
        System.out.println("5. Edit bio ");
        System.out.println("6. Back to previous menu ");
    }

    public void showEnterBioMessage() {
        System.out.print("bio: ");
    }
}
