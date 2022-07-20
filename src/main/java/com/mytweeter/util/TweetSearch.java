package com.mytweeter.util;

public class TweetSearch {

    private String TweetContext;
    private String username;
    private String firstName;
    private String lastName;

    public String getTweetContext() {
        return TweetContext;
    }

    public void setTweetContext(String tweetContext) {
        TweetContext = tweetContext;
    }

    public TweetSearch(String tweetContext, String username, String firstName, String lastName) {
        TweetContext = tweetContext;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
