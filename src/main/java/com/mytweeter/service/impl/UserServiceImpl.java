package com.mytweeter.service.impl;

import com.mytweeter.base.service.impl.BaseServiceImpl;
import com.mytweeter.entity.User;
import com.mytweeter.repository.UserRepository;
import com.mytweeter.service.UserService;
import com.mytweeter.util.UserSearch;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl extends BaseServiceImpl<User,Long, UserRepository> implements UserService {
    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }

    @Override
    public User loginWithUsernameAndPassword(String username, String password) {
        User user=null;
        try {
            user = repository.findByUsernameAndPassword(username, password);
        }catch (Exception ex){
            System.out.println("Wrong username or password!!!");
        }
        return user;
    }

    @Override
    public User findUserWithUsername(String username) {
        User user=null;
        try {
            user = repository.findByUsername(username);
        }catch (Exception ex){
            System.out.println("Wrong username!!!");
        }
        return user;
    }

    @Override
    public List<User> searchUser(UserSearch userSearch) {
        List<User> userList=new ArrayList<>();
        try {
            userList = repository.findUserByUsersProperty(userSearch);
        }catch (Exception e){
            System.out.println("There is not any user with this features!");
        }

        return userList;
    }
}
