package com.mytweeter.entity;

import com.mytweeter.base.entity.BaseEntity;
import org.hibernate.annotations.Cascade;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class LikeTweet extends BaseEntity<Long> {

    private Boolean isLiked;

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

    public LikeTweet() {
    }

    public LikeTweet(Boolean isLiked) {
        this.isLiked = isLiked;
    }

    public Boolean getLiked() {
        return isLiked;
    }

    public void setLiked(Boolean liked) {
        isLiked = liked;
    }



    @Override
    public String toString() {
        return "Like{" +
                "isLiked=" + isLiked +
                '}';
    }
}
