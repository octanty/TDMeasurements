package com.pa165.mlib.test.entity;

import com.pa165.mlib.dao.impl.GroupDaoImpl;
import com.pa165.mlib.dao.impl.UserDaoImpl;
import com.pa165.mlib.dto.Role;
import com.pa165.mlib.entity.Group;
import com.pa165.mlib.entity.User;
import com.pa165.mlib.test.EntityTestBase;
import java.util.List;
import javax.persistence.EntityManager;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author xbek
 */
public class GroupEntityTest extends EntityTestBase {
    
    @Test
    public void testAddGroup() throws Throwable {
        GroupDaoImpl gd = new GroupDaoImpl();
        EntityManager em = getTestEntityManager();
        gd.setEntityManager(em);
        
        UserDaoImpl ud = new UserDaoImpl();
        ud.setEntityManager(em);
        ud.setGroupDao(gd);
        
        User user = new User();
        user.setUsername("yann");
        
        Group group = new Group();
        group.setRole(Role.ADMIN);
        group.setUser(user);
        
        em.getTransaction().begin();
        ud.addUser(user);
        gd.addGroup(group);
        em.getTransaction().commit();
        
        List<Group> foundGroups = gd.getGroups(user);
        assertTrue(foundGroups.contains(group));
    }
    
    @Test
    public void testGroupRemove() throws Throwable {
        GroupDaoImpl gd = new GroupDaoImpl();
        EntityManager em = getTestEntityManager();
        gd.setEntityManager(em);
        
        UserDaoImpl ud = new UserDaoImpl();
        ud.setEntityManager(em);
        ud.setGroupDao(gd);
        
        User user = new User();
        user.setUsername("hugues");
        
        Group group = new Group();
        group.setRole(Role.ADMIN);
        group.setUser(user);
        
        em.getTransaction().begin();
        ud.addUser(user);
        gd.addGroup(group);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        gd.removeGroup(group);
        em.getTransaction().commit();
        
        List<Group> foundGroups = gd.getGroups(user);
        assertTrue(!foundGroups.contains(group));
    }
    
    @Test
    public void testGetAll() throws Throwable {
        GroupDaoImpl gd = new GroupDaoImpl();
        EntityManager em = getTestEntityManager();
        gd.setEntityManager(em);
        
        UserDaoImpl ud = new UserDaoImpl();
        ud.setEntityManager(em);
        ud.setGroupDao(gd);
        
        User user = new User();
        user.setUsername("gregory");
        User user2 = new User();
        user2.setUsername("yaniv");
        
        Group group = new Group();
        group.setRole(Role.ADMIN);
        group.setUser(user);
        Group group2 = new Group();
        group2.setRole(Role.USER);
        group2.setUser(user2);
        
        em.getTransaction().begin();
        ud.addUser(user);
        ud.addUser(user2);
        gd.addGroup(group);
        gd.addGroup(group2);
        em.getTransaction().commit();
        
        List<Group> list = gd.getAll();
        assertTrue(list != null);
        assertTrue(list.contains(group));
        assertTrue(list.contains(group2));
    }
    
    @Test
    public void testGetGroups() throws Throwable {
        GroupDaoImpl gd = new GroupDaoImpl();
        EntityManager em = getTestEntityManager();
        gd.setEntityManager(em);
        
        UserDaoImpl ud = new UserDaoImpl();
        ud.setEntityManager(em);
        ud.setGroupDao(gd);
        
        User user = new User();
        user.setUsername("bartolomy");
        
        Group group = new Group();
        group.setRole(Role.USER);
        group.setUser(user);
        
        em.getTransaction().begin();
        ud.addUser(user);
        gd.addGroup(group);
        em.getTransaction().commit();
        
        List<Group> foundGroups = gd.getGroups(user);
        assertTrue(foundGroups.contains(group));
    }
    
    
    
}
