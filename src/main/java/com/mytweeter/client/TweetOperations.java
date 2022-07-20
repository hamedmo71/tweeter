package com.mytweeter.client;

import com.mytweeter.entity.Comment;
import com.mytweeter.entity.LikeTweet;
import com.mytweeter.entity.Tweet;
import com.mytweeter.entity.User;
import com.mytweeter.util.ApplicationContext;
import com.mytweeter.util.TweetSearch;

import java.util.InputMismatchException;
import java.util.List;

public class TweetOperations {


    /*Method for tweet*/
    public static void tweet(User user) {
        ApplicationContext.menu.showInsertTweetContext();
        String tweetContext = ApplicationContext.getStringScanner().nextLine();
        Tweet tweet = new Tweet();
        if (tweetContext.length() <= 280) {
            tweet.setTweetContext(tweetContext);
            tweet.setUser(user);
            ApplicationContext.getTweetService().save(tweet);
        } else System.out.println("Tweet's length is more than 280");

    }


    /*Method for show tweets*/
    public static void showAllTweets(User user) {
        List<Tweet> tweetList = ApplicationContext.getTweetService().findAll();
        for (Tweet t : tweetList) {
            System.out.println("Tweet's id: " + t.getId());
            System.out.print(t.getUser().getUsername() + ": \t");
            System.out.println("Context: " + t);
            System.out.print(ApplicationContext.getLikeService().countTweetsLikes(t.getId()) + " Like\t \t");
            System.out.println(ApplicationContext.getCommentService().countTweetsComment(t.getId()) + " Comment");
            System.out.println("==============================");
        }
        if (tweetList.size() != 0) {

            askForShowTweetByDetail(user);

        } else System.out.println("There isn't any tweet.");
    }


    /*Method for display user's tweet*/
    public static void showMyTweets(User user) {
        List<Tweet> tweetList = ApplicationContext.getTweetService().findTweetByUserId(user.getId());
        for (Tweet t : tweetList) {
            System.out.println("Tweet's id: " + t.getId());
            System.out.print(t.getUser().getUsername() + ": \t");
            System.out.println("Context: " + t);
            System.out.print(ApplicationContext.getLikeService().countTweetsLikes(t.getId()) + " Like\t \t");
            System.out.println(ApplicationContext.getCommentService().countTweetsComment(t.getId()) + " Comment");
            System.out.println("==============================");
        }
        if (tweetList.size() != 0) {

            Tweet tweet = askForShowTweetByDetail(user);

            if (tweet != null) {
                updateOrDeleteTweet(user, tweet);
            }

        } else System.out.println("You don't have any tweet.");

    }


    /*Method for update or delete user's tweet*/
    private static void updateOrDeleteTweet(User user, Tweet tweet) {


        try {
            if (tweet != null && tweet.getUser().getId() == user.getId()) {

                ApplicationContext.menu.updateOrDeleteTweetMessage();
                int selectedNumber = ApplicationContext.getNumberScanner().nextInt();

                if (selectedNumber == 1) {

                    System.out.println("Enter tweet's context: ");
                    String tweeetContext = ApplicationContext.getStringScanner().nextLine();
                    tweet.setTweetContext(tweeetContext);
                    ApplicationContext.getTweetService().save(tweet);

                } else if (selectedNumber == 2) {

                    ApplicationContext.menu.showWarningForDeleteTweet();
                    int a = ApplicationContext.getNumberScanner().nextInt();

                    if (a == 1) {

                        ApplicationContext.getCommentService().deleteCommentByTweetId(tweet.getId());
                        ApplicationContext.getLikeService().deleteLikeByTweetId(tweet.getId());
                        ApplicationContext.getTweetService().deleteById(tweet.getId());
                        System.out.println("This tweet delete successfully.");

                    } else if (a == 2) {

                        System.out.println("You don't delete tweet.");

                    } else System.out.println("You've entered an invalid number.");

                } else if (selectedNumber == 3) {
                    System.out.println("Back to user menu...");


                } else System.out.println("You've entered an invalid number.");

            } else System.out.println("The tweet id you've enterd is not the id of one of your tweets.");

        } catch (InputMismatchException ex) {
            System.out.println("You've entered a letter.");
        }
    }

