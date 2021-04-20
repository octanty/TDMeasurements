package com.pa165.mlib.test.service;

import com.pa165.mlib.dao.GenreDao;
import com.pa165.mlib.dao.GroupDao;
import com.pa165.mlib.dao.UserDao;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.pa165.mlib.dto.GenreTO;
import com.pa165.mlib.dto.Role;
import com.pa165.mlib.dto.UserTO;
import com.pa165.mlib.entity.Genre;
import com.pa165.mlib.entity.User;
import com.pa165.mlib.service.impl.GenreServiceImpl;
import com.pa165.mlib.service.impl.UserServiceImpl;
import com.pa165.mlib.utils.EntityDTOTransformer;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author xbek
 */
public class UserServiceTest {
    
    @Test
    public void createNewUserTest() throws Exception {
        UserServiceImpl us = new UserServiceImpl();
        EntityDTOTransformer transformer = new EntityDTOTransformer();
        us.setTransformer(transformer);
        
        UserDao ud = mock(UserDao.class);
        GroupDao gd = mock(GroupDao.class);
        User lukov = new User();
        lukov.setUsername("Lukov");
        when(ud.getUser("Yaniv")).thenReturn(null);
        when(ud.getUser("Lukov")).thenReturn(lukov);
        us.setUserDao(ud);
        us.setGroupDao(gd);
        
        UserTO userto = new UserTO();
        userto.setUsername("Lukov");
        userto.setPassword("heslo");
        UserTO createdUser = us.createNewUser(userto, Role.ADMIN);
        
        assertEquals(createdUser.getUsername(), "Lukov");
    }
    
    @Test
    public void removeUserTest() throws Exception {
        UserServiceImpl us = new UserServiceImpl();
        EntityDTOTransformer transformer = new EntityDTOTransformer();
        us.setTransformer(transformer);
        
        UserDao ud = mock(UserDao.class);
        User yaniv = new User();
        yaniv.setUsername("Yaniv");
        when(ud.getUser("Yaniv")).thenReturn(yaniv);
        when(ud.getUser("Lukov")).thenReturn(null);
        us.setUserDao(ud);
        
        UserTO userto = new UserTO();
        userto.setUsername("Yaniv");
        
        boolean removed = us.removeUser(userto);
        assertTrue(removed);
    }
    
    @Test
    public void getUserTest() throws Exception {
        UserServiceImpl us = new UserServiceImpl();
        EntityDTOTransformer transformer = new EntityDTOTransformer();
        us.setTransformer(transformer);
        
        UserDao ud = mock(UserDao.class);
        User yaniv = new User();
        yaniv.setUsername("Yaniv");
        when(ud.getUser("Yaniv")).thenReturn(yaniv);
        when(ud.getUser("Lukov")).thenReturn(null);
        us.setUserDao(ud);
        
        UserTO foundUser = us.getUser("Yaniv");
        assertEquals(foundUser.getUsername(), "Yaniv");
    }
    
    @Test
    public void getAllUsersTest() throws Exception {
        UserServiceImpl us = new UserServiceImpl();
        EntityDTOTransformer transformer = new EntityDTOTransformer();
        us.setTransformer(transformer);
        
        UserDao ud = mock(UserDao.class);
        when(ud.getAll()).thenReturn(new ArrayList<User>(){{
            User user = new User();
            user.setUsername("albrecht");
            add(user);
            User user2 = new User();
            user2.setUsername("francimore");
            add(user2);
        }});
        us.setUserDao(ud);
        
        List<UserTO> all = us.getAllUsers();
        assertEquals(2, all.size());
        assertEquals("albrecht", all.get(0).getUsername());
        assertEquals("francimore", all.get(1).getUsername());
    }
}
