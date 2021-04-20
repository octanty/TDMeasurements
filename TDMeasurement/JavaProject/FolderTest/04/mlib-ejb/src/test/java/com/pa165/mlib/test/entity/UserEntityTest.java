package com.pa165.mlib.test.entity;

import com.pa165.mlib.dao.impl.GenreDaoImpl;
import com.pa165.mlib.dao.impl.UserDaoImpl;
import com.pa165.mlib.dao.impl.GroupDaoImpl;
import com.pa165.mlib.entity.Genre;
import com.pa165.mlib.entity.User;
import com.pa165.mlib.test.EntityTestBase;
import java.util.List;
import javax.persistence.EntityManager;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author xbek
 */
public class UserEntityTest extends EntityTestBase {
    
    @Test
    public void testAddUser() throws Throwable {
        UserDaoImpl ud = new UserDaoImpl();
        EntityManager em = getTestEntityManager();
        ud.setEntityManager(em);
        
        User peter = new User();
        peter.setUsername("peterParker");
        peter.setPasswordHash("04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb");
        
        em.getTransaction().begin();
        ud.addUser(peter);
        em.getTransaction().commit();
        
        User user = ud.getUser("peterParker");
        assertEquals(peter, user);
    }
    
    @Test
    public void testGetAll() throws Throwable {
        UserDaoImpl ud = new UserDaoImpl();
        EntityManager em = getTestEntityManager();
        ud.setEntityManager(em);
        
        User user = new User();
        user.setUsername("augustus");
        User user2 = new User();
        user2.setUsername("alfons");
        
        em.getTransaction().begin();
        ud.addUser(user);
        ud.addUser(user2);
        em.getTransaction().commit();
        
        List<User> list = ud.getAll();
        assertTrue(list != null);
        assertTrue(list.contains(user));
        assertTrue(list.contains(user2));
    }
    
    @Test
    public void testGetUser() throws Throwable {
        UserDaoImpl ud = new UserDaoImpl();
        EntityManager em = getTestEntityManager();
        ud.setEntityManager(em);
        
        User user = new User();
        user.setUsername("hugo");
        
        em.getTransaction().begin();
        ud.addUser(user);
        em.getTransaction().commit();
        User foundUser = ud.getUser("hugo");
        assertEquals(foundUser, user);
    }
    
    @Test
    public void testUserRemove() throws Throwable {
        UserDaoImpl ud = new UserDaoImpl();
        EntityManager em = getTestEntityManager();
        ud.setEntityManager(em);
        GroupDaoImpl gd = new GroupDaoImpl();
        gd.setEntityManager(em);
        ud.setGroupDao(gd);
        
        User user = new User();
        user.setUsername("emmanuel");
        
        em.getTransaction().begin();
        ud.addUser(user);
        em.getTransaction().commit();
        
        User foundUser = ud.getUser("emmanuel");
        assertEquals(foundUser, user);
        
        em.getTransaction().begin();
        ud.removeUser(foundUser);
        em.getTransaction().commit();
        
        User foundUser2 = ud.getUser(user.getUsername());
        assertNull(foundUser2);
    }
    
    
}
