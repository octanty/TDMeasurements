package com.muni.fi.pa165.dao.service.impl;

import com.muni.fi.pa165.dao.WeaponDao;
import com.muni.fi.pa165.dto.WeaponDto;
import com.muni.fi.pa165.entities.Weapon;
import com.muni.fi.pa165.enums.WeaponClass;
import com.muni.fi.pa165.enums.WeaponType;
import com.muni.fi.pa165.service.AbstractServiceIntegrationTest;
import com.muni.fi.pa165.service.impl.WeaponServiceImpl;
import javax.inject.Inject;
import org.dozer.Mapper;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 *
 * @author Auron
 */
public class WeaponServiceImplTest extends AbstractServiceIntegrationTest {

    @Inject
    private WeaponDao mockDAO;
    private WeaponServiceImpl service;
    @Inject
    private Mapper mapper;

    @Before
    public void setUp() {
        service = new WeaponServiceImpl();
        mockDAO = mock(WeaponDao.class);
        service.setDao(mockDAO);
        service.setMapper(mapper);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @After
    public void tearDown() {
    }
    
    /**
     * Test of save method, of class WeaponServiceImpl.
     */

    @Test
    public void testSave() {
        WeaponDto dto = new WeaponDto();
        dto.setId(1L);
        dto.setName("TESTAK47");
        dto.setCaliber(Double.MIN_NORMAL);
        dto.setDescription("Africa's favourite");
        dto.setRounds(44);
        dto.setRange(100);
        dto.setWeaponClass(WeaponClass.RANGED);
        dto.setWeaponType(WeaponType.GUN);

        Weapon entity = mapper.map(dto, Weapon.class);
        when(mockDAO.save(any(Weapon.class))).thenReturn(entity);
        WeaponDto returned = service.save(dto);
        assertEquals(returned, dto);
    }

    /**
     * Test of update method, of class WeaponServiceImpl.
     */
    @Test
    public void testUpdate() {
        WeaponDto dto = new WeaponDto();
        dto.setId(1L);
        dto.setName("TESTAK47");
        dto.setCaliber(Double.MIN_NORMAL);
        dto.setDescription("Africa's favourite");
        dto.setRounds(44);
        dto.setRange(100);
        dto.setWeaponClass(WeaponClass.RANGED);
        dto.setWeaponType(WeaponType.GUN);

        Weapon entity = mapper.map(dto, Weapon.class);

        when(mockDAO.update(any(Weapon.class))).thenReturn(entity);
        WeaponDto returned = service.update(dto);
        verify(mockDAO, times(1)).update(entity);
        verifyNoMoreInteractions(mockDAO);
        assertEquals(returned, dto);
    }

    /**
     * Test of delete method, of class WeaponServiceImpl.
     */
    @Test
    public void testDelete() {

        WeaponDto dto = new WeaponDto();
        dto.setId(1L);

        dto.setName("TESTAK47");
        dto.setCaliber(Double.MIN_NORMAL);
        dto.setDescription("Africa's favourite");
        dto.setRounds(44);
        dto.setRange(100);
        dto.setWeaponClass(WeaponClass.RANGED);
        dto.setWeaponType(WeaponType.GUN);
        Weapon entity = new Weapon();
        entity.setId(1L);
        entity.setName("Farm");

        service.delete(dto.getId());

        verify(mockDAO, times(1)).delete(entity.getId());
        verifyNoMoreInteractions(mockDAO);


    }

    /**
     * Test of findById method, of class WeaponServiceImpl.
     */
    @Test
    public void testFindById() {

        WeaponDto dto = new WeaponDto();

        dto.setId(1L);
        dto.setName("TESTAK47");
        dto.setCaliber(Double.MIN_NORMAL);
        dto.setDescription("Africa's favourite");
        dto.setRounds(44);
        dto.setRange(100);
        dto.setWeaponClass(WeaponClass.RANGED);
        dto.setWeaponType(WeaponType.GUN);

        Weapon entity = mapper.map(dto, Weapon.class);

        when(mockDAO.findById(dto.getId())).thenReturn(entity);

        WeaponDto returned = service.findById(dto.getId());
        verify(mockDAO, times(1)).findById(dto.getId());
        verifyNoMoreInteractions(mockDAO);

        assertEquals(returned, dto);


    }
    

}