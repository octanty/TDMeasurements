package com.pa165.mlib.service;

import com.pa165.mlib.dto.Role;
import com.pa165.mlib.dto.UserTO;
import com.pa165.mlib.exception.DuplicateException;
import java.util.List;

/**
 *
 * @author xbek
 */
public interface UserService {
    
    UserTO createNewUser(UserTO user, Role role) throws DuplicateException ;
    
    UserTO getUser(String name);
    
    boolean removeUser(UserTO user);
    
    List<UserTO> getAllUsers();
    
}
