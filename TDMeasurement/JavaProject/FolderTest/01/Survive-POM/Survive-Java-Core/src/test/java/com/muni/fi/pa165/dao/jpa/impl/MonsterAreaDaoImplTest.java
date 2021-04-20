package com.muni.fi.pa165.dao.jpa.impl;

import com.muni.fi.pa165.dao.AreaDao;
import com.muni.fi.pa165.dao.MonsterAreaDao;
import com.muni.fi.pa165.dao.MonsterDao;
import com.muni.fi.pa165.dao.gen.AbstractDaoIntegrationTest;
import com.muni.fi.pa165.entities.Area;
import com.muni.fi.pa165.entities.Monster;
import com.muni.fi.pa165.entities.MonsterArea;
import com.muni.fi.pa165.entities.MonsterAreaPK;
import com.muni.fi.pa165.enums.MonsterClass;
import com.muni.fi.pa165.enums.TerrainType;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author irina
 */
public class MonsterAreaDaoImplTest extends AbstractDaoIntegrationTest {
    
    @Autowired
    MonsterDao monsterDao;
    Monster monster;
    @Autowired
    AreaDao areaDao;
    Area area;
    @Autowired
    MonsterAreaDao monsterAreaDao;
    MonsterArea monsterArea;
    
    
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
        
        area = new Area();
        area.setName("Farm");
        area.setTerrain(TerrainType.SNOW);
        areaDao.save(area);
        
        monsterArea = new MonsterArea();
        monsterArea.setArea(area);
        monsterArea.setMonster(monster);
        monsterArea.setMonsterquantity(50);
        monsterArea.setMonsterareaPK(new MonsterAreaPK(monster.getId(), area.getId()));
        monsterAreaDao.save(monsterArea);
}
   
    @After
    public void tearDown() {
        for (MonsterArea item : monsterAreaDao.findAll()){
            monsterAreaDao.delete(item.getMonsterareaPK());
        }
    }

    @Before
    public void setUpClass() {
    }
    

    @Test
     public void testFindAll() {
         List<MonsterArea> monsterAreas = monsterAreaDao.findAll();
         assertEquals("List contains only one monsterArea.",1 , monsterAreas.size());
         assertEquals("In the is proper monsterArea.", monsterArea, monsterAreas.get(0));
     }
    
    @Test
     public void testFindByMonsterId() {
         List<MonsterArea> monsterAreas = monsterAreaDao.getByMonsterId(monster.getId());
         assertEquals("List contains only one monsterArea.",1 , monsterAreas.size());
         assertEquals("In the is proper monsterArea.", monsterArea, monsterAreas.get(0));
     }
    @Test
     public void testFindByAreaId() {
         List<MonsterArea> monsterAreas = monsterAreaDao.getByAreaId(area.getId());
         assertEquals("List contains only one monsterArea.",1 , monsterAreas.size());
         assertEquals("In the is proper monsterArea.", monsterArea, monsterAreas.get(0));
     }
    @Test
    public void testDelete(){
        monsterAreaDao.delete(monsterArea.getMonsterareaPK());
        List<MonsterArea> monsterAreas = monsterAreaDao.findAll();
        assertEquals("List should be empty.", 0, monsterAreas.size());
    }
    
    @Test
     public void testFindById() {
         MonsterArea returned = monsterAreaDao.findById(monsterArea.getMonsterareaPK());         
         assertEquals("In the is proper monsterArea.", returned, monsterArea);
     }
}