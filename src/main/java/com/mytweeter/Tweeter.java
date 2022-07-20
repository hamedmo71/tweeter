package com.mytweeter;

import com.mytweeter.client.Login;
import com.mytweeter.entity.User;
import com.mytweeter.util.ApplicationContext;

import java.util.InputMismatchException;
import java.util.regex.Pattern;

public class Tweeter {
    public static void main(String[] args) {

        ApplicationContext.menu.showWelcomeMessage();
        Boolean flag = true;
        while (flag) {
            ApplicationContext.menu.showFirstPageMenu();

            try {
                int selectedNumber=ApplicationContext.getNumberScanner().nextInt();
                switch (selectedNumber) {
                    case 1:
                        Login.login();
                        break;
                    case 2:
                        signUp();
                        break;
                    case 3:
                        flag = false;
                        break;
                    default:
                        ApplicationContext.menu.showEnterValidNumMessage();
                        break;
                }
            } catch (InputMismatchException e) {
                ApplicationContext.getNumberScanner().next();
                ApplicationContext.menu.showEnterValidNumMessage();
            }
            System.out.println("=============================");
        }

    }


    /*Create Method For Signing up*/
    private static void signUp() {
        ApplicationContext.menu.showInputFirstName();
        String firstName = ApplicationContext.getStringScanner().nextLine();
        ApplicationContext.menu.showInputLastName();
        String lastName = ApplicationContext.getStringScanner().nextLine();
        ApplicationContext.menu.showEnterUsernameMessage();
        String username = ApplicationContext.getStringScanner().nextLine();
        if (ApplicationContext.getUserService().findUserWithUsername(username)==null){
            ApplicationContext.menu.showPasswordRules();
            ApplicationContext.menu.showEnterPasswordMessage();
            String password = ApplicationContext.getStringScanner().nextLine();
            String regex="^(?:(?=.*[a-z])(?:(?=.*[A-Z])(?=.*[\\d\\W])|(?=.*\\W)(?=.*\\d))|(?=.*\\W)(?=.*[A-Z])(?=.*\\d)).{8,}$";


            if (Pattern.matches(regex,password)){
                password=ApplicationContext.getEncryptPassword(password);
                User user = new User(firstName, lastName, username, password);
                ApplicationContext.getUserService().save(user);
                System.out.println("You're registered successfully.");
            }else System.out.println("Registration failed because password does not follows the rules. ");

        }else System.out.println("This username already exists.");

    }







}
