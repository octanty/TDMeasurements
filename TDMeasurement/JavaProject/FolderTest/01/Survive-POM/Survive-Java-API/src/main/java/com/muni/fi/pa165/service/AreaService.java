package com.muni.fi.pa165.service;

import com.muni.fi.pa165.dto.AreaDto;
import java.util.List;

/**
 *
 * @author Auron
 */
public interface AreaService {

    /**
     *
     * @param dto
     * @return
     */
    public AreaDto save(AreaDto dto);

    /**
     * Updates AreaDto
     *
     * @param dto object of type AreaDto
     */
    public AreaDto update(AreaDto dto);

    /**
     * Deletes AreaDto
     *
     * @param id object of type AreaDto
     */
   

    public void delete(Long id);

    /**
     * Finds AreaDto by id
     *
     * @param id ID of the area
     * @return Area object of type AreaDto
     */
    public AreaDto findById(Long id);

    /**
     *
     * @return
     */
    public List<AreaDto> findAll();

    public AreaDto checkAndSave(AreaDto dto);
}
