package by.horunzhyn.godel.service;


import by.horunzhyn.godel.entity.BaseEntity;

import java.util.List;
import java.util.Optional;

public interface BaseService<T extends BaseEntity> {

    /**
     * finds entity by id
     */
    Optional<T> findOne(Long id);

    /**
     * finds entity by id
     */
    List<T> findAllById(List<Long> ids);

    /**
     * finds all entities
     */
    List<T> findAll();

    /**
     * saves entity
     */
    T save(T entity);

    /**
     * deletes entity by id
     */
    void delete(Long id);

    /**
     * deletes entity by entity
     */
    void delete(T entity);
}