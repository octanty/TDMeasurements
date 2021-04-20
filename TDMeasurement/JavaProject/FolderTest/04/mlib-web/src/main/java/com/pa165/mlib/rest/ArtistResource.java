package com.pa165.mlib.rest;

import com.pa165.mlib.dto.ArtistTO;
import com.pa165.mlib.exception.DuplicateException;
import com.pa165.mlib.service.ArtistService;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author ibek
 */
@Path("artist")
@RequestScoped
public class ArtistResource {

    @Inject
    ArtistService as;

    @Context
    HttpServletRequest req;

    @Context
    HttpServletResponse resp;
    
    private final AuthChecker ac;

    public ArtistResource() {
        ac = new AuthChecker();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addArtist(ArtistTO artist) {
        ac.auth(req, resp, null);
        if (artist == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        try {
            as.createNewArtist(artist);
        } catch (DuplicateException ex) {
            return Response.status(Response.Status.CONFLICT).build();
        }
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArtistTO getArtist(@PathParam("name") String name) {
        return as.getArtist(name);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ArtistTO> getAll() {
        return as.getAllArtists();
    }
    
    @DELETE
    @Path("{name}")
    public Response removeArtist(@PathParam("name") String name) {
        ac.auth(req, resp, null);
        ArtistTO ato = as.getArtist(name);
        if (ato == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if (as.removeArtist(ato)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_MODIFIED).build();
        }
    }
    
    @POST
    @Path("{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateArtist(@PathParam("name") String name, ArtistTO artist) {
        ac.auth(req, resp, null);
        ArtistTO old = as.getArtist(name);
        if (old == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if (artist == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        as.updateArtist(old, artist);
        return Response.status(Response.Status.OK).build();
    }

}
