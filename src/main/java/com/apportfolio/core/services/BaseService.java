package com.apportfolio.core.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.apportfolio.core.models.entities.Base;

public interface BaseService<E extends Base, ID extends Serializable> {
    public List<E> findAll();
    public Page<E> findAll(Pageable pageable);
    public E findById(ID id);
    public E save(E entity);
    public E update(ID id, E entity);
    public boolean delete(ID id);
}
