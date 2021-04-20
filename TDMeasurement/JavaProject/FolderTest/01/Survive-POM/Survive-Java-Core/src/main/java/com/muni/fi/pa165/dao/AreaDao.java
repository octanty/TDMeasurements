package com.muni.fi.pa165.dao;

import com.muni.fi.pa165.entities.Area;

/**
 * Interface implemented by typed JpaDao objects containing type specific
 * methods.
 *
 * @author Aubrey Oosthuizen
 * 
 * @throws IllegalArgumentException when null is passed
 */
public interface AreaDao extends GenericDao<Area, Long> {

    /**
     *
     * @param areaName
     * @return
     * @throws IllegalArgumentException
     */
    boolean checkAvailable(String areaName) throws IllegalArgumentException;
}
