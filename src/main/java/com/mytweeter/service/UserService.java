package com.mytweeter.service;

import com.mytweeter.base.service.BaseService;
import com.mytweeter.entity.User;

public interface UserService extends BaseService<User,Long> {

    User loginWithUsernameAndPassword(String username,String password);
    User findUserWithUsername(String username);
}
