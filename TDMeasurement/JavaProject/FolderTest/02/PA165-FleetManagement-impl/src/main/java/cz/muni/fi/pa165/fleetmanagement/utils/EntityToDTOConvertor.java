package cz.muni.fi.pa165.fleetmanagement.utils;

import cz.muni.fi.pa165.fleetmanagement.dto.CarDTO;
import cz.muni.fi.pa165.fleetmanagement.dto.EmployeeDTO;
import cz.muni.fi.pa165.fleetmanagement.dto.JourneyDTO;
import cz.muni.fi.pa165.fleetmanagement.dto.ServiceIntervalDTO;
import cz.muni.fi.pa165.fleetmanagement.entity.Car;
import cz.muni.fi.pa165.fleetmanagement.entity.Employee;
import cz.muni.fi.pa165.fleetmanagement.entity.Journey;
import cz.muni.fi.pa165.fleetmanagement.entity.ServiceInterval;
import java.util.List;

/**
 *
 * @author Lubomir Lacika
 */
public class EntityToDTOConvertor {

    public static CarDTO toDto(Car carEntity) {
        if (carEntity == null) {
            return null;
        }

        CarDTO carDTO = new CarDTO();
        carDTO.setId(carEntity.getId());
        carDTO.setBrand(carEntity.getBrand());
        carDTO.setColor(carEntity.getColor());
        carDTO.setMileage(carEntity.getMileage());
        carDTO.setModel(carEntity.getModel());
        carDTO.setPhoto(carEntity.getPhoto());

        if (carEntity.getServiceInterval() != null && carEntity.getServiceInterval().size() > 0) {
            List<ServiceIntervalDTO> serviceIntervalsDTO = null;
            for (int i = 0; i < carEntity.getServiceInterval().size(); i++) {
                serviceIntervalsDTO.add(toDTO(carEntity.getServiceInterval().get(i)));
            }
            carDTO.setServiceInterval(serviceIntervalsDTO);
        }

        carDTO.setEngine(carEntity.getEngine());
        carDTO.setUserClass(carEntity.getUserClass());
        carDTO.setLicensePlate(carEntity.getLicensePlate());

        return carDTO;
    }

    public static Car toEntity(CarDTO carDTO) {
        if (carDTO == null) {
            return null;
        }

        Car carEntity = new Car();
        carEntity.setId(carDTO.getId());
        carEntity.setBrand(carDTO.getBrand());
        carEntity.setColor(carDTO.getColor());
        carEntity.setMileage(carDTO.getMileage());
        carEntity.setModel(carDTO.getModel());
        carEntity.setPhoto(carDTO.getPhoto());

        if (carDTO.getServiceInterval() != null && carDTO.getServiceInterval().size() > 0) {
            List<ServiceInterval> serviceIntervals = null;
            for (int i = 0; i < carDTO.getServiceInterval().size(); i++) {
                serviceIntervals.add(toEntity(carDTO.getServiceInterval().get(i)));
            }
            carEntity.setServiceInterval(serviceIntervals);
        }

        carEntity.setEngine(carDTO.getEngine());
        carEntity.setUserClass(carDTO.getUserClass());
        carEntity.setLicensePlate(carDTO.getLicensePlate());

        return carEntity;
    }

    /**
     * Converts entity to DTO
     */
    public static EmployeeDTO toDTO(Employee employee) {
        if (employee == null) {
            return null;
        }
        EmployeeDTO edto = new EmployeeDTO();
        return applyToDTO(edto, employee);
    }

    /**
     * Converts DTO to entity
     */
    public static Employee toEntity(EmployeeDTO edto) {
        if (edto == null) {
            return null;
        }
        Employee employee = new Employee();
        employee.setId(edto.getId());
        employee.setFirstName(edto.getFirstName());
        employee.setLastName(edto.getLastName());
        employee.setUserClass(edto.getUserClass());
        employee.setGender(edto.getGender());
        employee.setPhoneNumber(edto.getPhoneNumber());
        employee.setEmail(edto.getEmail());
        employee.setIsActive(edto.isIsActive());
        employee.setIsAdmin(edto.isIsAdmin());

        return employee;
    }

