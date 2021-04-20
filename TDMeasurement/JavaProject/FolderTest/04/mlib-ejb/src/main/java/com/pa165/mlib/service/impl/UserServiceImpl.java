package com.pa165.mlib.service.impl;

import com.pa165.mlib.dao.GroupDao;
import com.pa165.mlib.dao.UserDao;
import com.pa165.mlib.dto.Role;
import com.pa165.mlib.dto.UserTO;
import com.pa165.mlib.entity.Group;
import com.pa165.mlib.entity.User;
import com.pa165.mlib.exception.DuplicateException;
import com.pa165.mlib.service.UserService;
import com.pa165.mlib.utils.EntityDTOTransformer;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

/**
 *
 * @author xbek
 */
@MlibService
@Stateless
@DeclareRoles({"ADMIN", "USER"})
@RolesAllowed("ADMIN")
public class UserServiceImpl implements UserService {
    
    @Inject
    UserDao ud;
    
    @Inject
    GroupDao gd;
    
    @Inject
    EntityDTOTransformer transformer;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public UserTO createNewUser(UserTO user, Role role) throws DuplicateException {
        User u = new User();
        u.setUsername(user.getUsername());
        try {
            byte[] hash = MessageDigest.getInstance("sha-256").digest(user.getPassword().getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
            }
            u.setPasswordHash(sb.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        ud.addUser(u);
        Group g = new Group();
        g.setRole(role);
        g.setUser(u);
        gd.addGroup(g);
        return transformer.transformUserTO(u);
    }

    @Override
    public List<UserTO> getAllUsers() {
        List<UserTO> list = new ArrayList<>();
        for (User u : ud.getAll()) {
            list.add(transformer.transformUserTO(u));
        }
        return list;
    }

    @Override
    @PermitAll
    public UserTO getUser(String username) {
        return transformer.transformUserTO(ud.getUser(username));
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean removeUser(UserTO userTO) {
        User user = ud.getUser(userTO.getUsername());
        if (user == null) {
            return false;
        }
        ud.removeUser(user);
        return true;
    }
    
    public void setUserDao(UserDao userDao) {
        this.ud = userDao;
    }
    
    public void setGroupDao(GroupDao groupDao) {
        this.gd = groupDao;
    }
    
    public void setTransformer(EntityDTOTransformer transformer) {
        this.transformer = transformer;
    }
    
}
