package com.mytweeter.base.service;

import com.mytweeter.base.entity.BaseEntity;
import com.mytweeter.base.repository.BaseRepository;

import java.io.Serializable;
import java.util.List;

public interface BaseService<E extends BaseEntity<ID>,ID extends Serializable>{

    E save(E e);

    E findById(ID id);

    void deleteById(ID id);

    List<E> findAll();

}
