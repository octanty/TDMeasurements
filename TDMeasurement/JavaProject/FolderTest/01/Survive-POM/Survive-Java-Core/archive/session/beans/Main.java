/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muni.fi.pa165.session.beans;

import com.muni.fi.pa165.dao.service.WeaponService;
import com.muni.fi.pa165.dao.service.impl.WeaponServiceImpl;
import com.muni.fi.pa165.dto.WeaponDto;
//import javax.interceptor.Interceptor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Aubrey Oosthuizen
 */
public class Main {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
//    @PersistenceContext(unitName = "ProjectPU")
    public static void main(String args[]) {

//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjectPU");
////        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
//        EntityManager em = emf.createEntityManager();
//
//        System.out.println("MAIN__________________");
//        Weapon weapon = new Weapon();
//        weapon.setName("AK48");
//        weapon.setCaliber(44);
//        weapon.setDescription("Very cool");
//        weapon.setRounds(44);
//
//        em.getTransaction().begin();
//        WeaponJpaDaoImpl instance = new WeaponJpaDaoImpl();
//        instance.setEntityManager(em);
//        instance.save(weapon);
//
//        Area area = new Area();
//        area.setDescription("Nice place");
//        area.setName("AA23");
//        area.setTerrain(TerrainType.JUNGLE);
//
//        AreaJpaDaoImpl areaDao = new AreaJpaDaoImpl();
//        areaDao.setEntityManager(em);
//        areaDao.save(area);
//        
//        em.getTransaction().commit();
//        
//        Weapon w2= em.find(Weapon.class, weapon.getId());
//        
//        System.out.println(w2);
//        
//        em.close();


        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        WeaponService weaponService = context.getBean(WeaponServiceImpl.class);
        WeaponService weaponService =  context.getBean(WeaponService.class);
        
        try {
            
//            weaponService.save(null);
            
             weaponService.checkAvailable(null);
        } catch (DataAccessException e) {
            System.out.println("__________________________EXCEPTION: " + e.toString());
        }
        
       
        
        System.out.println("MAIN__________________");
//        WeaponDto weaponDto = new WeaponDto();
//        weaponDto.setName("AK49");
//        weaponDto.setCaliber(1D);
//        weaponDto.setDescription("Very bad");
//        weaponDto.setRounds(44);
//
//        weaponDto = weaponService.save(weaponDto);
//
//       System.out.println(weaponDto.getId());
       
       

    }
    
    
    
    /**
     * Interceptor method for later authorization. Do not delete this method. It
     * is commented for next project stage that would require authorization. Would need to be implemented in EJB
     * This type of interceptor only applies to this method. To invoke interceptor on entire bean, add Annotation on bean
     */
    // @Interceptor(AuthorizationInterceptor.class)
    // public void MethodThatRequiresAuthorization()
    // {
    //     
    //     
    // }
}
