package com.mytweeter.service.impl;

import com.mytweeter.base.service.impl.BaseServiceImpl;
import com.mytweeter.entity.Comment;
import com.mytweeter.repository.CommentRepository;
import com.mytweeter.service.CommentService;

import java.util.List;

public class CommentServiceImpl extends BaseServiceImpl<Comment,Long, CommentRepository> implements CommentService {
    public CommentServiceImpl(CommentRepository repository) {
        super(repository);
    }

    @Override
    public void deleteComment(Long tweetId, Long userId) {
        try {
            repository.beginTransaction();
            repository.deleteComment(tweetId,userId);
            repository.commitTransaction();
        }catch (Exception ex){
            System.out.println("Deletion Unsuccessful!!!");
            repository.rollbackTransaction();
        }
    }

    @Override
    public void deleteCommentByTweetId(Long tweetId) {
        try {
            repository.beginTransaction();
            repository.deleteCommentByTweetId(tweetId);
            repository.commitTransaction();
        }catch (Exception ex){
            System.out.println("Deletion Unsuccessful!!!");
            repository.rollbackTransaction();
        }
    }

    @Override
    public Long countTweetsComment(Long tweetId) {
        return repository.countTweetsComment(tweetId);
    }

    @Override
    public List<Comment> showCommentsOfATweet(Long tweetId) {
        List<Comment> commentsOnATweet =null;
        try {
            commentsOnATweet=repository.findCommentsOfATweet(tweetId);
        }catch (Exception e){
            System.out.println("This tweet does not have any comments.");
        }

        return commentsOnATweet;
    }

    @Override
    public List<Comment> findCommentsOfAUserOnATweet(Long tweetId, Long userId) {
        List<Comment> commentsOfAUserOnATweet =null;
        try {
            commentsOfAUserOnATweet=repository.findCommentsOfAUserOnATweet(tweetId, userId);
        }catch (Exception e){
            System.out.println("You didn't comment on this tweet before.");
        }
        return commentsOfAUserOnATweet;
    }
}
