package com.pa165.mlib.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

/**
 *
 * @author ibek
 */
public class AuthChecker {
    
    protected Response auth(HttpServletRequest req, HttpServletResponse resp, String role) {
        if (req.getUserPrincipal() == null) {
            try {
                req.authenticate(resp);
            } catch (Exception ex) {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        }
        if (role != null && !req.isUserInRole(role)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        return null;
    }
    
}
