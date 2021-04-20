package com.pa165.mlib.service.impl;

import com.pa165.mlib.dao.AlbumDao;
import com.pa165.mlib.dao.ArtistDao;
import com.pa165.mlib.dto.AlbumTO;
import com.pa165.mlib.dto.ArtistTO;
import com.pa165.mlib.entity.Album;
import com.pa165.mlib.entity.Artist;
import com.pa165.mlib.exception.DuplicateException;
import com.pa165.mlib.service.AlbumService;
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
 * @author brazdil
 */
@MlibService
@Stateless
@DeclareRoles({"ADMIN", "USER"})
@RolesAllowed({"ADMIN", "USER"})
public class AlbumServiceImpl implements AlbumService{
    
    @Inject
    AlbumDao albumDao;
    
    @Inject
    ArtistDao artistDao;
    
    @Inject
    EntityDTOTransformer transformer;
    
    @Override
    @PermitAll
    public List<AlbumTO> getAllAlbums(){
        List<AlbumTO> list = new ArrayList<>();
     
        for (Album a : albumDao.getAll()) {
            AlbumTO ato = transformer.transformAlbumTO(a);
            list.add(ato);
        }
        return list;
    }
    
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public AlbumTO createNewAlbum(AlbumTO albumTO) throws DuplicateException{
        
        Album album = new Album();
        
        album.setTitle(albumTO.getTitle());
        album.setReleased(albumTO.getReleased());
        
        albumDao.addAlbum(album);
        
        return transformer.transformAlbumTO(album);
    }
    
    @Override
    @PermitAll
    public AlbumTO getAlbum(String title) {
        return transformer.transformAlbumTO(albumDao.getAlbum(title));
    }
    
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public AlbumTO updateAlbum(AlbumTO oldAlbum, AlbumTO newAlbum) {
        Album album = albumDao.getAlbum(oldAlbum.getTitle());
        album.setTitle(newAlbum.getTitle());
        album.setReleased(newAlbum.getReleased());
        albumDao.updateAlbum(album);        
        return newAlbum;
    }
    
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean removeAlbum(String title) {
        
        Album album = albumDao.getAlbum(title);
        if (album == null) {
            return false;
        }
        albumDao.removeAlbum(album);
        return true;
    }
    
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean removeAlbum(AlbumTO album) {
        return removeAlbum(album.getTitle());
    }
    
    public void setAlbumDao(AlbumDao albumDao) {
        this.albumDao = albumDao;
    }
    
    public void setTransformer(EntityDTOTransformer transformer) {
        this.transformer = transformer;
    }

    @Override
    @PermitAll
    public List<AlbumTO> getAlbumsWithArtist(ArtistTO artist) {
        List<AlbumTO> list = new ArrayList<>();
        
        Artist art = artistDao.getArtist(artist.getId());
     
        for (Album a : albumDao.getAlbumsWithArtist(art)) {
            AlbumTO ato = transformer.transformAlbumTO(a);
            list.add(ato);
        }
        return list;
    }
    
}
