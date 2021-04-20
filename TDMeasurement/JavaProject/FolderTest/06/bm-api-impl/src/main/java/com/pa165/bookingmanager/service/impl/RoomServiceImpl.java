package com.pa165.bookingmanager.service.impl;

import com.pa165.bookingmanager.convertor.impl.HotelConvertorImpl;
import com.pa165.bookingmanager.convertor.impl.RoomConvertorImpl;
import com.pa165.bookingmanager.dao.HotelDao;
import com.pa165.bookingmanager.dao.RoomDao;
import com.pa165.bookingmanager.dto.HotelDto;
import com.pa165.bookingmanager.dto.RoomDto;
import com.pa165.bookingmanager.entity.HotelEntity;
import com.pa165.bookingmanager.entity.RoomEntity;
import com.pa165.bookingmanager.service.RoomService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author Jana Polakova, Jakub Polak, Jan Hrubes, Josef Stribny
 */
@Service("roomService")
@Transactional(readOnly = true)
public class RoomServiceImpl implements RoomService
{
    @Autowired
    RoomDao roomDao;

    @Autowired
    HotelDao hotelDao;

    @Autowired
    RoomConvertorImpl roomConvertor;

    @Autowired
    HotelConvertorImpl hotelConvertor;

    /**
     * Constructor
     */
    public RoomServiceImpl(){

    }

    /**
     * Constructor
     *
     * @param roomDao room dao
     * @param roomConvertor room convertor
     */
    public RoomServiceImpl(RoomDao roomDao, RoomConvertorImpl roomConvertor, HotelConvertorImpl hotelConvertor, HotelDao hotelDao){
        if (roomDao == null){
            throw new IllegalArgumentException("RoomDao can't be null.");
        }

        this.roomDao = roomDao;

        if (roomConvertor == null){
            throw new IllegalArgumentException("RoomConvertor can't be null.");
        }

        this.roomConvertor = roomConvertor;

        if (hotelConvertor == null){
            throw new IllegalArgumentException("HotelConvertor can't be null.");
        }

        this.hotelConvertor = hotelConvertor;

        if (hotelDao == null){
            throw new IllegalArgumentException("HotelDao can't be null.");
        }

        this.hotelDao = hotelDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RoomDto> findAll() {
        List<RoomEntity> roomEntities = roomDao.findAll();
        List<RoomDto> roomDtos = null;

        if (roomEntities != null){
            roomDtos = roomConvertor.convertEntityListToDtoList(roomEntities);
        }

        return roomDtos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RoomDto find(Long id) {
        if (id == null){
            throw new IllegalArgumentException("Id can't be null.");
        }

        RoomEntity roomEntity = roomDao.find(id);
        RoomDto roomDto = null;

        if (roomEntity != null){
            roomDto = roomConvertor.convertEntityToDto(roomEntity);
        }

        return roomDto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RoomDto> findByHotel(Long id){
        if (id == null){
            throw new IllegalArgumentException("Id can't be null.");
        }

        HotelEntity hotelEntity = hotelDao.find(id);

        List<RoomDto> roomDtos = null;
        if (hotelEntity != null){
            List<RoomEntity> roomEntities = roomDao.findByHotel(hotelEntity);
            roomDtos = roomConvertor.convertEntityListToDtoList(roomEntities);
        }

        return roomDtos;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<RoomDto> findAvailableByHotel(Long id, Date from, Date to){
        if (id == null){
            throw new IllegalArgumentException("Id can't be null.");
        }

        HotelEntity hotelEntity = hotelDao.find(id);

        List<RoomDto> roomDtos = null;
        if (hotelEntity != null){
            List<RoomEntity> roomEntities = roomDao.findAvailable(hotelEntity.getId(), from, to);
            roomDtos = roomConvertor.convertEntityListToDtoList(roomEntities);
        }

        return roomDtos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public void create(RoomDto roomDto) {
        if (roomDto == null){
            throw new IllegalArgumentException("RoomDto can't be null.");
        }

        HotelDto hotelDto = roomDto.getHotelByHotelId();

        if (hotelDto == null){
            throw new IllegalArgumentException("HotelDto can't be null.");
        }

        RoomEntity roomEntity = roomConvertor.convertDtoToEntity(roomDto);
        HotelEntity hotelEntity = hotelConvertor.convertDtoToEntity(hotelDto);

        roomEntity.setHotelByHotelId(hotelEntity);

        roomDao.create(roomEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public void update(RoomDto roomDto) {
        if (roomDto == null){
            throw new IllegalArgumentException("RoomDto can't be null.");
        }

        RoomEntity roomEntity = roomDao.find(roomDto.getId());

        if (roomEntity != null){
            roomConvertor.convertDtoToEntity(roomDto, roomEntity);
            roomDao.update(roomEntity);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(RoomDto roomDto) {
        if (roomDto == null){
            throw new IllegalArgumentException("RoomDto can't be null.");
        }

        RoomEntity roomEntity = roomDao.find(roomDto.getId());

        if (roomEntity != null){
            roomDao.delete(roomEntity);
        }
    }
}
