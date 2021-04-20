package cz.muni.fi.pa165.bottler.service;

import cz.muni.fi.pa165.bottler.data.dao.StampDao;
import cz.muni.fi.pa165.bottler.data.dto.StampDto;
import cz.muni.fi.pa165.bottler.data.model.Stamp;
import static cz.muni.fi.pa165.bottler.service.EntityAndDtoMapping.*;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Vit Stanislav <373843@mail.muni.cz>
 */
@Service
@Transactional
public class StampServiceImpl implements StampService {

    @Autowired
    StampDao stampDao;

    private Subject currentUser;

    public void setStampDao(StampDao stampDao) {
        this.stampDao = stampDao;
    }

    @Override
    public StampDto createStamp(StampDto stampDto) {
        currentUser = SecurityUtils.getSubject();
        if (currentUser.hasRole("admin")) {
            Stamp stamp = stampDtoToStamp(stampDto, true);
            return stampToStampDto(stampDao.create(stamp));
        } else {
            return null;
        }
    }

    @Override
    public StampDto updateStamp(StampDto stampDto) {
        if (currentUser.hasRole("admin")) {
            Stamp stamp = stampDao.findById(stampDto.getId());
            stamp.setIssuedDate(stampDto.getIssuedDate());
            stamp.setNumberOfStamp(stampDto.getNumberOfStamp());
            return stampToStampDto(stampDao.update(stamp));
        } else {
            return null;
        }
    }

    @Override
    public void removeStamp(StampDto stampDto) {
        if (currentUser.hasRole("admin")) {
            Stamp stamp = stampDtoToStamp(stampDto);
            stampDao.remove(stamp);
        }
    }

    @Override
    public List<StampDto> getAllStamps() {
        return EntityAndDtoMapping.stampToStampDto(stampDao.getAll());
    }

    @Override
    public StampDto findStampById(Long id) {
        Stamp stamp = stampDao.findById(id);
        return stampToStampDto(stamp);
    }
}
