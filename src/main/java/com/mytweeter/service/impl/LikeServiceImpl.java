package com.mytweeter.service.impl;

import com.mytweeter.base.service.impl.BaseServiceImpl;
import com.mytweeter.entity.LikeTweet;
import com.mytweeter.repository.LikeRepository;
import com.mytweeter.service.LikeService;

public class LikeServiceImpl extends BaseServiceImpl<LikeTweet,Long, LikeRepository> implements LikeService {
    public LikeServiceImpl(LikeRepository repository) {
        super(repository);
    }

    @Override
    public void deleteLike(Long tweetId, Long userId) {
        try {
            repository.beginTransaction();
            repository.deleteLike(tweetId,userId);
            repository.commitTransaction();
        }catch (Exception ex){
            System.out.println("Deletion Unsuccessful!!!");
            repository.rollbackTransaction();
        }
    }

    @Override
    public void deleteLikeByTweetId(Long tweetId) {
        try {
            repository.beginTransaction();
            repository.deleteLikeByTweetId(tweetId);
            repository.commitTransaction();
        }catch (Exception ex){
            System.out.println("Deletion Unsuccessful!!!");
            repository.rollbackTransaction();
        }
    }

    @Override
    public Long countTweetsLikes(Long tweetId) {
        repository.beginTransaction();
        Long countTweetsLikes = repository.countTweetsLikes(tweetId);
        repository.commitTransaction();
        return countTweetsLikes;
    }

    @Override
    public LikeTweet findLike(Long tweetId, Long userId) {
        LikeTweet likeTweet;
        try {
            likeTweet=repository.findLike(tweetId,userId);
        }catch (Exception e){
            likeTweet=null;
        }
        return likeTweet;
    }
}
