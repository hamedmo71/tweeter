package com.mytweeter.repository.impl;

import com.mytweeter.base.repository.BaseRepository;
import com.mytweeter.base.repository.impl.BaseRepositoryImpl;
import com.mytweeter.entity.Comment;
import com.mytweeter.repository.CommentRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class CommentRepositoryImpl extends BaseRepositoryImpl<Comment,Long> implements CommentRepository {

    public CommentRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Comment> getEntityClass() {
        return Comment.class;
    }

    @Override
    public void deleteComment(Long tweetId, Long userId) {
        entityManager.createQuery
                ("delete from Comment c " +
                        "where c.user.id=:userId and c.tweet.id=:tweetId")
                .setParameter("userId",userId).setParameter("tweetId",tweetId)
                .executeUpdate();
    }

    @Override
    public void deleteCommentByTweetId(Long tweetId) {
        entityManager.createQuery
                        ("delete from Comment c " +
                                "where c.tweet.id=:tweetId")
                .setParameter("tweetId",tweetId)
                .executeUpdate();
    }

    @Override
    public Long countTweetsComment(Long tweetId) {
        return entityManager.createQuery(
                "select count(c) from Comment c where c.tweet.id=:id",
                Long.class
        ).setParameter("id",tweetId).getSingleResult();
    }

    @Override
    public List<Comment> findCommentsOfATweet(Long tweetId) {
        List<Comment> commentList = entityManager.createQuery("select c from Comment c where c.tweet.id=:tweetId",
                        Comment.class)
                .setParameter("tweetId", tweetId).getResultList();
        return commentList;
    }

    @Override
    public List<Comment> findCommentsOfAUserOnATweet(Long tweetId, Long userId) {
        List<Comment> commentList = entityManager.createQuery("select c from Comment c " +
                                "where c.tweet.id=:tweetId and c.user.id=:userId",
                        Comment.class)
                .setParameter("tweetId", tweetId).setParameter("userId",userId).getResultList();
        return commentList;
    }
}
