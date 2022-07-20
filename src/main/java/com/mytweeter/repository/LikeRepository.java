package com.mytweeter.repository;

import com.mytweeter.base.repository.BaseRepository;
import com.mytweeter.entity.LikeTweet;

public interface LikeRepository extends BaseRepository<LikeTweet,Long> {

    void deleteLike(Long tweetId,Long userId);

    LikeTweet findLike(Long tweetId,Long userId);

    void deleteLikeByTweetId(Long tweetId);

    Long countTweetsLikes(Long tweetId);
}
