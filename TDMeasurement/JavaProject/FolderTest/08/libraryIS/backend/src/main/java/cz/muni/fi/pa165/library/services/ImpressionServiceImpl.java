package cz.muni.fi.pa165.library.services;

import cz.muni.fi.pa165.library.daos.ImpressionDao;
import cz.muni.fi.pa165.library.dtos.ImpressionTO;
import cz.muni.fi.pa165.library.entities.Impression;
import cz.muni.fi.pa165.library.utils.Convertor;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Matej
 */
@Service
@Transactional
public class ImpressionServiceImpl implements ImpressionService {

    @Autowired
    private ImpressionDao impressionDao;
    @Autowired
    private Convertor convertor;

    public void setConvertor(Convertor convertor) {
        this.convertor = convertor;
    }

    @Secured({"ROLE_ADMIN"})
    public void createImpression(ImpressionTO impressionTO) {
        Impression impressionEntity = convertor.convert(impressionTO);
        impressionDao.createImpression(impressionEntity);
        impressionTO.setId(impressionEntity.getId());
    }

    public List<ImpressionTO> findAllImpressions() {
        List<ImpressionTO> impressionTOs = new ArrayList<ImpressionTO>();
        List<Impression> impressionEntities = impressionDao.findAllImpressions();
        for (Impression impression : impressionEntities) {
            impressionTOs.add(convertor.convert(impression));
        }
        return impressionTOs;
    }

    public ImpressionTO findImpressionById(Long id) {
        return convertor.convert(impressionDao.findImpressionById(id));
    }

    @Secured({"ROLE_ADMIN"})
    public void deleteImpression(ImpressionTO impressionTO) {
        impressionDao.deleteImpression(convertor.convert(impressionTO));
        impressionTO.setId(null);
    }

    @Secured({"ROLE_ADMIN"})
    public void updateImpression(ImpressionTO impressionTO) {
        impressionDao.updateImpression(convertor.convert(impressionTO));
    }

    public List<ImpressionTO> findImpressions(String search) {
        List<ImpressionTO> impressionTOs = new ArrayList<ImpressionTO>();
        List<Impression> impressionEntities = impressionDao.findImpressions(search);
        for (Impression impression : impressionEntities) {
            impressionTOs.add(convertor.convert(impression));
        }
        return impressionTOs;
    }
}
