/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.fleetmanagement.rest;

import cz.muni.fi.pa165.fleetmanagement.dto.ServiceIntervalDTO;
import cz.muni.fi.pa165.fleetmanagement.service.ServiceIntervalService;
import java.util.LinkedList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * REST Web Service
 *
 * @author Peter Pavlik
 */
@Path("serviceInterval")
@Component
public class ServiceIntervalServiceResource {

    @Autowired
    private ServiceIntervalService serviceIntervalService;
    @Context
    private UriInfo context;

    public ServiceIntervalServiceResource() {
    }

    @DELETE
    @Path("delete/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            ServiceIntervalDTO serviceIntervalDTO = new ServiceIntervalDTO();
            serviceIntervalDTO.setId(id);

            serviceIntervalService.deleteServiceInterval(serviceIntervalDTO);
            return Response.status(Response.Status.OK).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("json/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ServiceIntervalResource> getJsonAll() {
        List<ServiceIntervalDTO> serviceIntervalDTOs;
        try {
            serviceIntervalDTOs = serviceIntervalService.getAllServiceInterval();
        } catch (Exception ex) {
            throw new WebApplicationException(ex, Response.Status.INTERNAL_SERVER_ERROR);
        }
        if (serviceIntervalDTOs == null) {
            return null;
        }

        List<ServiceIntervalResource> serviceIntervalResources = new LinkedList<ServiceIntervalResource>();
        for (ServiceIntervalDTO serviceIntervalDTO : serviceIntervalDTOs) {
            serviceIntervalResources.add(ResourceConvertor.fromServiceIntervalTo(serviceIntervalDTO));
        }
        return serviceIntervalResources;
    }

}
