package com.muni.fi.pa165.dao.jpa.impl;

import com.muni.fi.pa165.dao.MonsterDao;
import com.muni.fi.pa165.dao.MonsterWeaponDao;
import com.muni.fi.pa165.dao.WeaponDao;
import com.muni.fi.pa165.dao.gen.AbstractDaoIntegrationTest;
import com.muni.fi.pa165.entities.Monster;
import com.muni.fi.pa165.entities.MonsterArea;
import com.muni.fi.pa165.entities.MonsterWeapon;
import com.muni.fi.pa165.entities.MonsterWeaponPK;
import com.muni.fi.pa165.entities.Weapon;
import com.muni.fi.pa165.enums.MonsterClass;
import com.muni.fi.pa165.enums.WeaponClass;
import com.muni.fi.pa165.enums.WeaponType;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author irina
 */
public class MonsterWeaponDaoImplTest extends AbstractDaoIntegrationTest {
    
        
    @Autowired
    MonsterDao monsterDao;
    Monster monster;
    @Autowired
    WeaponDao weaponDao;
    Weapon weapon;
    @Autowired
    MonsterWeaponDao monsterWeaponDao;
    MonsterWeapon monsterWeapon;

    /**
     *
     */
    @Before
    public void setUp() {
        monster = new Monster();
        monster.setAgility(11.0);
        monster.setDangerlevel(22.4);
        monster.setDescription("Headless Zombie");
        monster.setHeight(11.4);
        monster.setImagepath("C:\\image.png");
        monster.setMonsterclass(MonsterClass.ZOMBIE);
        monster.setStamina(11.5);
        monster.setStrength(11.8);
        monster.setWeight(11.2);
        monster.setName("HeadlessNick");
        monsterDao.save(monster);
       
        weapon = new Weapon();
        weapon.setName("TESTAK47");
        weapon.setCaliber(Double.valueOf(0.88));
        weapon.setDescription("Africa's favourite");
        weapon.setRounds(44);
        weapon.setRange(100);
        weapon.setWeaponclass(WeaponClass.RANGED);
        weapon.setWeapontype(WeaponType.GUN);
        weaponDao.save(weapon);
                
        monsterWeapon = new MonsterWeapon();
        monsterWeapon.setMonsterweaponPK(new MonsterWeaponPK(monster.getId(), weapon.getId()));
        monsterWeapon.setWeapon(weapon);
        monsterWeapon.setMonster(monster);
        monsterWeapon.setHitrate(22);
        monsterWeapon.setDamage(12);
        monsterWeapon.setEfficiency(55);
        monsterWeaponDao.save(monsterWeapon);           
    }

    @After
    public void tearDown() {
        for (MonsterWeapon item : monsterWeaponDao.findAll()){
            monsterWeaponDao.delete(item.getMonsterweaponPK());
        }
    }


    @Before
    public void setUpClass() {
    }
    
    @Test
     public void testFindAll() {
         List<MonsterWeapon> monsterWeapons = monsterWeaponDao.findAll();
         assertEquals("List contains only one monsterWeapon.",1 , monsterWeapons.size());
         assertEquals("In the is proper monsterWeapon.", monsterWeapon, monsterWeapons.get(0));
     }

    @Test
    public void testFindByMonsterId() {
         List<MonsterWeapon> monsterWeapons = monsterWeaponDao.getByMonsterId(monster.getId());
         assertEquals("List contains only one monsterWeapon.",1 , monsterWeapons.size());
         assertEquals("In the is proper monsterWeapon.", monsterWeapon, monsterWeapons.get(0));
 }
    @Test
    public void testFindByWeaponId() {
         List<MonsterWeapon> monsterWeapons = monsterWeaponDao.getByWeaponId(weapon.getId());
         assertEquals("List contains only one monsterWeapon.",1 , monsterWeapons.size());
         assertEquals("In the is proper monsterWeapon.", monsterWeapon, monsterWeapons.get(0));
         
    
}
    @Test
    public void testDelete(){
        monsterWeaponDao.delete(monsterWeapon.getMonsterweaponPK());
        List<MonsterWeapon> monsterWeapons = monsterWeaponDao.findAll();
        assertEquals("List should be empty.", 0, monsterWeapons.size());
    }
    @Test
     public void testFindById() {
         MonsterWeapon returned = monsterWeaponDao.findById(monsterWeapon.getMonsterweaponPK());         
         assertEquals("In the is proper monsterArea.", returned, monsterWeapon);
     }
    
}
    