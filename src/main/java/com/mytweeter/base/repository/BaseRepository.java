package com.mytweeter.base.repository;

import com.mytweeter.base.entity.BaseEntity;

import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.util.List;

public interface BaseRepository<E extends BaseEntity<ID>,ID extends Serializable> {

    E save(E e);

    E findById(ID id);

    void deleteById(ID id);

    List<E> findAll();

    void beginTransaction();

    void commitTransaction();

    void rollbackTransaction();

    EntityTransaction getEntityTransaction();
}
