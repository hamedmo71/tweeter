package com.mytweeter.service.impl;

import com.mytweeter.base.service.impl.BaseServiceImpl;
import com.mytweeter.entity.Tweet;
import com.mytweeter.repository.TweetRepository;
import com.mytweeter.service.TweetService;
import com.mytweeter.util.TweetSearch;

import java.util.ArrayList;
import java.util.List;

public class TweetServiceImpl extends BaseServiceImpl<Tweet,Long, TweetRepository> implements TweetService {
    public TweetServiceImpl(TweetRepository repository) {
        super(repository);
    }

    @Override
    public List<Tweet> findTweetByUsername(String username) {
        List<Tweet> tweetList=new ArrayList<>();
        try {
            tweetList=repository.findTweetByUsername(username);
        }catch (Exception ex){
            System.out.println("This user have not tweet yet.");
        }
        return tweetList;
    }

    @Override
    public List<Tweet> searchTweet(TweetSearch tweetSearch) {
        List<Tweet> tweetList=new ArrayList<>();
        try {
            tweetList=repository.findTweetByTweetsProperty(tweetSearch);
        }catch (Exception ex){
            System.out.println("There is no tweet!!!");
        }
        return tweetList;
    }

    @Override
    public List<Tweet> findTweetByUserId(Long userId) {

        List<Tweet> tweetList=new ArrayList<>();
        try {
            tweetList=repository.findTweetByUserId(userId);
        }catch (Exception ex){
            System.out.println("There is no tweet!!!");
        }
        return tweetList;
    }
}