    /**
     * Overwrites DTO with information in entity
     */
    public static EmployeeDTO applyToDTO(EmployeeDTO edto, Employee employee) {
        if (employee == null) {
            return null;
        }

        if (edto == null) {
            return null;
        }

        edto.setId(employee.getId());
        edto.setFirstName(employee.getFirstName());
        edto.setLastName(employee.getLastName());
        edto.setUserClass(employee.getUserClass());
        edto.setEmail(employee.getEmail());
        edto.setGender(employee.getGender());
        edto.setPhoneNumber(employee.getPhoneNumber());
        edto.setIsActive(employee.isIsActive());
        edto.setIsAdmin(employee.isIsAdmin());

        return edto;
    }

    /**
     * Converts entity to DTO
     */
    public static JourneyDTO toDTO(Journey journey) {
        if (journey == null) {
            return null;
        }
        JourneyDTO jdto = new JourneyDTO();

        return applyToDTO(jdto, journey);
    }

    /**
     * Converts DTO to entity
     */
    public static Journey toEntity(JourneyDTO jdto) {
        if (jdto == null) {
            return null;
        }
        Journey journey = new Journey();

        journey.setId(jdto.getId());
        journey.setMileage(jdto.getMileage());
        journey.setPickUpDate(jdto.getPickUpDate());
        journey.setReturnDate(jdto.getReturnDate());
        journey.setEmployee(toEntity(jdto.getEmployee()));
        journey.setCar(toEntity(jdto.getCar()));

        if (jdto.getEmployee() != null) {
            journey.setEmployee(toEntity(jdto.getEmployee()));
        }

        if (jdto.getCar() != null) {
            journey.setCar(toEntity(jdto.getCar()));
        }

        return journey;
    }

    /**
     * Overwrites DTO with information in entity
     */
    public static JourneyDTO applyToDTO(JourneyDTO jdto, Journey journey) {
        if (jdto == null) {
            return null;
        }

        if (journey == null) {
            return null;
        }

        jdto.setId(journey.getId());
        jdto.setMileage(journey.getMileage());

        if (journey.getEmployee() != null) {
            jdto.setEmployee(toDTO(journey.getEmployee()));
        }

        if (journey.getCar() != null) {
            jdto.setCar(toDto(journey.getCar()));
        }

        jdto.setPickUpDate(journey.getPickUpDate());
        jdto.setReturnDate(journey.getReturnDate());

        return jdto;
    }

    /**
     * Converts entity to DTO
     */
    public static ServiceIntervalDTO toDTO(ServiceInterval entity) {
        if (entity == null) {
            return null;
        }
        ServiceIntervalDTO dto = new ServiceIntervalDTO();
        return applyToDTO(dto, entity);
    }

    /**
     * Converts DTO to entity
     */
    public static ServiceInterval toEntity(ServiceIntervalDTO dto) {
        if (dto == null) {
            return null;
        }
        ServiceInterval entity = new ServiceInterval();
        entity.setId(dto.getId());
        entity.setService(dto.getService());
        entity.setMileageControl(dto.getMileageControl());
        entity.setDateControl(dto.getDateControl());
        entity.setCompletionDate(dto.getCompletionDate());
        return entity;
    }

    /**
     * Overwrites DTO with information in entity
     */
    public static ServiceIntervalDTO applyToDTO(ServiceIntervalDTO sdto, ServiceInterval entity) {
        if (entity == null) {
            return null;
        }

        if (sdto == null) {
            return null;
        }
        sdto.setId(entity.getId());
        sdto.setService(entity.getService());
        sdto.setMileageControl(entity.getMileageControl());
        sdto.setDateControl(entity.getDateControl());
        sdto.setCompletionDate(entity.getCompletionDate());
        return sdto;
    }
}
