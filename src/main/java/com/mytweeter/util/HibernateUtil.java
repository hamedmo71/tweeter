package com.mytweeter.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

    private static final EntityManagerFactory emf=
            Persistence.createEntityManagerFactory("default");

    private static EntityManager entityManager;

    private HibernateUtil() {
    }

    public static synchronized EntityManager getEntityManager(){
        if(entityManager ==null){
            entityManager =emf.createEntityManager();
        }
        return entityManager;
    }
}
