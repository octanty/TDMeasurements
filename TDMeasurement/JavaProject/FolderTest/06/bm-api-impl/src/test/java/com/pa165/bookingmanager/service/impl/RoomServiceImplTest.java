package com.pa165.bookingmanager.service.impl;

import com.pa165.bookingmanager.TestServiceSetup;
import com.pa165.bookingmanager.convertor.impl.HotelConvertorImpl;
import com.pa165.bookingmanager.convertor.impl.RoomConvertorImpl;
import com.pa165.bookingmanager.dao.HotelDao;
import com.pa165.bookingmanager.dao.RoomDao;
import com.pa165.bookingmanager.dto.RoomDto;
import com.pa165.bookingmanager.dto.impl.RoomDtoImpl;
import com.pa165.bookingmanager.entity.HotelEntity;
import com.pa165.bookingmanager.entity.RoomEntity;
import com.pa165.bookingmanager.service.RoomService;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * @author Jakub Polak, Jan Hrubes
 */
public class RoomServiceImplTest extends TestServiceSetup
{
    @Mock
    private RoomDao roomDao;

    @Mock
    private HotelDao hotelDao;

    @Mock
    private RoomConvertorImpl roomConvertor;

    @Mock
    private HotelConvertorImpl hotelConvertor;

    private RoomService roomService;

    @Before
    public void setup() throws Exception {
        super.setup();
        roomService = new RoomServiceImpl(roomDao, roomConvertor, hotelConvertor, hotelDao);
    }

    @Test
    public void testFindAll() throws Exception {
        List<RoomEntity> roomEntities = new ArrayList<RoomEntity>();
        List<RoomDto> roomDtos = new ArrayList<RoomDto>();

        roomEntities.add(new RoomEntity());
        roomDtos.add(new RoomDtoImpl());

        when(roomDao.findAll()).thenReturn(roomEntities);
        when(roomConvertor.convertEntityListToDtoList(roomEntities)).thenReturn(roomDtos);

        Assert.assertEquals(1, roomService.findAll().size());
    }

    @Test
    public void testFind() throws Exception {
        RoomEntity roomEntity = new RoomEntity();
        RoomDto roomDto = new RoomDtoImpl();

        when(roomDao.find(1L)).thenReturn(roomEntity);
        when(roomDao.find(999L)).thenReturn(null);
        when(roomConvertor.convertEntityToDto(roomEntity)).thenReturn(roomDto);

        Assert.assertNotNull(roomService.find(1L));
        Assert.assertNull(roomService.find(999L));
    }

    @Test
    public void testFindByHotel() throws Exception {
        HotelEntity hotelEntity = new HotelEntity();
        hotelEntity.setId(1L);
        List<RoomEntity> roomEntities = new ArrayList<RoomEntity>();
        List<RoomDto> roomDtos = new ArrayList<RoomDto>();

        when(hotelDao.find(hotelEntity.getId())).thenReturn(hotelEntity);
        when(roomDao.findByHotel(hotelEntity)).thenReturn(roomEntities);
        when(roomConvertor.convertEntityListToDtoList(roomEntities)).thenReturn(roomDtos);

        Assert.assertEquals(0, roomDtos.size());
    }

    @Test
    public void testFindAvailableByHotel() throws Exception {
        HotelEntity hotelEntity = new HotelEntity();
        hotelEntity.setId(1L);
        List<RoomEntity> roomEntities = new ArrayList<RoomEntity>();
        List<RoomDto> roomDtos = new ArrayList<RoomDto>();

        when(hotelDao.find(hotelEntity.getId())).thenReturn(hotelEntity);
        when(roomDao.findAvailable(hotelEntity.getId(), new Date(), new Date())).thenReturn(roomEntities);
        when(roomConvertor.convertEntityListToDtoList(roomEntities)).thenReturn(roomDtos);

        Assert.assertEquals(0, roomDtos.size());
    }
}
