package com.pa165.mlib.service.impl;

import com.pa165.mlib.dao.ArtistDao;
import com.pa165.mlib.dto.ArtistTO;
import com.pa165.mlib.entity.Artist;
import com.pa165.mlib.exception.DuplicateException;
import com.pa165.mlib.service.ArtistService;
import com.pa165.mlib.utils.EntityDTOTransformer;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

/**
 *
 * @author ibek
 */
@MlibService
@Stateless
@DeclareRoles({"ADMIN", "USER"})
@RolesAllowed({"ADMIN", "USER"})
public class ArtistServiceImpl implements ArtistService {
    
    @Inject
    ArtistDao ad;
    
    @Inject
    EntityDTOTransformer transformer;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public ArtistTO createNewArtist(ArtistTO artistTO) throws DuplicateException {
        Artist artist = new Artist();
        artist.setName(artistTO.getName());
        ad.addArtist(artist);
        return transformer.transformArtistTO(artist);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public ArtistTO updateArtist(ArtistTO oldArtist, ArtistTO newArtist) {
        Artist artist = ad.getArtist(oldArtist.getName());
        artist.setName(newArtist.getName());
        ad.updateArtist(artist);       
        return newArtist;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean removeArtist(String name) {
        Artist artist = ad.getArtist(name);
        if (artist == null) {
            return false;
        }
        ad.removeArtist(artist);
        return true;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean removeArtist(ArtistTO artist) {
        return removeArtist(artist.getName());
    }

    @Override
    @PermitAll
    public List<ArtistTO> getAllArtists() {
        List<ArtistTO> artists = new ArrayList<>();
        for (Artist artist : ad.getAll()) {
            artists.add(transformer.transformArtistTO(artist));
        }
        return artists;
    }

    @Override
    @PermitAll
    public ArtistTO getArtist(String name) {
        return transformer.transformArtistTO(ad.getArtist(name));
    }
    
    public void setArtistDao(ArtistDao artistDao) {
        this.ad = artistDao;
    }
    
    public void setTransformer(EntityDTOTransformer transformer) {
        this.transformer = transformer;
    }
    
}
