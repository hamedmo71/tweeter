package com.mytweeter.util;

import com.mytweeter.repository.CommentRepository;
import com.mytweeter.repository.LikeRepository;
import com.mytweeter.repository.TweetRepository;
import com.mytweeter.repository.UserRepository;
import com.mytweeter.repository.impl.CommentRepositoryImpl;
import com.mytweeter.repository.impl.LikeRepositoryImpl;
import com.mytweeter.repository.impl.TweetRepositoryImpl;
import com.mytweeter.repository.impl.UserRepositoryImpl;
import com.mytweeter.service.CommentService;
import com.mytweeter.service.LikeService;
import com.mytweeter.service.TweetService;
import com.mytweeter.service.UserService;
import com.mytweeter.service.impl.CommentServiceImpl;
import com.mytweeter.service.impl.LikeServiceImpl;
import com.mytweeter.service.impl.TweetServiceImpl;
import com.mytweeter.service.impl.UserServiceImpl;

import javax.persistence.EntityManager;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class ApplicationContext {

    public static final EntityManager em=HibernateUtil.getEntityManager();

    private static UserService userService;

    private static TweetService tweetService;

    private static CommentService commentService;

    private static LikeService likeService;

    private static UserRepository userRepository;

    private static TweetRepository tweetRepository;

    private static CommentRepository commentRepository;

    private static LikeRepository likeRepository;

    public static Menu menu=new Menu();

    private static Scanner numberScanner;

    private static Scanner stringScanner;

    private static String encryptPassword;



    public static UserRepository getUserRepository(){
        if (userRepository==null){
            userRepository=new UserRepositoryImpl(em);
        }
        return userRepository;
    }

    public static UserService getUserService(){
        if (userService==null){
            userService=new UserServiceImpl(getUserRepository());
        }
        return userService;
    }

    public static TweetRepository getTweetRepository(){
        if(tweetRepository==null){
            tweetRepository=new TweetRepositoryImpl(em);
        }
        return tweetRepository;
    }

    public static TweetService getTweetService(){
        if (tweetService==null){
            tweetService=new TweetServiceImpl(getTweetRepository());
        }
        return tweetService;
    }

    public static CommentRepository getCommentRepository(){
        if(commentRepository==null){
            commentRepository=new CommentRepositoryImpl(em);
        }
        return commentRepository;
    }

    public static CommentService getCommentService(){
        if (commentService==null){
            commentService=new CommentServiceImpl(getCommentRepository());
        }
        return commentService;
    }

    public static LikeRepository getLikeRepository(){
        if(likeRepository==null){
            likeRepository=new LikeRepositoryImpl(em) {
            };
        }
        return likeRepository;
    }

    public static LikeService getLikeService(){
        if (likeService==null){
            likeService=new LikeServiceImpl(getLikeRepository());
        }
        return likeService;
    }

    public static Scanner getNumberScanner(){
        if (numberScanner==null){
            numberScanner=new Scanner(System.in);
        }
        return numberScanner;
    }

    public static Scanner getStringScanner(){
        if (stringScanner==null){
            stringScanner=new Scanner(System.in);
        }
        return stringScanner;
    }

    public static String getEncryptPassword(String password)  {
        EcryptPassword ecryptPassword = new EcryptPassword(password);
        try {
            return ecryptPassword.encPassword();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
            return password;
        }
    }
}
