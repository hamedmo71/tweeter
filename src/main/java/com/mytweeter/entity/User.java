package com.mytweeter.entity;

import com.mytweeter.base.entity.BaseEntity;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class User extends BaseEntity<Long> {

    @Column(nullable = false/*,name = "first_name"*/)
    private String firstName;

    @Column(nullable = false/*,name = "last_name"*/)
    private String lastName;

    @Column(nullable = false, unique = true )
    private String username;

    @Column(nullable = false)
    private String password;

    private String bio;

    @OneToMany(mappedBy = "user")
    @Cascade(CascadeType.ALL)
    private List<Tweet> tweets;

    @OneToMany(mappedBy = "user")
    @Cascade(CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "user")
    @Cascade(CascadeType.ALL)
    private List<LikeTweet> likeTweets;



    public User() {
    }

    public User(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<LikeTweet> getLikes() {
        return likeTweets;
    }

    public void setLikes(List<LikeTweet> likeTweets) {
        this.likeTweets = likeTweets;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<LikeTweet> getLikeTweets() {
        return likeTweets;
    }

    public void setLikeTweets(List<LikeTweet> likeTweets) {
        this.likeTweets = likeTweets;
    }

    @Override
    public String toString() {
        return "User Profile: \n" +
                "\tfirstName: " + firstName  + "\n"+
                "\tlastName: " + lastName + "\n"+
                "\tusername: " + username + "\n"+
                "\tpassword: " + password + "\n"+
                "\tbio: " + bio;
    }
}
