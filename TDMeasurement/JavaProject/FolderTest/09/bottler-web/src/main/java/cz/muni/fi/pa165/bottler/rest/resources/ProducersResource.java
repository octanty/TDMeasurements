package cz.muni.fi.pa165.bottler.rest.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pa165.bottler.data.dto.ProducerDto;
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
 * Implementation of REST for Producers
 *
 * @author Vaclav Mach <374430@mail.muni.cz>
 */
@Component
@Path("/producers")
public class ProducersResource {

    @Autowired
    private CompanyRegisterService companyRegisterService;

    public ProducersResource() {
    }

    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getProducersJson() {

        ObjectMapper mapper = new ObjectMapper();
        String output;

        try {
            output = mapper.writeValueAsString(companyRegisterService.getAllProducers());
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ProducersResource.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }


        return output;
    }

    /**
     * Returns a one producer
     *
     * @param id
     * @return
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getProducerJson(@PathParam("id") Integer id) {
        ObjectMapper mapper = new ObjectMapper();
        String output;

        try {
            ProducerDto producer = companyRegisterService.findProducerById(id);
            if (producer != null) {
                output = mapper.writeValueAsString(producer);
            } else {
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }
        } catch (JsonProcessingException ex) {
            
            Logger.getLogger(ProducersResource.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
        return output;
    }

    /**
     * Deletes a producer
     *
     * @param id
     * @return
     */
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteProducerJson(@PathParam("id") Integer id) {

        String output = "{}";
        ProducerDto producer = companyRegisterService.findProducerById(id);

        if (producer != null) {
            companyRegisterService.removeProducer(producer);
        } else {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        return output;
    }

    /**
     * Creates a producer
     *
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String postProducerJson(ProducerDto producer) {
        ObjectMapper mapper = new ObjectMapper();
        String output = "{}";
        
        if (producer.getAddress() == null || producer.getName() == null || producer.getIco() == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }

        try {
            ProducerDto p = companyRegisterService.createProducer(producer);
            output = mapper.writeValueAsString(p);

        } catch (JsonProcessingException ex) {
            
            Logger.getLogger(ProducersResource.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            
            Logger.getLogger(ProducersResource.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
            

        }
        return output;
    }

    /**
     * Updates a producer
     *
     * @param id
     * @return
     */
    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateProducerJson(@PathParam("id") Integer id, ProducerDto source) {
        ObjectMapper mapper = new ObjectMapper();
        String output = "{}";

        try {
            ProducerDto producer = companyRegisterService.findProducerById(id);
            if (producer != null) {

                if (source.getAddress() != null) {
                    producer.setAddress(source.getAddress());
                }
                if (source.getName() != null) {
                    producer.setName(source.getName());
                }

                if (source.getIco() != null) {
                    producer.setIco(source.getIco());
                }
                companyRegisterService.updateProducer(producer);
                output = mapper.writeValueAsString(producer);
            } else {
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }
        } catch (JsonProcessingException ex) {
           
            Logger.getLogger(ProducersResource.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
            
        } catch (Exception ex) {
            
            Logger.getLogger(ProducersResource.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        return output;
    }
}
