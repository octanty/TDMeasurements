package com.pa165.mlib.dao;

import com.pa165.mlib.entity.User;
import com.pa165.mlib.exception.DuplicateException;
import java.util.List;

/**
 *
 * @author ibek
 */
public interface UserDao {

    /**
     * Persists the given user to persistence context
     * @param user
     */
    void addUser(User user) throws DuplicateException;

    /**
     * Read all users
     * @return
     */
    List<User> getAll();

    /**
     * Read user via username
     * @param username
     * @return
     */
    User getUser(String username);

    /**
     * Remove the given user from persistence context
     * @param user
     */
    void removeUser(User user);
    
}
