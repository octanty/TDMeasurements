package com.muni.fi.pa165.dao.impl;

import com.muni.fi.pa165.dao.WeaponDao;
import com.muni.fi.pa165.dao.gen.GenericDaoAbs;
import com.muni.fi.pa165.entities.Weapon;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 * Typed JpaDao objects extending the GenericDaoAbs abstract class and
 * implementing a Type specific interface. This Dao object will be used to
 * perform all operations within the business layer.
 *
 * @author Aubrey Oosthuizen 
 */
@Repository
public class WeaponDaoImpl extends GenericDaoAbs<Weapon, Long> implements WeaponDao {

    /**
     *
     */
    public WeaponDaoImpl() {
        super(Weapon.class);
    }

    /**
     *
     * @param name
     * @return
     */
    @Override
    public boolean checkAvailable(String name) {

        if (name == null) {
            throw new IllegalArgumentException("Null argument.");
        }

        System.out.println("Trying to look for weapon named " + name);
        EntityManager em = this.getEntityManagerFactory().createEntityManager();
        TypedQuery<Weapon> query = em.createQuery("select p from Weapon  p where p.name = :name", Weapon.class);
        query.setParameter("name", name);
        if (!query.getResultList().isEmpty()) {
            return true;
        }
        return false;
    }
}
