package com.mytweeter.entity;

import com.mytweeter.base.entity.BaseEntity;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
public class Tweet extends BaseEntity<Long> {

    @Column(length = 280)
    private String tweetContext;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private User user;

    @OneToMany(mappedBy = "tweet")
    private List<Comment> comments;

    @OneToMany(mappedBy = "tweet")
    private List<LikeTweet> likeTweets;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tweet() {
    }

    public Tweet(String tweetContext) {
        this.tweetContext = tweetContext;
    }

    public String getTweetContext() {
        return tweetContext;
    }

    public void setTweetContext(String tweet) {
        this.tweetContext = tweet;
    }

    @Override
    public String toString() {
        return "{" + tweetContext +"}";
    }
}
