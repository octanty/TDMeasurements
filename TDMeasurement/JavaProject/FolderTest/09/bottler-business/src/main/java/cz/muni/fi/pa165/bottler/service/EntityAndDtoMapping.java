package cz.muni.fi.pa165.bottler.service;

import cz.muni.fi.pa165.bottler.data.dto.*;
import cz.muni.fi.pa165.bottler.data.model.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jakub Macák <374551@mail.muni.cz>
 * @author Vaclav Mach <374430@mail.muni.cz>
 */
public class EntityAndDtoMapping {

    /**
     * Mapping LiquorDto to Liquor.
     */
    public static Liquor liquorDtoToLiquor(LiquorDto liquorDto) {
        return EntityAndDtoMapping.liquorDtoToLiquor(liquorDto, false);
    }

    /**
     * Mapping LiquorDto to Liquor.
     */
    public static Liquor liquorDtoToLiquor(LiquorDto liquorDto, boolean disableIdMapping) {

        if (liquorDto == null) {
            return null;
        }

        Liquor liquor = new Liquor();
        if (!disableIdMapping) {
            liquor.setId(liquorDto.getId());
        }
        liquor.setName(liquorDto.getName());
        liquor.setAlcoholPercentage(liquorDto.getAlcoholPercentage());
        liquor.setEan(liquorDto.getEan());
        liquor.setProducer(producerDtoToProducer(liquorDto.getProducer()));
        liquor.setVolume(liquorDto.getVolume());

        return liquor;
    }

    /**
     * Mapping Liquor to LiquorDto.
     */
    public static LiquorDto liquorToLiquorDto(Liquor liquor) {

        if (liquor == null) {
            return null;
        }

        LiquorDto liquorDto = new LiquorDto();
        liquorDto.setId(liquor.getId());
        liquorDto.setName(liquor.getName());
        liquorDto.setAlcoholPercentage(liquor.getAlcoholPercentage());
        liquorDto.setEan(liquor.getEan());
        liquorDto.setProducer(producerToProducerDto(liquor.getProducer()));
        liquorDto.setVolume(liquor.getVolume());
        return liquorDto;
    }

    /**
     * Mapping List<Liquor> to List<LiquorDto>.
     */
    public static List<LiquorDto> liquorToLiquorDto(List<Liquor> liquors) {

        List<LiquorDto> result = new ArrayList<>();
        for (Liquor liquor : liquors) {
            result.add(liquorToLiquorDto(liquor));
        }
        return result;
    }

    /**
     * Mapping ProducerDto to Producer.
     */
    public static Producer producerDtoToProducer(ProducerDto producerDto) {
        return producerDtoToProducer(producerDto, false);
    }

    /**
     * Mapping ProducerDto to Producer.
     */
    public static Producer producerDtoToProducer(ProducerDto producerDto, boolean disableIdMapping) {

        if (producerDto == null) {
            return null;
        }

        Producer producer = new Producer();
        if (!disableIdMapping) {
            producer.setId(producerDto.getId());
        }
        producer.setName(producerDto.getName());
        producer.setAddress(producerDto.getAddress());
        producer.setIco(producerDto.getIco());
        return producer;
    }

    /**
     * Mapping Producer to ProducerDto.
     */
    public static ProducerDto producerToProducerDto(Producer producer) {

        if (producer == null) {
            return null;
        }

        ProducerDto producerDto = new ProducerDto();
        producerDto.setName(producer.getName());
        producerDto.setAddress(producer.getAddress());
        producerDto.setIco(producer.getIco());
        producerDto.setId(producer.getId());
        return producerDto;
    }

    /**
     * Mapping List<Producer> to List<ProducerDto>.
     */
    public static List<ProducerDto> producerToProducerDto(List<Producer> producers) {

        List<ProducerDto> result = new ArrayList<>();
        for (Producer producer : producers) {
            result.add(producerToProducerDto(producer));
        }
        return result;
    }

    /**
     * Mapping BottleDto to Bottle.
     */
    public static Bottle bottleDtoToBottle(BottleDto bottleDto) {
        return bottleDtoToBottle(bottleDto, false);
    }

    /**
     * Mapping BottleDto to Bottle.
     */
    public static Bottle bottleDtoToBottle(BottleDto bottleDto, boolean disableIdMapping) {

        if (bottleDto == null) {
            return null;
        }

        Bottle bottle = new Bottle();
        if (!disableIdMapping) {
            bottle.setId(bottleDto.getId());
        }
        bottle.setLotCode(bottleDto.getLotCode());
        bottle.setProducedDate(bottleDto.getProducedDate());
        bottle.setStamp(stampDtoToStamp(bottleDto.getStamp()));
        bottle.setStore(storeDtoToStore(bottleDto.getStore()));
        bottle.setLiquor(liquorDtoToLiquor(bottleDto.getLiquor()));
        bottle.setSold(bottleDto.isSold());
        return bottle;
    }

