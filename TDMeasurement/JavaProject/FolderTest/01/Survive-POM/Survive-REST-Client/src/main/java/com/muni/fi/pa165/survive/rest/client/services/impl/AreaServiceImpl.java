/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muni.fi.pa165.survive.rest.client.services.impl;

import com.muni.fi.pa165.dto.AreaDto;
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
public class AreaServiceImpl extends BaseRestService implements CustomRestService<AreaDto> {

    static final String WEB_TARGET = "areas/";
    static final String ACCEPT = "accept";

    private Response response;

    @Override
    public AreaDto create(AreaDto dto) {
        WebTarget resourceWebTarget = webTarget.path(WEB_TARGET);

        Invocation.Builder invocationBuilder = resourceWebTarget.request(MediaType.APPLICATION_XML);
        invocationBuilder.header(ACCEPT, HEADER_XML);
        response = invocationBuilder.post(Entity.entity(dto, MediaType.APPLICATION_XML));
        AreaDto obj = response.readEntity(AreaDto.class);
        return obj;
    }

    @Override
    public AreaDto getById(Long id) {
        WebTarget resourceWebTarget = webTarget.path(WEB_TARGET + id.toString());

        Invocation.Builder invocationBuilder = resourceWebTarget.request(MediaType.APPLICATION_XML);
        invocationBuilder.header(ACCEPT, HEADER_XML);
        response = invocationBuilder.get();

        AreaDto obj = response.readEntity(AreaDto.class);
        return obj;
    }

    @Override
    public AreaDto update(AreaDto dto) {
        AreaDto byId = getById(dto.getId());

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
    public List<AreaDto> getAll() {
        WebTarget resourceWebTarget = webTarget.path(WEB_TARGET + "all");

        Invocation.Builder invocationBuilder = resourceWebTarget.request(MediaType.APPLICATION_XML);
        response = invocationBuilder.accept(MediaType.APPLICATION_XML).get();
        List<AreaDto> list = response.readEntity(new GenericType<List<AreaDto>>() {
        });
        return list;
    }

    @Override
    public Response getResponse() {
        return response;
    }

    private AreaDto updateDto(AreaDto toBeUpdated, AreaDto update) {

        if (update.getDescription() != null) {
            toBeUpdated.setDescription(update.getDescription());
        }

        if (update.getName() != null) {
            toBeUpdated.setName(update.getName());
        }

        if (update.getTerrain() != null) {
            toBeUpdated.setTerrain(update.getTerrain());
        }

        return toBeUpdated;
    }
}
