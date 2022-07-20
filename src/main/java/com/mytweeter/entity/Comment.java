package com.mytweeter.entity;

import com.mytweeter.base.entity.BaseEntity;
import org.hibernate.annotations.Cascade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Comment extends BaseEntity<Long> {

    @Column(length = 280)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private User user;

    @ManyToOne
    @JoinColumn(name = "tweet_id", nullable = false)
    private Tweet tweet;

    public Tweet getTweet() {
        return tweet;
    }

    public void setTweet(Tweet tweet) {
        this.tweet = tweet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment() {
    }

    public Comment(String comment) {
        this.comment = comment;
    }

    public Comment(String comment, User user, Tweet tweet) {
        this.comment = comment;
        this.user = user;
        this.tweet = tweet;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return comment ;
    }
}
