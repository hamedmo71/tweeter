package com.mytweeter.repository;

import com.mytweeter.base.repository.BaseRepository;
import com.mytweeter.entity.Tweet;
import com.mytweeter.entity.User;
import com.mytweeter.util.TweetSearch;
import com.mytweeter.util.UserSearch;

import java.util.List;

public interface UserRepository extends BaseRepository<User,Long> {

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

    List<User> findUserByUsersProperty(UserSearch userSearch);



}
