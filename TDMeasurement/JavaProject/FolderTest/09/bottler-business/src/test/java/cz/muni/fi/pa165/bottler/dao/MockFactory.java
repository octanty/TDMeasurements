/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.bottler.dao;

import cz.muni.fi.pa165.bottler.data.model.Bottle;
import cz.muni.fi.pa165.bottler.data.model.Liquor;
import cz.muni.fi.pa165.bottler.data.model.Producer;
import cz.muni.fi.pa165.bottler.data.model.Stamp;
import cz.muni.fi.pa165.bottler.data.model.Store;
import cz.muni.fi.pa165.bottler.data.model.TestResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import org.joda.time.DateTime;

/**
 * A factory for domain objects that mocks their data.
 *
 * Downloaded from: http://java.dzone.com/articles/patterns-better-unit-testing
 *
 * Additional code by Vaclav Mach
 *
 * @author David Green
 * @author Vaclav Mach <374430@mail.muni.cz>
 */
public abstract class MockFactory<T> {

    private static Map<Class<?>, MockFactory<?>> factories =
            new HashMap<>();

    static {
        register(new MockProducerFactory());
        register(new MockLiquorFactory());
        register(new MockBottleFactory());
        register(new MockStampFactory());
        register(new MockStoreFactory());
        register(new MockTestResultFactory());
    }

    private static void register(MockFactory<?> mockFactory) {
        factories.put(mockFactory.domainClass, mockFactory);
    }

    @SuppressWarnings("unchecked")
    public static <T> MockFactory<T> on(Class<T> domainClass) {
        MockFactory<?> factory = factories.get(domainClass);
        if (factory == null) {
            throw new IllegalStateException(
                    "Did you forget to register a mock factory for "
                    + domainClass.getClass().getName() + "?");
        }
        return (MockFactory<T>) factory;
    }
    private final Class<T> domainClass;
    private int seed;

    protected MockFactory(Class<T> domainClass) {
        if (domainClass.getAnnotation(Entity.class) == null) {
            throw new IllegalArgumentException();
        }
        this.domainClass = domainClass;
    }

    /**
     * Create several objects
     *
     * @param entityManager the entity manager, or null if the mocked objects
     * should not be persisted
     * @param count the number of objects to create
     * @return the created objects
     */
    public List<T> create(EntityManager entityManager, int count) {
        return create(entityManager, count, true);
    }
    
    /**
     * Create several objects
     *
     * @param entityManager the entity manager, or null if the mocked objects
     * should not be persisted
     * @param count the number of objects to create
     * @param setId default true (when persisted by Entity manager, or generated automatically)
     * @return the created objects
     */
    public List<T> create(EntityManager entityManager, int count, boolean setId) {
        List<T> mocks = new ArrayList<>(count);
        for (int x = 0; x < count; ++x) {
            T t = create(entityManager, setId);
            mocks.add(t);
        }
        return mocks;
    }
    

    /**
     * Create a single object.
     * If enitityManager null, it will add IDs too
     *
     * @param entityManager the entity manager, or null if the mocked object
     * should not be persisted
     * @return the mocked object
     */
    public T create(EntityManager entityManager) {
       return create(entityManager, true);
    }
    
    /**
     * Create a single object.
     * If enitityManager null, it will add IDs too
     *
     * @param entityManager the entity manager, or null if the mocked object
     * should not be persisted
     * @return the mocked object
     */
    public T create(EntityManager entityManager, boolean setId) {
        T mock;
        try {
            mock = domainClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            // must have a default constructor
            throw new IllegalStateException();
        }
        populate(++seed, mock, entityManager, setId);
        if (entityManager != null) {
            entityManager.getTransaction().begin();
            entityManager.persist(mock);
            entityManager.getTransaction().commit();
        }
        return mock;
    }
    
    
    

    /**
     * Populate the given domain object with data
     *
     * @param seed a seed that may be used to create data
     * @param mock the domain object to populate
     * @param em entity manager
     * @param setId whether to setID or not
     */
    protected abstract void populate(int seed, T mock, EntityManager em, boolean setId);

    private static class MockProducerFactory extends MockFactory<Producer> {

        public MockProducerFactory() {
            super(Producer.class);
        }

        @Override
        protected void populate(int seed, Producer mock, EntityManager em, boolean setId) {

            if (em == null && setId) {
                mock.setId((long) seed);
            }

            mock.setName("Producer " + seed);
            mock.setAddress("Address " + seed);
            mock.setIco(seed);
        }
    }

    private static class MockStoreFactory extends MockFactory<Store> {

        public MockStoreFactory() {
            super(Store.class);
        }

        @Override
        protected void populate(int seed, Store mock, EntityManager em, boolean setId) {

            if (em == null && setId) {
                mock.setId((long) seed);
            }

            mock.setName("Store " + seed);
            mock.setAddress("Address " + seed);
            mock.setIco(seed);
        }
    }

    private static class MockLiquorFactory extends MockFactory<Liquor> {

        public MockLiquorFactory() {
            super(Liquor.class);
        }

        @Override
        protected void populate(int seed, Liquor mock, EntityManager em, boolean setId) {

            if (em == null && setId) {
                mock.setId((long) seed);
            }

            Producer p = MockFactory.on(Producer.class).create(em, setId);

            mock.setAlcoholPercentage(Math.random());
            mock.setEan("Ean " + seed);
            mock.setVolume(Math.random() + 0.2);
            mock.setName("Name " + seed);
            mock.setProducer(p);
        }
    }

    private static class MockStampFactory extends MockFactory<Stamp> {

        public MockStampFactory() {
            super(Stamp.class);
        }

        @Override
        protected void populate(int seed, Stamp mock, EntityManager em, boolean setId) {

            if (em == null && setId) {
                mock.setId((long) seed);
            }

            mock.setIssuedDate(new DateTime());
            /**
             * TODO: Fix generating stamp number
             */
            mock.setNumberOfStamp("stamp no " + seed);

        }
    }

    private static class MockBottleFactory extends MockFactory<Bottle> {

        public MockBottleFactory() {
            super(Bottle.class);
        }

        @Override
        protected void populate(int seed, Bottle mock, EntityManager em, boolean setId) {

            if (em == null && setId) {
                mock.setId((long) seed);
            }

            Liquor l = MockFactory.on(Liquor.class).create(em, setId);
            Stamp s = MockFactory.on(Stamp.class).create(em, setId);

            mock.setLiquor(l);
            mock.setLotCode("lot code " + seed);
            mock.setProducedDate(new DateTime());
            mock.setSold(false);
            mock.setStamp(s);
            mock.setStore(null);

        }
    }

    private static class MockTestResultFactory extends MockFactory<TestResult> {

        public MockTestResultFactory() {
            super(TestResult.class);
        }

        @Override
        protected void populate(int seed, TestResult mock, EntityManager em, boolean setId) {

            if (em == null && setId) {
                mock.setId((long) seed);
            }

            Bottle b = MockFactory.on(Bottle.class).create(em, setId);

            mock.setBottle(b);
            mock.setTime(new DateTime());

            boolean toxic = (seed % 2 == 0);
            mock.setToxic(toxic);

        }
    }
}
