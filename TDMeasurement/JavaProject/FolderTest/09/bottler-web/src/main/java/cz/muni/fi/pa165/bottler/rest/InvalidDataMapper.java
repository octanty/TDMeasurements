package cz.muni.fi.pa165.bottler.rest;

import org.codehaus.jackson.JsonParseException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Maps JsonParseException and HTTP status 500 to HTTP Bad Request
 * 
 * @author Vaclav Mach <374430@mail.muni.cz>
 */
@Provider
public class InvalidDataMapper implements ExceptionMapper<JsonParseException> {

    @Override
    public Response toResponse(JsonParseException arg0) {
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

}
