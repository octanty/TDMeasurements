/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.fleetmanagement.rest;

import cz.muni.fi.pa165.fleetmanagement.dto.EmployeeDTO;
import cz.muni.fi.pa165.fleetmanagement.service.EmployeeService;
import java.net.URI;

import java.util.LinkedList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Path("employee")
@Component
public class EmployeeServiceResource {

    @Autowired
    private EmployeeService employeeService;
    @Context
    private UriInfo context;

    public EmployeeServiceResource() {
    }

    @DELETE
    @Path("delete/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            EmployeeDTO employee = new EmployeeDTO();
            employee.setId(id);

            employeeService.deleteEmployee(employee);
            return Response.status(Response.Status.OK).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Path("{id}")
    public EmployeeResource getEmployeeResource(@PathParam("id") Integer id) {

        EmployeeDTO employeeDTO = null;
        try {
            employeeDTO = employeeService.getEmployeeById(id.longValue());
        } catch (Exception ex) {
            throw new WebApplicationException(ex, Response.Status.INTERNAL_SERVER_ERROR);
        }
        return ResourceConvertor.fromEmployeeTo(employeeDTO);
    }

    @GET
    @Path("json/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public EmployeeResource getJsonId(@PathParam("id") Integer id) {

        EmployeeDTO employeeDTO = null;
        try {
            employeeDTO = employeeService.getEmployeeById(id.longValue());
        } catch (Exception ex) {
            throw new WebApplicationException(ex, Response.Status.INTERNAL_SERVER_ERROR);
        }
        return ResourceConvertor.fromEmployeeTo(employeeDTO);
    }

    @GET
    @Path("json/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EmployeeResource> getJsonAll() {
        List<EmployeeDTO> employeeDTOs;
        try {
            employeeDTOs = employeeService.getAllEmployee();
        } catch (Exception ex) {
            throw new WebApplicationException(ex, Response.Status.INTERNAL_SERVER_ERROR);
        }
        if (employeeDTOs == null) {
            return null;
        }

        List<EmployeeResource> employeeResources = new LinkedList<EmployeeResource>();
        for (EmployeeDTO employeeDTO : employeeDTOs) {
            employeeResources.add(ResourceConvertor.fromEmployeeTo(employeeDTO));
        }
        return employeeResources;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("json")
    public Response postJson(EmployeeResource employeeResource) {
        EmployeeDTO employeeDTO;
        try {
            employeeService.createEmployee(ResourceConvertor.fromEmployeeResource(employeeResource));
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.created(URI.create(context.getAbsolutePath() + "/" + ResourceConvertor.fromEmployeeResource(employeeResource).getId())).build();
    }

    @PUT
    @Path("json/put/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putJson(@PathParam("id") Integer id, EmployeeResource employeeResource) {

        Response response;
        Long idLong = id.longValue();
        try {
            EmployeeDTO employeeDTO = employeeService.getEmployeeById(idLong);
            if (employeeDTO == null) {
                response = Response.status(Response.Status.NOT_FOUND).build();
            } else {
                employeeResource.setId(idLong);
                employeeService.updateEmployee(ResourceConvertor.fromEmployeeResource(employeeResource));
                response = Response.noContent().build();
            }
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return response;
    }
}
