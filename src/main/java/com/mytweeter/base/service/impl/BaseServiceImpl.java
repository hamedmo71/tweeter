package com.mytweeter.base.service.impl;

import com.mytweeter.base.entity.BaseEntity;
import com.mytweeter.base.repository.BaseRepository;
import com.mytweeter.base.service.BaseService;

import java.io.Serializable;
import java.util.List;

public abstract class BaseServiceImpl<E extends BaseEntity<ID>, ID extends Serializable, R extends BaseRepository<E, ID>>
        implements BaseService<E,ID> {

    protected final R repository;

    public BaseServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public E save(E e) {
        try {
            repository.beginTransaction();
            e=repository.save(e);
            repository.commitTransaction();
            return e;
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            repository.rollbackTransaction();
            throw exception;
        }
    }

    @Override
    public E findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(ID id) {
        try {
            repository.beginTransaction();
            repository.deleteById(id);
            repository.commitTransaction();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            repository.rollbackTransaction();
            throw ex;
        }
    }

    @Override
    public List<E> findAll() {
        return repository.findAll();
    }
}
