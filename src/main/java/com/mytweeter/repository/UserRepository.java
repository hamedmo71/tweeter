package com.mytweeter.repository;

import com.mytweeter.base.repository.BaseRepository;
import com.mytweeter.entity.User;

public interface UserRepository extends BaseRepository<User,Long> {

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);



}
