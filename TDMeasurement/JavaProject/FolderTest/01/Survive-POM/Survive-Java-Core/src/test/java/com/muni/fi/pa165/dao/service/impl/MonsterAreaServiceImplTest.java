package com.muni.fi.pa165.dao.service.impl;

import com.muni.fi.pa165.service.impl.MonsterAreaServiceImpl;
import com.muni.fi.pa165.dao.MonsterAreaDao;
import com.muni.fi.pa165.dto.AreaDto;
import com.muni.fi.pa165.service.AbstractServiceIntegrationTest;
import com.muni.fi.pa165.dto.MonsterAreaDto;
import com.muni.fi.pa165.dto.MonsterDto;
import com.muni.fi.pa165.entities.MonsterArea;
import com.muni.fi.pa165.enums.MonsterClass;
import com.muni.fi.pa165.enums.TerrainType;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;
import org.dozer.Mapper;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import static org.junit.Assert.*;


/**
 *
 * @author irina
 */
public class MonsterAreaServiceImplTest extends AbstractServiceIntegrationTest {

    @Inject
    private MonsterAreaDao mockDAO;
    private MonsterAreaServiceImpl service;
    @Inject
    private Mapper mapper;
    private MonsterDto monsterDto;
    private AreaDto areaDto;
    private MonsterAreaDto monsterAreaDto;

    /**
     *
     */
    @Before
    public void setUp() {
        service = new MonsterAreaServiceImpl();
        mockDAO = mock(MonsterAreaDao.class);
        service.setDao(mockDAO);
        service.setMapper(mapper);

        monsterDto = new MonsterDto();
        monsterDto.setId(1L);
        monsterDto.setAgility(11.0);
        monsterDto.setDangerLevel(22.2);
        monsterDto.setDescription("Headless Zombie");
        monsterDto.setHeight(11.4);
        monsterDto.setImagePath("C:\\image.png");
        monsterDto.setMonsterClass(MonsterClass.ZOMBIE);
        monsterDto.setStamina(11.5);
        monsterDto.setStrength(11.8);
        monsterDto.setWeight(11.2);
        monsterDto.setName("HeadlessNick");
        monsterDto.setId(Long.MIN_VALUE);
        
        areaDto = new AreaDto();
        areaDto.setName("Farm");
        areaDto.setTerrain(TerrainType.SNOW);
        areaDto.setId(Long.MIN_VALUE);


        monsterAreaDto = new MonsterAreaDto();
        monsterAreaDto.setArea(areaDto);
        monsterAreaDto.setMonster(monsterDto);
        monsterAreaDto.setMonsterQuantity(5);
    }

    /**
     *
     */
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     *
     */
    @After
    public void tearDown() {
    
    
    }

    /**
     * Test of save method, of class MonsterAreaServiceImpl.
     */
    @Test
    public void testSave() {
        MonsterArea entity = mapper.map(monsterAreaDto, MonsterArea.class);
        when(mockDAO.save(any(MonsterArea.class))).thenReturn(entity);
        MonsterAreaDto returned = service.save(monsterAreaDto);
        assertEquals(returned, monsterAreaDto);
    }

    /**
     * Test of update method, of class MonsterAreaServiceImpl.
     */
    @Test
    public void testUpdate() {
        MonsterArea entity = mapper.map(monsterAreaDto, MonsterArea.class);
        when(mockDAO.update(any(MonsterArea.class))).thenReturn(entity);
        MonsterAreaDto returned = service.update(monsterAreaDto);
        assertEquals(returned, monsterAreaDto);
    }

    /**
     * Test of delete method, of class MonsterAreaServiceImpl.
     */
    @Test
    public void testDelete() {

        MonsterArea entity = mapper.map(monsterAreaDto, MonsterArea.class);
        service.delete(monsterAreaDto);
        verify(mockDAO, times(1)).delete(entity);
        verifyNoMoreInteractions(mockDAO);

    }
    @Test
    public void testFindByMonsterId() {
        MonsterArea entity = mapper.map(monsterAreaDto, MonsterArea.class);
        List<MonsterArea> mw = new LinkedList();
        mw.add(entity);
        when(mockDAO.getByMonsterId(any(Long.class))).thenReturn(mw);
        List<MonsterAreaDto> returned = service.findByMonsterId(monsterDto.getId());
        assertNotNull(returned);
        assertEquals(1, returned.size());
        assertEquals(returned.get(0), monsterAreaDto);
    }
    
    @Test
    public void testFindByArearId() {
        MonsterArea entity = mapper.map(monsterAreaDto, MonsterArea.class);
        List<MonsterArea> mw = new LinkedList();
        mw.add(entity);
        when(mockDAO.getByAreaId(any(Long.class))).thenReturn(mw);
        List<MonsterAreaDto> returned = service.findByAreaId(monsterDto.getId());
        assertNotNull(returned);
        assertEquals(1, returned.size());
        assertEquals(returned.get(0), monsterAreaDto);
    }
    @Test
    public void testFindAll() {
        MonsterArea entity = mapper.map(monsterAreaDto, MonsterArea.class);
        List<MonsterArea> mw = new LinkedList();
        mw.add(entity);
        when(mockDAO.findAll()).thenReturn(mw);
        List<MonsterAreaDto> returned = service.findAll();
        assertNotNull(returned);
        assertEquals(returned.size(), 1);
        assertEquals(entity.getMonsterquantity(), returned.get(0).getMonsterQuantity());
}
}