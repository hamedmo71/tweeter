package com.mytweeter.repository;

import com.mytweeter.base.repository.BaseRepository;
import com.mytweeter.entity.Tweet;
import com.mytweeter.util.TweetSearch;

import java.util.List;

public interface TweetRepository extends BaseRepository<Tweet,Long> {

    List<Tweet> findTweetByUsername(String username);

    List<Tweet> findTweetByTweetsProperty(TweetSearch tweetSearch);

    List<Tweet> findTweetByUserId(Long userId);
}
