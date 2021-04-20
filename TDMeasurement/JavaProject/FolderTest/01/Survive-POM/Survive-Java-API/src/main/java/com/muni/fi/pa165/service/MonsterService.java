package com.muni.fi.pa165.service;

import com.muni.fi.pa165.dto.MonsterDto;
import java.util.List;

/**
 *
 * @author Auron
 */
public interface MonsterService {

    /**
     *
     * @param dto
     * @return
     */
    public MonsterDto save(MonsterDto dto);

    /**
     * Updates MonsterDto
     *
     * @param dto object of type MonsterDto
     */
    public MonsterDto update(MonsterDto dto);

    /**
     * Deletes MonsterDto
     *
     * @param id object of type MonsterDto
     */
   

    public void delete(Long id);

    /**
     * Finds MonsterDto by id
     *
     * @param id ID of the monster
     * @return Monster object of type MonsterDto
     */
    public MonsterDto findById(Long id);

    /**
     *
     * @return
     */
    public List<MonsterDto> findAll();
}
