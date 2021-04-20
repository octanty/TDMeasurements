package cz.muni.fi.pa165.fleetmanagement.service.impl;

import cz.muni.fi.pa165.fleetmanagement.dao.JourneyDao;
import cz.muni.fi.pa165.fleetmanagement.dao.impl.JourneyDaoImpl;
import cz.muni.fi.pa165.fleetmanagement.dto.CarDTO;
import cz.muni.fi.pa165.fleetmanagement.dto.EmployeeDTO;
import cz.muni.fi.pa165.fleetmanagement.dto.JourneyDTO;
import cz.muni.fi.pa165.fleetmanagement.entity.Journey;
import cz.muni.fi.pa165.fleetmanagement.exception.ServiceDataAccesException;
import cz.muni.fi.pa165.fleetmanagement.service.JourneyService;
import cz.muni.fi.pa165.fleetmanagement.utils.EntityToDTOConvertor;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ján Švec
 */
@Service
public class JourneyServiceImpl implements JourneyService {

    @Inject
    private JourneyDao journeyDao;

    public JourneyDao getJourneyDao() {
        return journeyDao;
    }

    public void setJourneyDao(JourneyDao journeyDao) {
        this.journeyDao = journeyDao;
    }

    @Transactional
    @Override
    public void createJourney(JourneyDTO journey) {
        try {
            journeyDao.createJourney(EntityToDTOConvertor.toEntity(journey));
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(JourneyDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceDataAccesException(ex.getMessage(), ex.getCause());
        }
    }

    @Transactional
    @Override
    public void deleteJourney(JourneyDTO journey) {
        try {
            journeyDao.deleteJourney(EntityToDTOConvertor.toEntity(journey));
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(JourneyDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceDataAccesException(ex.getMessage(), ex.getCause());
        }
    }

    @Transactional
    @Override
    public void updateJourney(JourneyDTO journey) {
        try {
            journeyDao.updateJourney(EntityToDTOConvertor.toEntity(journey));
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(JourneyDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceDataAccesException(ex.getMessage(), ex.getCause());
        }
    }

    @Transactional
    public List<JourneyDTO> getJourneyForEmployee(EmployeeDTO employee) {
        try {

            List<Journey> journeys = journeyDao.getJourneyForEmployee(EntityToDTOConvertor.toEntity(employee));
            List<JourneyDTO> dtos = new ArrayList<JourneyDTO>();
            for (Journey j : journeys) {
                dtos.add(EntityToDTOConvertor.toDTO(j));
            }

            return dtos;
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(JourneyDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceDataAccesException(ex.getMessage(), ex.getCause());
        }
    }

    @Transactional
    public List<JourneyDTO> getJourneyForCar(CarDTO car) {
        try {

            List<Journey> journeys = journeyDao.getJourneyForCar(EntityToDTOConvertor.toEntity(car));
            List<JourneyDTO> dtos = new ArrayList<JourneyDTO>();
            for (Journey j : journeys) {
                dtos.add(EntityToDTOConvertor.toDTO(j));
            }

            return dtos;

        } catch (IllegalArgumentException ex) {
            Logger.getLogger(JourneyDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceDataAccesException(ex.getMessage(), ex.getCause());
        }
    }

    @Transactional
    @Override
    public JourneyDTO getJourneyById(long id) {
        try {
            Journey journey = journeyDao.getJourneyById(id);
            return EntityToDTOConvertor.toDTO(journey);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(JourneyDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceDataAccesException(ex.getMessage(), ex.getCause());
        }
    }

    @Transactional
    @Override
    public List<JourneyDTO> getAllJourneys() {
        try {
            List<Journey> journeys = journeyDao.getAllJourneys();
            List<JourneyDTO> dtos = new ArrayList<JourneyDTO>();

            for (Journey j : journeys) {
                dtos.add(EntityToDTOConvertor.toDTO(j));
            }
            return dtos;
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(JourneyDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServiceDataAccesException(ex.getMessage(), ex.getCause());
        }
    }
}
