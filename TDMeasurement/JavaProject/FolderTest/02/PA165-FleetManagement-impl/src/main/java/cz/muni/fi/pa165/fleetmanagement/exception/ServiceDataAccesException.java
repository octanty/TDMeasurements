package cz.muni.fi.pa165.fleetmanagement.exception;

import org.springframework.dao.DataAccessException;

/**
 *
 * @author Peter Pavlík
 */
public class ServiceDataAccesException extends DataAccessException {

    public ServiceDataAccesException(String msg) {
        super(msg);
    }

    public ServiceDataAccesException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
