package com.mytweeter.repository.impl;

import com.mytweeter.base.repository.impl.BaseRepositoryImpl;
import com.mytweeter.entity.Tweet;
import com.mytweeter.entity.User;
import com.mytweeter.repository.UserRepository;
import com.mytweeter.util.UserSearch;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


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

    @Override
    public List<User> findUserByUsersProperty(UserSearch userSearch) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);

        List<Predicate> predicates=new ArrayList<>();
        addFirstName(predicates , criteriaBuilder , root , userSearch.getFirstName());
        addLastName(predicates , criteriaBuilder , root , userSearch.getLastName());
        addUsername(predicates , criteriaBuilder , root , userSearch.getUsername());


        criteriaQuery.where(
                criteriaBuilder.and(
                        predicates.toArray(new Predicate[0])
                )
        );

        return entityManager.createQuery(criteriaQuery).getResultList();
    }


    private void addUsername(List<Predicate> predicates, CriteriaBuilder criteriaBuilder,
                             Root<User> root, String username) {
        if (username!=null && !username.isEmpty()){
            predicates.add(criteriaBuilder.like(
                    root.get("username"),"%"+username+"%"
            ));
        }
    }

    private void addLastName(List<Predicate> predicates, CriteriaBuilder criteriaBuilder,
                             Root<User> root, String lastName) {
        if (lastName!=null && !lastName.isEmpty()){
            predicates.add(criteriaBuilder.like(
                    root.get("lastName"),"%"+lastName+"%"
            ));
        }
    }

    private void addFirstName(List<Predicate> predicates, CriteriaBuilder criteriaBuilder,
                              Root<User> root, String firstName) {

        if(firstName!=null && !firstName.isEmpty()){
            predicates.add(criteriaBuilder.like(
                            root.get("firstName"), "%"+firstName+"%"
                    )
            );
        }
    }
}
