package com.pa165.mlib.rest;

import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author ibek
 */
@ApplicationPath("rest")
public class RestApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
        resources.add(GenreResource.class);
        resources.add(ArtistResource.class);
        return resources;
    }

}
