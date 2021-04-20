package cz.muni.fi.pa165.bottler.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;

/**
 * @author Josef Ludvicek <374268@mail.muni.cz>
 */
public class BottlerTestDb {

    private EntityManager entityManager;
    private EntityManagerFactory emFactory;


    /**
     * This method starts in memory derby database and creates entity manager.
     * Created entity manager is accessible via getEntityManager on this class
     *
     * @throws SQLException           if cant connect to memory database
     * @throws ClassNotFoundException if org.apache.derby.jdbc.EmbeddedDriver not found
     */
    public void startDb() throws SQLException, ClassNotFoundException {
        //start db server
        System.out.println("Starting in-memory database for unit tests");
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        DriverManager.getConnection("jdbc:derby:memory:unit-testing-jpa;create=true").close();

        //create entity manager
        System.out.println("Building JPA EntityManager for unit tests");
        emFactory = Persistence.createEntityManagerFactory("persistenceUnitForTests");
        entityManager = emFactory.createEntityManager();
    }

    /**
     * This method stops closes entity manager and emFactory connections and then stops database
     *
     * @throws SQLException if stopping db fails
     */
    public void stopDb() throws SQLException {
        System.out.println("Shuting down Hibernate JPA layer.");
        if (entityManager != null) {
            entityManager.close();
        }
        if (emFactory != null) {
            emFactory.close();
        }
        System.out.println("Stopping in-memory database.");
        try {
            DriverManager.getConnection("jdbc:derby:memory:unit-testing-jpa;shutdown=true").close();
        } catch (SQLNonTransientConnectionException ex) {
            if (ex.getErrorCode() != 45000) {
                throw ex;
            }
            // Shutdown success
        }

    }


    /**
     * Get entity manager instance for started in memory db.
     *
     * @return entity manager instance
     * @throws IllegalStateException if db wasn't successfully started and entity manager instance created
     */
    public EntityManager getEntityManager() throws IllegalStateException {
        if (entityManager == null)
            throw new IllegalStateException("Memory database probably wasn't successfully started");
        return entityManager;
    }
}




