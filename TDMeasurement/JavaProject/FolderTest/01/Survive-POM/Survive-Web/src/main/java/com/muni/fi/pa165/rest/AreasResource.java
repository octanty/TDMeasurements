/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muni.fi.pa165.rest;

import com.muni.fi.pa165.dto.AreaDto;
import com.muni.fi.pa165.enums.TerrainType;
import com.muni.fi.pa165.service.AreaService;
import com.sun.jersey.spi.inject.Inject;
import java.net.URI;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Rest resource for manipulating Area 
 * @author Aubrey Oosthuizen
 */
@Path("/areas")
@Component
@Scope("request")
//@Singleton
public class AreasResource {

    @Context
    private UriInfo context;
    @Inject
    AreaService areaService;

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_XML)
    public List<AreaDto> getAll() {
        List<AreaDto> list = null;
        try {
            list = areaService.findAll();
        } catch (Exception ex) {
            throw new WebApplicationException(ex, Response.Status.NO_CONTENT);
        }
        return list;
    }

    /**
     *
     * @return
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getPlain() {
        StringBuilder returnString = new StringBuilder();
        try {
            for (AreaDto dto : areaService.findAll()) {
                returnString.append(dto.getName());
                returnString.append("\n");
                
            }
        } catch (Exception ex) {
            throw new WebApplicationException(ex, Response.Status.NO_CONTENT);
        }

        return returnString.toString();
    }

    /**
     *
     * @param id
     * @return
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML})
    public AreaDto getById(@PathParam("id") Integer id) {
        AreaDto dto = null;
        try{
        dto = areaService.findById(Long.valueOf(id.toString()));
        if (dto == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        } catch (Exception ex) {
            throw new WebApplicationException(ex, Response.Status.NO_CONTENT);
        }
        return dto;
    }

    /**
     *
     * @param dto
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public AreaDto postXml(AreaDto dto) {
        AreaDto checkAndSave = areaService.checkAndSave(dto);
        
        URI uri = context.getAbsolutePath();
        Response response;
        
        try {
            response = Response.created(URI.create(context.getAbsolutePath() + "/" + checkAndSave.getId())).build();
        } catch (Exception ex) {
            throw new WebApplicationException(ex, Response.Status.NO_CONTENT);
        }

        return dto;
    }

    /**
     *
     * @param id
     * @param dto
     * @return
     */
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_XML)
    public Response putXml(@PathParam("id") Integer id, AreaDto dto) {
        Response response;
        AreaDto returned = null;
        try {
            returned = areaService.update(dto);
            if (returned == null)
            {
              throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);                
            }                
            response = Response.ok(dto).build();
        } catch (Exception ex) {
            throw new WebApplicationException(ex, Response.Status.NOT_MODIFIED);
        }
        return response;
    }

    /**
     *
     * @param id
     * @return
     */
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Integer id) {
        AreaDto dto = null;
        try{
       dto = areaService.findById(Long.valueOf(id));
        if (dto == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        areaService.delete(dto.getId());
        } catch (Exception ex) {
            throw new WebApplicationException(ex);
        }
        Response response = Response.ok().build();
        return response;
    }
}
