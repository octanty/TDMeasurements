/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muni.fi.pa165.survive.rest.client.services.impl;

import com.muni.fi.pa165.dto.WeaponDto;
import com.muni.fi.pa165.survive.rest.client.services.BaseRestService;
import com.muni.fi.pa165.survive.rest.client.services.CustomRestService;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Michal
 */
public class WeaponServiceImpl extends BaseRestService implements CustomRestService<WeaponDto> {

    static final String WEB_TARGET = "weapons/";
    static final String ACCEPT = "accept";

    private Response response;

    @Override
    public WeaponDto create(WeaponDto dto) {
        WebTarget resourceWebTarget = webTarget.path(WEB_TARGET);

        Invocation.Builder invocationBuilder = resourceWebTarget.request(MediaType.APPLICATION_XML);
        invocationBuilder.header(ACCEPT, HEADER_XML);
        response = invocationBuilder.post(Entity.entity(dto, MediaType.APPLICATION_XML));
        WeaponDto obj = response.readEntity(WeaponDto.class);
        return obj;
    }

    @Override
    public WeaponDto getById(Long id) {
        WebTarget resourceWebTarget = webTarget.path(WEB_TARGET + id.toString());

        Invocation.Builder invocationBuilder = resourceWebTarget.request(MediaType.APPLICATION_XML);
        invocationBuilder.header(ACCEPT, HEADER_XML);
        response = invocationBuilder.get();

        WeaponDto obj = response.readEntity(WeaponDto.class);
        return obj;
    }

    @Override
    public WeaponDto update(WeaponDto dto) {
        WeaponDto byId = getById(dto.getId());

        if (byId == null) {
            return null;
        }
        updateDto(byId, dto);

        WebTarget resourceWebTarget = webTarget.path(WEB_TARGET + dto.getId());

        Invocation.Builder invocationBuilder = resourceWebTarget.request(MediaType.APPLICATION_XML);
        invocationBuilder.header(ACCEPT, HEADER_XML);
        response = invocationBuilder.put(Entity.entity(byId, MediaType.APPLICATION_XML));

        return byId;
    }

    @Override
    public Response delete(Long id) {
        WebTarget resourceWebTarget = webTarget.path(WEB_TARGET + id.toString());

        Invocation.Builder invocationBuilder = resourceWebTarget.request(MediaType.TEXT_PLAIN);
        invocationBuilder.header(ACCEPT, HEADER_JSON);
        response = invocationBuilder.delete();
        return response;
    }

    @Override
    public List<WeaponDto> getAll() {
        WebTarget resourceWebTarget = webTarget.path(WEB_TARGET + "all");

        Invocation.Builder invocationBuilder = resourceWebTarget.request(MediaType.APPLICATION_XML);
        response = invocationBuilder.accept(MediaType.APPLICATION_XML).get();
        List<WeaponDto> list = response.readEntity(new GenericType<List<WeaponDto>>() {
        });
        return list;
    }

    @Override
    public Response getResponse() {
        return response;
    }

    WeaponDto updateDto(WeaponDto toBeUpdated, WeaponDto update) {
        if (update.getCaliber() != null) {
            toBeUpdated.setCaliber(update.getCaliber());
        }

        if (update.getDescription() != null) {
            toBeUpdated.setDescription(update.getDescription());
        }

        if (update.getName() != null) {
            toBeUpdated.setName(update.getName());
        }

        if (update.getRange() != null) {
            toBeUpdated.setRange(update.getRange());
        }

        if (update.getRounds() != null) {
            toBeUpdated.setRounds(update.getRounds());
        }

        if (update.getWeaponClass() != null) {
            toBeUpdated.setWeaponClass(update.getWeaponClass());
        }

        if (update.getWeaponType() != null) {
            toBeUpdated.setWeaponType(update.getWeaponType());
        }

        return toBeUpdated;
    }
}
