package com.mytweeter.repository.impl;

import com.mytweeter.base.repository.impl.BaseRepositoryImpl;
import com.mytweeter.entity.LikeTweet;
import com.mytweeter.repository.LikeRepository;

import javax.persistence.EntityManager;

public class LikeRepositoryImpl extends BaseRepositoryImpl<LikeTweet,Long> implements LikeRepository {

    public LikeRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<LikeTweet> getEntityClass() {
        return LikeTweet.class;
    }

    @Override
    public void deleteLike(Long tweetId, Long userId) {
        entityManager.createQuery
                        ("delete from LikeTweet l " +
                                "where l.user.id=:userId and l.tweet.id=:tweetId")
                .setParameter("userId",userId).setParameter("tweetId",tweetId)
                .executeUpdate();
    }

    @Override
    public LikeTweet findLike(Long tweetId, Long userId) {
        LikeTweet singleResult = entityManager.createQuery
                        ("select l from LikeTweet l " +
                                "where l.user.id=:userId and l.tweet.id=:tweetId", entityClass)
                .setParameter("userId", userId).setParameter("tweetId", tweetId)
                .getSingleResult();
        return singleResult;
    }

    @Override
    public void deleteLikeByTweetId(Long tweetId) {
        entityManager.createQuery
                        ("delete from LikeTweet l " +
                                "where l.tweet.id=:tweetId")
                .setParameter("tweetId",tweetId)
                .executeUpdate();
    }

    @Override
    public Long countTweetsLikes(Long tweetId) {
        return entityManager.createQuery(
                "select count(l) from LikeTweet l where l.tweet.id=:id",
                Long.class
        ).setParameter("id",tweetId).getSingleResult();
    }
}
