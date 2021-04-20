package com.pa165.mlib.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author xbek
 */
public class EntityTestBase {
    
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("testPU");
    
    public EntityManager getTestEntityManager() {
        return emf.createEntityManager();
    }
    
}
