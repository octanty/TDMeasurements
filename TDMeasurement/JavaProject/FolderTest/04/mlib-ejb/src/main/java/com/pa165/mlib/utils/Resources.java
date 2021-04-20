package com.pa165.mlib.utils;

import java.util.logging.Logger;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 *
 * @author ibek
 */
public class Resources {

    @Produces
    Logger getLogger(InjectionPoint ip) {
        String category = ip.getMember().getDeclaringClass().getName();
        return Logger.getLogger(category);
    }

}