    /*Method for searching in tweets*/
    public static void searchInTweets(User user) {
        String firstName = null;
        String lastName = null;
        String username = null;
        String tweetsCntext = null;

        Boolean flag = true;

        while (flag) {
            try {
                ApplicationContext.menu.showSearchMenu();
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
                        System.out.print("Search in tweet's context: ");
                        tweetsCntext = ApplicationContext.getStringScanner().next();
                        break;
                    case 5:
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

        TweetSearch tweetSearch = new TweetSearch(tweetsCntext, username, firstName, lastName);
        List<Tweet> tweetList = ApplicationContext.getTweetService().searchTweet(tweetSearch);

        for (Tweet t : tweetList) {
            System.out.println("Tweet's id: " + t.getId());
            System.out.println("Context: " + t);
            System.out.print(ApplicationContext.getLikeService().countTweetsLikes(t.getId()) + " Like\t \t");
            System.out.println(ApplicationContext.getCommentService().countTweetsComment(t.getId()) + " Comment");
            System.out.println("==============================");
        }


        if (tweetList.size() != 0) {
            askForShowTweetByDetail(user);
        }

    }


    private static Tweet askForShowTweetByDetail(User user) {
        Tweet tweet = null;

        try {
            ApplicationContext.menu.showTweetDetailQuestion();
            int selectedNumber = ApplicationContext.getNumberScanner().nextInt();
            if (selectedNumber == 1) {
                tweet = showTweetWithDetail(user);
            } else if (selectedNumber == 2) {
                System.out.println("coming back to user menu.");
                ;
            } else ApplicationContext.menu.showEnterValidNumMessage();
        } catch (InputMismatchException e) {
            ApplicationContext.getNumberScanner().next();
            ApplicationContext.menu.showEnterValidNumMessage();
        }

        return tweet;
    }


    private static Tweet showTweetWithDetail(User user) {
        ApplicationContext.menu.showInputTweetId();
        long tweetId = ApplicationContext.getNumberScanner().nextLong();
        Tweet tweet = ApplicationContext.getTweetService().findById(tweetId);
        if (tweet != null) {
            System.out.print(tweet.getUser().getUsername() + ": ");
            System.out.println(tweet);
            System.out.print(ApplicationContext.getLikeService().countTweetsLikes(tweetId) + " Like\t \t");
            System.out.println(ApplicationContext.getCommentService().countTweetsComment(tweetId) + " Comment");
            List<Comment> commentsOfATweet = ApplicationContext.getCommentService().showCommentsOfATweet(tweetId);
            for (Comment c : commentsOfATweet) {
                System.out.format("%s:  ", c.getUser().getUsername());
                System.out.println(c);
            }

            likeOrCommentOnTweet(tweet, user);

        } else System.out.println("This tweet id does not exist.");

        return tweet;
    }


    private static void likeOrCommentOnTweet(Tweet tweet, User user) {
        ApplicationContext.menu.likeOrCommentOnTweetMenu();
        int nextInt = ApplicationContext.getNumberScanner().nextInt();

        try {
            if (nextInt == 1) {
                commentOnTweet(tweet, user);
            } else if (nextInt == 2) {
                likeTweet(tweet, user);
            } else if (nextInt == 3) {
                System.out.println("Come back to previous menu...");
            } else System.out.println("You've entered an invalid number.");
        } catch (InputMismatchException ex) {
            System.out.println(ex.getMessage());
        }
    }


    /*Method for comment or remove comment*/
    private static void commentOnTweet(Tweet tweet, User user) {
        ApplicationContext.menu.commentMenu();
        int nextInt = ApplicationContext.getNumberScanner().nextInt();

        if (nextInt == 1) {

            System.out.println("Enter comment's text: ");
            String commentText = ApplicationContext.getStringScanner().nextLine();
            Comment comment = new Comment(commentText, user, tweet);
            ApplicationContext.getCommentService().save(comment);

        } else if (nextInt == 2) {

            deleteMyComment(tweet, user);

        }else if (nextInt==3){
            editMyComment(tweet, user);
        } else if (nextInt == 4) {

            System.out.println("Come back to previous menu...");

        } else System.out.println("You've entered an invalid number.");

    }

    private static void deleteMyComment(Tweet tweet, User user) {
        List<Comment> commentsOfAUserOnATweet =
                ApplicationContext.getCommentService().findCommentsOfAUserOnATweet(tweet.getId(), user.getId());
        int i = 1;


        boolean flag = true;
        while (flag) {
            for (Comment c : commentsOfAUserOnATweet) {
                System.out.println(i + ": " + c);
                /*System.out.print("Enter \"d\" to delete comment and any other word to continue... ");*/
            /*if (ApplicationContext.getStringScanner().next().equalsIgnoreCase("d")){
                ApplicationContext.getCommentService().deleteComment(tweet.getId(), user.getId());
                System.out.println("Comment successfully deleted.");
            }*/
            }
            if (commentsOfAUserOnATweet.size() > 0) {
                System.out.print("Press 1 to delete a comment and 2 for exit: ");
                int selectNumber = ApplicationContext.getNumberScanner().nextInt();

                if (selectNumber == 1) {
                    System.out.print("Enter the number of above comments to delete it: ");
                    int commentNumber = ApplicationContext.getNumberScanner().nextInt() - 1;
                    if (commentNumber < commentsOfAUserOnATweet.size() && commentNumber >= 0) {
                        ApplicationContext.getCommentService().
                                deleteById(commentsOfAUserOnATweet.get(commentNumber).getId());
                        commentsOfAUserOnATweet.remove(commentsOfAUserOnATweet.get(commentNumber));
                        System.out.println("Comment deleted successfully.");
                    } else System.out.println("Number is out of bound.");
                } else if (selectNumber == 2) {
                    flag = false;
                } else System.out.println("Enter valid number");

            } else {
                System.out.println("You don't have comment on this tweet.");
                flag = false;
            }

            System.out.println("===============================");
        }
    }


    private static void editMyComment(Tweet tweet, User user) {
        List<Comment> commentsOfAUserOnATweet =
                ApplicationContext.getCommentService().findCommentsOfAUserOnATweet(tweet.getId(), user.getId());
        int i = 1;


        boolean flag = true;
        while (flag) {
            for (Comment c : commentsOfAUserOnATweet) {
                System.out.println(i + ": " + c);
            }
            System.out.print("Press 1 to edit a comment and 2 for exit: ");
            int selectNumber = ApplicationContext.getNumberScanner().nextInt();

            if (selectNumber == 1) {
                System.out.print("Enter the number of above comments to edit it: ");
                int commentNumber = ApplicationContext.getNumberScanner().nextInt() - 1;
                if (commentNumber < commentsOfAUserOnATweet.size() && commentNumber >= 0) {
                    Comment comment = commentsOfAUserOnATweet.get(commentNumber);
                    System.out.println("Write your comment.");
                    String commentText = ApplicationContext.getStringScanner().nextLine();
                    comment.setComment(commentText);
                    ApplicationContext.getCommentService().save(comment);
                } else System.out.println("Number is out of bound.");
            } else if (selectNumber == 2) {
                flag = false;
            } else System.out.println("Enter valid number");

            System.out.println("===============================");
        }
    }


    /*Method for like or remove like*/
    private static void likeTweet(Tweet tweet, User user) {

        ApplicationContext.menu.likeMenu();
        int nextInt = ApplicationContext.getNumberScanner().nextInt();

        if (nextInt == 1) {
            if (ApplicationContext.getLikeService().findLike(tweet.getId(), user.getId()) == null) {
                LikeTweet like = new LikeTweet();
                like.setLiked(true);
                like.setUser(user);
                like.setTweet(tweet);
                ApplicationContext.getLikeService().save(like);
            } else {
                System.out.println("You liked this tweet before.");
            }

        } else if (nextInt == 2) {

            LikeTweet likeTweet = ApplicationContext.getLikeService().findLike(tweet.getId(), user.getId());
            if (likeTweet != null) {
                ApplicationContext.getLikeService().deleteById(likeTweet.getId());
                System.out.println("Your like successfully removed.");
            } else {
                System.out.println("You didn't like this tweet before.");
            }

        } else if (nextInt == 3) {
            System.out.println("Come back to previous menu...");

        } else System.out.println("You've entered an invalid number.");

    }


}