    /**
     * Mapping Bottle to BottleDto.
     */
    public static BottleDto bottleToBottleDto(Bottle bottle) {

        if (bottle == null) {
            return null;
        }

        BottleDto bottleDto = new BottleDto();
        bottleDto.setId(bottle.getId());
        bottleDto.setLotCode(bottle.getLotCode());
        bottleDto.setProducedDate(bottle.getProducedDate());
        bottleDto.setStamp(stampToStampDto(bottle.getStamp()));
        bottleDto.setStore(storeToStoreDto(bottle.getStore()));
        bottleDto.setLiquor(liquorToLiquorDto(bottle.getLiquor()));
        bottleDto.setSold(bottle.isSold());
        return bottleDto;
    }

    /**
     * Mapping List<Bottle> to List<BottleDto>.
     */
    public static List<BottleDto> bottleToBottleDto(List<Bottle> bottles) {

        List<BottleDto> result = new ArrayList<>();
        for (Bottle bottle : bottles) {
            result.add(bottleToBottleDto(bottle));
        }
        return result;
    }

    /**
     * Mapping StampDto to Stamp.
     */
    public static Stamp stampDtoToStamp(StampDto stampDto) {
        return stampDtoToStamp(stampDto, false);
    }

    /**
     * Mapping StampDto to Stamp.
     */
    public static Stamp stampDtoToStamp(StampDto stampDto, boolean disableIdMapping) {

        if (stampDto == null) {
            return null;
        }

        Stamp stamp = new Stamp();
        if (!disableIdMapping) {
            stamp.setId(stampDto.getId());
        }
        stamp.setIssuedDate(stampDto.getIssuedDate());
        stamp.setNumberOfStamp(stampDto.getNumberOfStamp());
        return stamp;
    }

    /**
     * Mapping StampDto to Stamp.
     */
    public static StampDto stampToStampDto(Stamp stamp) {

        if (stamp == null) {
            return null;
        }

        StampDto stampDto = new StampDto();
        stampDto.setId(stamp.getId());
        stampDto.setIssuedDate(stamp.getIssuedDate());
        stampDto.setNumberOfStamp(stamp.getNumberOfStamp());
        return stampDto;
    }

    /**
     * Mapping List<Stamp> to List<StampDto>.
     */
    public static List<StampDto> stampToStampDto(List<Stamp> stamps) {

        List<StampDto> result = new ArrayList<>();
        for (Stamp stamp : stamps) {
            result.add(stampToStampDto(stamp));
        }
        return result;
    }

    /**
     * Mapping StoreDto to Store.
     */
    public static Store storeDtoToStore(StoreDto storeDto) {
        return storeDtoToStore(storeDto, false);
    }
        
    /**
     * Mapping StoreDto to Store.
     */
    public static Store storeDtoToStore(StoreDto storeDto, boolean disableIdMapping) {

        if (storeDto == null) {
            return null;
        }

        Store store = new Store();
        if (!disableIdMapping) {
            store.setId(storeDto.getId());
        }
        store.setIco(storeDto.getIco());
        store.setName(storeDto.getName());
        store.setAddress(storeDto.getAddress());
        return store;
    }

    /**
     * Mapping Store to StoreDto.
     */
    public static StoreDto storeToStoreDto(Store store) {

        if (store == null) {
            return null;
        }

        StoreDto storeDto = new StoreDto();
        storeDto.setId(store.getId());
        storeDto.setIco(store.getIco());
        storeDto.setName(store.getName());
        storeDto.setAddress(store.getAddress());
        return storeDto;
    }

    /**
     * Mapping List<Store> to List<StoreDto>.
     */
    public static List<StoreDto> storeToStoreDto(List<Store> stores) {

        List<StoreDto> result = new ArrayList<>();
        for (Store store : stores) {
            result.add(storeToStoreDto(store));
        }
        return result;
    }

    /**
     * Mapping TestResultDto to TestResult.
     */
    public static TestResult testResultDtoToTestResult(TestResultDto testResultDto) {
        return testResultDtoToTestResult(testResultDto, false);
    }
    
    /**
     * Mapping TestResultDto to TestResult.
     */
    public static TestResult testResultDtoToTestResult(TestResultDto testResultDto, boolean disableIdMapping) {

        if (testResultDto == null) {
            return null;
        }

        TestResult testResult = new TestResult();
         if (!disableIdMapping) {
            testResult.setId(testResultDto.getId());
        }
        testResult.setBottle(bottleDtoToBottle(testResultDto.getBottle(), disableIdMapping));
        testResult.setTime(testResultDto.getTime());
        testResult.setToxic(testResultDto.isToxic());
        return testResult;
    }

    /**
     * Mapping TestResult to TestResultDto.
     */
    public static TestResultDto testResultToTestResultDto(TestResult testResult) {

        if (testResult == null) {
            return null;
        }

        TestResultDto testResultDto = new TestResultDto();
        testResultDto.setId(testResult.getId());
        testResultDto.setBottle(bottleToBottleDto(testResult.getBottle()));
        testResultDto.setTime(testResult.getTime());
        testResultDto.setToxic(testResult.isToxic());
        return testResultDto;
    }

    /**
     * Mapping List<TestResult> to List<TestResultDto>.
     */
    public static List<TestResultDto> testResultToTestResultDto(List<TestResult> testResults) {

        List<TestResultDto> result = new ArrayList<>();
        for (TestResult testResult : testResults) {
            result.add(testResultToTestResultDto(testResult));
        }
        return result;
    }
}
