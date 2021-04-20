package com.pa165.bookingmanager.dto.impl;

import com.pa165.bookingmanager.dto.RoleDto;
import com.pa165.bookingmanager.dto.UserDto;

import java.util.List;

/**
 * @author Josef Stribny
 */
public class RoleDtoImpl  implements RoleDto
{
    /**
     * Id
     */
    private Long id;

    /**
     * Name
     */
    private String name;

    /**
     * Users by id
     */
    private List<UserDto> usersById;

    /**
     * Get id
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set id
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get users by id
     *
     * @return users by id
     */
    public List<UserDto> getUsersById() {
        return usersById;
    }

    /**
     * Set users by id
     *
     * @param usersById users by id
     */
    public void setUsersById(List<UserDto> usersById) {
        this.usersById = usersById;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleDtoImpl)) return false;

        RoleDtoImpl roleDto = (RoleDtoImpl) o;

        if (!id.equals(roleDto.id)) return false;
        if (!name.equals(roleDto.name)) return false;
        if (usersById != null ? !usersById.equals(roleDto.usersById) : roleDto.usersById != null) return false;

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (usersById != null ? usersById.hashCode() : 0);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "RoleDtoImpl{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", usersById=" + usersById +
                '}';
    }
}
