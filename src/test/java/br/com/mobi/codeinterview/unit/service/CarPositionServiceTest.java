package br.com.mobi.codeinterview.unit.service;

import br.com.mobi.codeinterview.adapter.CarPositionCsvAdapter;
import br.com.mobi.codeinterview.database.entity.CarPosition;
import br.com.mobi.codeinterview.database.repository.CarPositionRepository;
import br.com.mobi.codeinterview.service.CarPositionService;
import br.com.mobi.codeinterview.web.resource.CarPositionResource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.util.ReflectionUtils;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarPositionServiceTest {

    @Mock
    private Logger logger;
    @Mock
    private ModelMapper mapper;
    @Mock
    private CarPositionCsvAdapter csvAdapter;
    @Mock
    private CarPositionRepository repository;

    @InjectMocks
    private CarPositionService service;


    @Test
    void importCarPositionsFromCsvFileTest_HappyPath() {
        service.importCarPositionsFromCsvFile();
        verify(csvAdapter,times(1)).toEntity(anyList());
        verify(repository, times(1)).saveAll(anyCollection());
    }

    @Test
    void importCarPositionsFromCsvFileTest_ThrowsException() throws Exception {
        Field loggerField = ReflectionUtils.findFields(CarPositionService.class,
                field -> field.getName().equalsIgnoreCase("LOGGER"),
                ReflectionUtils.HierarchyTraversalMode.TOP_DOWN).get(0);
        setLoggerMock(loggerField, logger);

        when(repository.saveAll(anyCollection())).thenThrow(NullPointerException.class);
        service.importCarPositionsFromCsvFile();
        verify(logger, times(1)).error(any(String.class), nullable(String.class));
    }

    @Test
    void createCarPositionTest() {
        CarPositionResource resource = getResourceMock01();
        CarPosition entity = getEntityMock01();
        when(mapper.map(resource, CarPosition.class)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.map(entity, CarPositionResource.class)).thenReturn(resource);

        CarPositionResource result = service.create(getResourceMock01());

        assertNotNull(result);
        assertEquals(entity.getPlate(), result.getPlate());
        assertEquals(entity.getPositionDate(), result.getPositionDate());
        assertEquals(entity.getSpeed(), result.getSpeed());
        assertEquals(entity.getLatitude(), result.getLatitude());
        assertEquals(entity.getLongitude(), result.getLongitude());
        assertEquals(entity.getIgnition(), result.getIgnition());
    }

    @Test
    void createCarPositionsTest() {
        CarPositionResource resource01 = getResourceMock01();
        CarPositionResource resource02 = getResourceMock02();
        CarPositionResource resource03 = getResourceMock03();
        List<CarPositionResource> resources = Arrays.asList(resource01, resource02, resource03);

        CarPosition entity01 = getEntityMock01();
        CarPosition entity02 = getEntityMock02();
        CarPosition entity03 = getEntityMock03();
        List<CarPosition> entities = Arrays.asList(entity01, entity02, entity03);

        when(mapper.map(resource01, CarPosition.class)).thenReturn(entity01);
        when(mapper.map(resource02, CarPosition.class)).thenReturn(entity02);
        when(mapper.map(resource03, CarPosition.class)).thenReturn(entity03);
        when(repository.saveAll(entities)).thenReturn(entities);
        when(mapper.map(entity01, CarPositionResource.class)).thenReturn(resource01);
        when(mapper.map(entity02, CarPositionResource.class)).thenReturn(resource02);
        when(mapper.map(entity03, CarPositionResource.class)).thenReturn(resource03);

        List<CarPositionResource> result = service.create(resources);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(resources.size(), result.size());

        CarPositionResource result01 = result.stream()
                .filter(res -> res.getPlate().equalsIgnoreCase(entity01.getPlate()))
                .findFirst().get();
        assertNotNull(result01);
        assertEquals(entity01.getPlate(), result01.getPlate());
        assertEquals(entity01.getPositionDate(), result01.getPositionDate());
        assertEquals(entity01.getSpeed(), result01.getSpeed());
        assertEquals(entity01.getLatitude(), result01.getLatitude());
        assertEquals(entity01.getLongitude(), result01.getLongitude());
        assertEquals(entity01.getIgnition(), result01.getIgnition());

        CarPositionResource result02 = result.stream()
                .filter(res -> res.getPlate().equalsIgnoreCase(entity02.getPlate()))
                .findFirst().get();
        assertNotNull(result02);
        assertEquals(entity02.getPlate(), result02.getPlate());
        assertEquals(entity02.getPositionDate(), result02.getPositionDate());
        assertEquals(entity02.getSpeed(), result02.getSpeed());
        assertEquals(entity02.getLatitude(), result02.getLatitude());
        assertEquals(entity02.getLongitude(), result02.getLongitude());
        assertEquals(entity02.getIgnition(), result02.getIgnition());

        CarPositionResource result03 = result.stream()
                .filter(res -> res.getPlate().equalsIgnoreCase(entity03.getPlate()))
                .findFirst().get();
        assertNotNull(result03);
        assertEquals(entity03.getPlate(), result03.getPlate());
        assertEquals(entity03.getPositionDate(), result03.getPositionDate());
        assertEquals(entity03.getSpeed(), result03.getSpeed());
        assertEquals(entity03.getLatitude(), result03.getLatitude());
        assertEquals(entity03.getLongitude(), result03.getLongitude());
        assertEquals(entity03.getIgnition(), result03.getIgnition());
    }

    private CarPositionResource getResourceMock01() {
        CarPositionResource resource = new CarPositionResource();
        resource.setPlate("MOCK001");
        resource.setPositionDate(LocalDateTime.of(2018, 12, 13, 0, 6, 31));
        resource.setSpeed(111);
        resource.setLatitude(-25.3649141);
        resource.setLongitude(-51.469891);
        resource.setIgnition(false);

        return resource;
    }

    private CarPositionResource getResourceMock02() {
        CarPositionResource resource = new CarPositionResource();
        resource.setPlate("MOCK002");
        resource.setPositionDate(LocalDateTime.of(2022, 1, 30, 16, 46, 58));
        resource.setSpeed(222);
        resource.setLatitude(-25.3648976);
        resource.setLongitude(-51.4698788);
        resource.setIgnition(true);

        return resource;
    }

    private CarPositionResource getResourceMock03() {
        CarPositionResource resource = new CarPositionResource();
        resource.setPlate("MOCK003");
        resource.setPositionDate(LocalDateTime.of(2020, 6, 20, 10, 26, 31));
        resource.setSpeed(333);
        resource.setLatitude(-25.3648968);
        resource.setLongitude(-51.4699221);
        resource.setIgnition(false);

        return resource;
    }

    private CarPosition getEntityMock01() {
        CarPosition entity = new CarPosition();
        entity.setPlate("MOCK001");
        entity.setPositionDate(LocalDateTime.of(2018, 12, 13, 0, 6, 31));
        entity.setSpeed(111);
        entity.setLatitude(-25.3649141);
        entity.setLongitude(-51.469891);
        entity.setIgnition(false);

        return entity;
    }

    private CarPosition getEntityMock02() {
        CarPosition entity = new CarPosition();
        entity.setPlate("MOCK002");
        entity.setPositionDate(LocalDateTime.of(2022, 1, 30, 16, 46, 58));
        entity.setSpeed(222);
        entity.setLatitude(-25.3648976);
        entity.setLongitude(-51.4698788);
        entity.setIgnition(true);

        return entity;
    }

    private CarPosition getEntityMock03() {
        CarPosition entity = new CarPosition();
        entity.setPlate("MOCK003");
        entity.setPositionDate(LocalDateTime.of(2020, 6, 20, 10, 26, 31));
        entity.setSpeed(333);
        entity.setLatitude(-25.3648968);
        entity.setLongitude(-51.4699221);
        entity.setIgnition(false);

        return entity;
    }

    static void setLoggerMock(Field field, Logger loggerMock) throws Exception {
        field.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        field.set(null, loggerMock);
    }

}
