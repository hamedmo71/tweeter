package com.mytweeter.repository.impl;

import com.mytweeter.base.repository.impl.BaseRepositoryImpl;
import com.mytweeter.entity.Tweet;
import com.mytweeter.repository.TweetRepository;
import com.mytweeter.util.TweetSearch;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class TweetRepositoryImpl extends BaseRepositoryImpl<Tweet,Long> implements TweetRepository {

    public TweetRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Tweet> getEntityClass() {
        return Tweet.class;
    }

    @Override
    public List<Tweet> findTweetByUsername(String username) {
        List<Tweet> tweetList = entityManager.createQuery
                        ("select t from Tweet t where t.user.username=:username ", getEntityClass())
                .setParameter("username", username).getResultList();
        return tweetList;
    }

    @Override
    public List<Tweet> findTweetByUserId(Long userId) {
        List<Tweet> tweetList = entityManager.createQuery
                        ("select t from Tweet t where t.user.id=:id ", getEntityClass())
                .setParameter("id", userId).getResultList();
        return tweetList;
    }








    @Override
    public List<Tweet> findTweetByTweetsProperty(TweetSearch tweetSearch) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tweet> criteriaQuery = criteriaBuilder.createQuery(Tweet.class);
        Root<Tweet> root = criteriaQuery.from(Tweet.class);

        List<Predicate> predicates=new ArrayList<>();
        addFirstName(predicates , criteriaBuilder , root , tweetSearch.getFirstName());
        addLastName(predicates , criteriaBuilder , root , tweetSearch.getLastName());
        addUsername(predicates , criteriaBuilder , root , tweetSearch.getUsername());
        addTweetContext(predicates , criteriaBuilder , root , tweetSearch.getTweetContext());

        criteriaQuery.where(
                criteriaBuilder.and(
                        predicates.toArray(new Predicate[0])
                )
        );

        return entityManager.createQuery(criteriaQuery).getResultList();
    }








    private void addTweetContext(List<Predicate> predicates, CriteriaBuilder criteriaBuilder,
                                 Root<Tweet> root, String tweetContext) {
        if (tweetContext!=null && !tweetContext.isEmpty()){
            predicates.add(criteriaBuilder.like(
                    root.get("tweetContext"),"%"+tweetContext+"%"
            ));
        }
    }

    private void addUsername(List<Predicate> predicates, CriteriaBuilder criteriaBuilder,
                             Root<Tweet> root, String username) {
        if (username!=null && !username.isEmpty()){
            predicates.add(criteriaBuilder.like(
                    root.get("user").get("username"),"%"+username+"%"
            ));
        }
    }

    private void addLastName(List<Predicate> predicates, CriteriaBuilder criteriaBuilder,
                             Root<Tweet> root, String lastName) {
        if (lastName!=null && !lastName.isEmpty()){
            predicates.add(criteriaBuilder.like(
                    root.get("user").get("lastName"),"%"+lastName+"%"
            ));
        }
    }

    private void addFirstName(List<Predicate> predicates, CriteriaBuilder criteriaBuilder,
                              Root<Tweet> root, String firstName) {

        if(firstName!=null && !firstName.isEmpty()){
            predicates.add(criteriaBuilder.like(
                    root.get("user").get("firstName"), "%"+firstName+"%"
                    )
            );
        }
    }
}
