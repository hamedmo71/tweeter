package com.mytweeter.repository.impl;

import com.mytweeter.base.repository.impl.BaseRepositoryImpl;
import com.mytweeter.entity.User;
import com.mytweeter.repository.UserRepository;

import javax.persistence.EntityManager;


public class UserRepositoryImpl extends BaseRepositoryImpl<User,Long> implements UserRepository {
    public UserRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public User findByUsername(String username) {
        User user = entityManager.createQuery("select u from User u where username=:username", getEntityClass())
                .setParameter("username", username).getSingleResult();
        return user;
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user = entityManager.createQuery
                        ("select u from User u where username=:username and password=:password", getEntityClass())
                .setParameter("username", username).setParameter("password",password).getSingleResult();
        return user;
    }
}
