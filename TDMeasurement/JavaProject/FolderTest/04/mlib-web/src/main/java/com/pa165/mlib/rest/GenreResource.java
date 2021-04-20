package com.pa165.mlib.rest;

import com.pa165.mlib.dto.GenreTO;
import com.pa165.mlib.exception.DuplicateException;
import com.pa165.mlib.service.GenreService;
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
@Path("genre")
@RequestScoped
public class GenreResource {

    @Inject
    GenreService gs;

    @Context
    HttpServletRequest req;

    @Context
    HttpServletResponse resp;
    
    private final AuthChecker ac;

    public GenreResource() {
        ac = new AuthChecker();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addGenre(GenreTO genre) {
        ac.auth(req, resp, "ADMIN");
        if (genre == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        try {
            gs.createNewGenre(genre);
        } catch (DuplicateException ex) {
            return Response.status(Response.Status.CONFLICT).build();
        }
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public GenreTO getGenre(@PathParam("name") String name) {
        return gs.getGenre(name);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<GenreTO> getAll() {
        return gs.getAllGenres();
    }

    @DELETE
    @Path("{name}")
    public Response removeGenre(@PathParam("name") String name) {
        ac.auth(req, resp, "ADMIN");
        GenreTO gto = gs.getGenre(name);
        if (gto == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if (gs.removeGenre(gto)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_MODIFIED).build();
        }
    }

    @POST
    @Path("{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateGenre(@PathParam("name") String name, GenreTO genre) {
        ac.auth(req, resp, "ADMIN");
        GenreTO old = gs.getGenre(name);
        if (old == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if (genre == null) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        gs.updateGenre(old, genre);
        return Response.status(Response.Status.OK).build();
    }

}
