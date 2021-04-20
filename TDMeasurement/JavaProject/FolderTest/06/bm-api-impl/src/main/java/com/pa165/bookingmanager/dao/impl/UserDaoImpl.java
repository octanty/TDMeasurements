package com.pa165.bookingmanager.dao.impl;

import com.pa165.bookingmanager.dao.UserDao;
import com.pa165.bookingmanager.entity.UserEntity;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

/**
 * @author Jana Polakova
 */
@Repository("userDao")
public class UserDaoImpl extends GenericDaoImpl<UserEntity, Long> implements UserDao
{
    /**
     * Constructor
     */
    public UserDaoImpl(){
        super(UserEntity.class);
    }

    /**
     * Find one by email
     *
     * @param email user email
     * @return UserEntity
     */
    @Override
    public UserEntity findOneByEmail(String email) throws DataAccessException {
        return (UserEntity) getCurrentSession()
            .createCriteria(UserEntity.class)
            .add(Restrictions.eq("email", email))
            .uniqueResult()
        ;
    }
}
