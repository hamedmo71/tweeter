package com.mytweeter.service;

import com.mytweeter.base.service.BaseService;
import com.mytweeter.entity.Tweet;
import com.mytweeter.util.TweetSearch;

import java.util.List;

public interface TweetService extends BaseService<Tweet,Long> {

    List<Tweet> findTweetByUsername(String username);

    List<Tweet> searchTweet(TweetSearch tweetSearch);

    List<Tweet> findTweetByUserId(Long userId);
}


