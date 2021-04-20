package cz.muni.fi.pa165.bottler.data.dao;

import cz.muni.fi.pa165.bottler.data.model.Bottle;
import cz.muni.fi.pa165.bottler.data.model.TestResult;
import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Implementation of TestResultDao interface
 * basic CRUD operations for TestResult enitty
 *
 * @author Josef Ludvicek <374268@mail.muni.cz>
 */
@Repository
public class TestResultDaoImpl implements TestResultDao {

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public TestResultDaoImpl() {
    }

    public TestResultDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public TestResult create(TestResult t) {
        if (t == null) {
            throw new IllegalArgumentException("TestResult is null");
        }
        if (t.getId() != null)
            throw new IllegalArgumentException("TestResult already has assigned ID");

        try {
            entityManager.persist(t);
        } catch (PersistenceException e) {
            throw new IllegalArgumentException("Incorrect attributes in TestResult object", e);
        }

        return t;
    }

    @Override
    public TestResult update(TestResult t) {
        entityManager.merge(t);
        return t;
    }

    @Override
    public void remove(TestResult t) {
        entityManager.remove(entityManager.merge(t));
    }

    @Override
    public TestResult findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return entityManager.find(TestResult.class, id);
    }

    @Override
    public List<TestResult> findByBottle(Bottle bottle) {

        TypedQuery<TestResult> q = entityManager.createQuery(
                "SELECT test from TestResult  test WHERE test.bottle = :bottle",
                TestResult.class);

        q.setParameter("bottle", bottle);

        return q.getResultList();
    }

    @Override
    public List<TestResult> findByTime(DateTime from, DateTime to) {
        if (from == null)
            throw new IllegalArgumentException("Date from cannot be null");
        if (to == null)
            throw new IllegalArgumentException("Date to cannot be null");

        TypedQuery<TestResult> q = entityManager.createQuery(
                "SELECT test FROM TestResult test WHERE test.time BETWEEN :since AND :to",
                TestResult.class);

        q.setParameter("since", from);
        q.setParameter("to", to);

        return q.getResultList();
    }

    @Override
    public List<TestResult> findByToxicity(Boolean toxic) {
        TypedQuery<TestResult> q = entityManager.createQuery(
                "SELECT t FROM TestResult t WHERE t.toxic = :toxic",
                TestResult.class);
        q.setParameter("toxic", toxic);
        return q.getResultList();
    }

    @Override
    public List<TestResult> getAll() {
        TypedQuery<TestResult> q = entityManager.createQuery(
                "SELECT t FROM TestResult t",
                TestResult.class);
        return q.getResultList();
    }
}
