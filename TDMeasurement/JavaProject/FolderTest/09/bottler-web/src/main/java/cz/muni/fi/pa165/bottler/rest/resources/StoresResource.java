package cz.muni.fi.pa165.bottler.rest.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pa165.bottler.data.dto.StoreDto;
import cz.muni.fi.pa165.bottler.service.CompanyRegisterService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Implementation of REST for Stores
 * @author Jakub Macák <374551@mail.muni.cz>
 */
@Component
@Path("/stores")
public class StoresResource {

    @Autowired
    private CompanyRegisterService companyRegisterService;

    public StoresResource() {
    }


    /**
     * Return all stores
     * @return String output
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getStoresJson() {

        ObjectMapper mapper = new ObjectMapper();
        String output;

        try {
            output = mapper.writeValueAsString(companyRegisterService.getAllStores());
        } catch (JsonProcessingException ex) {
            Logger.getLogger(StoresResource.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }

        return output;
    }

    /**
     * Return a one store
     * @param id
     * @return String output
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getStoreJson(@PathParam("id") Integer id) {

        ObjectMapper mapper = new ObjectMapper();
        String output;

        try {
            StoreDto store = companyRegisterService.findStoreById(id);
            if (store != null) {
                output = mapper.writeValueAsString(store);
            } else {
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }
        } catch (JsonProcessingException ex) {

            Logger.getLogger(StoresResource.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }

        return output;
    }

    /**
     * Delete a store
     * @param id
     * @return String output
     */
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteStoreJson(@PathParam("id") Integer id) {

        String output = "{}";
        StoreDto store = companyRegisterService.findStoreById(id);

        if (store != null) {
            companyRegisterService.removeStore(store);
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return output;
    }

    /**
     * Create a store
     * @return String output
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String postStoreJson(StoreDto store) {
        ObjectMapper mapper = new ObjectMapper();
        String output = "{}";

        if (store.getAddress() == null || store.getName() == null || store.getIco() == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        try {
            StoreDto st = companyRegisterService.createStore(store);
            output = mapper.writeValueAsString(st);

        } catch (JsonProcessingException ex) {

            Logger.getLogger(StoresResource.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {

            Logger.getLogger(StoresResource.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.BAD_REQUEST);

        }
        return output;
    }

    /**
     * Update a store
     * @param id
     * @return String output
     */
    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateStoreJson(@PathParam("id") Integer id, StoreDto source) {
        ObjectMapper mapper = new ObjectMapper();
        String output = "{}";

        try {
            StoreDto store = companyRegisterService.findStoreById(id);
            if (store != null) {

                if (source.getAddress() != null) {
                    store.setAddress(source.getAddress());
                }
                if (source.getName() != null) {
                    store.setName(source.getName());
                }

                if (source.getIco() != null) {
                    store.setIco(source.getIco());
                }
                companyRegisterService.updateStore(store);
                output = mapper.writeValueAsString(store);
            } else {
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }
        } catch (JsonProcessingException ex) {

            Logger.getLogger(StoresResource.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);

        } catch (Exception ex) {

            Logger.getLogger(StoresResource.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return output;
    }
}
