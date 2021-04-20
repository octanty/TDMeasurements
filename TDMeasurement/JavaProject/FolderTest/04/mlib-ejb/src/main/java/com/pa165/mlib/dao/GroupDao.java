package com.pa165.mlib.dao;

import com.pa165.mlib.entity.Group;
import com.pa165.mlib.entity.User;
import com.pa165.mlib.exception.DuplicateException;
import java.util.List;

/**
 *
 * @author ibek
 */
public interface GroupDao {

    /**
     * Persists the given group to persistence context
     * @param group
     */
    void addGroup(Group group) throws DuplicateException;

    /**
     * Read all groups
     * @return
     */
    List<Group> getAll();

    /**
     * Read groups for a user
     * @param user
     * @return
     */
    List<Group> getGroups(User user);

    /**
     * Remove the given group from persistence context
     * @param group
     */
    void removeGroup(Group group);
    
}
