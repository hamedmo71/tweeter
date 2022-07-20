package com.mytweeter.repository;

import com.mytweeter.base.repository.BaseRepository;
import com.mytweeter.entity.Comment;

import java.util.List;

public interface CommentRepository extends BaseRepository<Comment,Long> {

    void deleteComment(Long tweetId,Long userId);

    void deleteCommentByTweetId(Long tweetId);

    Long countTweetsComment(Long tweetId);

    List<Comment> findCommentsOfATweet(Long tweetId);

    List<Comment> findCommentsOfAUserOnATweet(Long tweetId,Long userId);
}
