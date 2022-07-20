package com.mytweeter.service;

import com.mytweeter.base.service.BaseService;
import com.mytweeter.entity.LikeTweet;

public interface LikeService extends BaseService<LikeTweet,Long> {

    void deleteLike(Long tweetId,Long userId);

    void deleteLikeByTweetId(Long tweetId);

    Long countTweetsLikes(Long tweetId);

    LikeTweet findLike(Long tweetId,Long userId);
}
