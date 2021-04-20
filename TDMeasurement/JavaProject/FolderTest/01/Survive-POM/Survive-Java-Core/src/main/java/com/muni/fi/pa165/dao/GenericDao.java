package com.muni.fi.pa165.dao;

import java.util.List;

/**
 *
 * @author Aubrey Oosthuizen
 *
 * GenericDao class that acts as Interface for all JPA DAO objects. Contains
 * CRUD operations on generic types T
 * 
 * @throws IllegalArgumentException when null is passed
 */
public interface GenericDao<T, ID> {

    /**
     *
     * @param entity
     * @return
     * @throws IllegalArgumentException
     */
    T save(T entity)throws IllegalArgumentException;

    /**
     *
     * @param entity
     * @return
     */
    T update(T entity);

    /**
     *
     * @param entity
     */
    void delete(T entity);

    /**
     *
     * @param id
     */
    void delete(Long id);

    /**
     *
     * @param id
     * @return
     */
    T findById(ID id);

    /**
     *
     * @return
     */
    List<T> findAll();

    /**
     *
     */
    void flush();
}
