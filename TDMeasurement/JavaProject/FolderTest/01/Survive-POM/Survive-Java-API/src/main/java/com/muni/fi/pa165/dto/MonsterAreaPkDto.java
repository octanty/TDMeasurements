package com.muni.fi.pa165.dto;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Auron
 */
@XmlRootElement
public class MonsterAreaPkDto {

    private Long monsterId;
    private Long areaId;

    /**
     *
     */
    public MonsterAreaPkDto() {
    }

    /**
     *
     * @param monsterId
     * @param areaId
     */
    public MonsterAreaPkDto(Long monsterId, Long areaId) {
        this.monsterId = monsterId;
        this.areaId = areaId;
    }

    /**
     *
     * @return
     */
    public Long getMonsterId() {
        return monsterId;
    }

    /**
     *
     * @param monsterId
     */
    public void setMonsterId(Long monsterId) {
        this.monsterId = monsterId;
    }

    /**
     *
     * @return
     */
    public Long getAreaId() {
        return areaId;
    }

    /**
     *
     * @param areaId
     */
    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }
}
