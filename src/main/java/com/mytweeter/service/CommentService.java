package com.mytweeter.service;

import com.mytweeter.base.service.BaseService;
import com.mytweeter.entity.Comment;

import java.util.List;

public interface CommentService extends BaseService<Comment,Long> {

    void deleteComment(Long tweetId,Long userId);

    void deleteCommentByTweetId(Long tweetId);

    Long countTweetsComment(Long tweetId);


    List<Comment> showCommentsOfATweet(Long tweetId);

    List<Comment> findCommentsOfAUserOnATweet(Long tweetId,Long userId);
}
