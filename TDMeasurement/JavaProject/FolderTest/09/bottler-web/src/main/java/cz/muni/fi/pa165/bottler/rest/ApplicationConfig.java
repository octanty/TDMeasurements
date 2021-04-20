package cz.muni.fi.pa165.bottler.rest;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 * Jackson REST settings
 * 
 * @author Vaclav Mach <374430@mail.muni.cz>
 */
@javax.ws.rs.ApplicationPath("rest")
public class ApplicationConfig extends Application {
    
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(cz.muni.fi.pa165.bottler.rest.InvalidDataMapper.class);
        resources.add(cz.muni.fi.pa165.bottler.rest.resources.ProducersResource.class);
        resources.add(cz.muni.fi.pa165.bottler.rest.resources.StoresResource.class);
    }
}