package br.com.mobi.codeinterview.unit.service;

import br.com.mobi.codeinterview.adapter.PointOfInterestCsvAdapter;
import br.com.mobi.codeinterview.database.entity.PointOfInterest;
import br.com.mobi.codeinterview.database.repository.PointOfInterestRepository;
import br.com.mobi.codeinterview.service.PointOfInterestService;
import br.com.mobi.codeinterview.web.resource.PointOfInterestResource;
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
class PointOfInterestServiceTest {

    @Mock
    private Logger logger;
    @Mock
    private ModelMapper mapper;
    @Mock
    private PointOfInterestCsvAdapter csvAdapter;
    @Mock
    private PointOfInterestRepository repository;

    @InjectMocks
    private PointOfInterestService service;

    @Test
    void importCarPositionsFromCsvFileTest_HappyPath() {
        service.importPointOfInterestFromCsvFile();
        verify(csvAdapter,times(1)).toEntity(anyList());
        verify(repository, times(1)).saveAll(anyCollection());
    }

    @Test
    void importCarPositionsFromCsvFileTest_ThrowsException() throws Exception {
        Field loggerField = ReflectionUtils.findFields(PointOfInterestService.class,
                field -> field.getName().equalsIgnoreCase("LOGGER"),
                ReflectionUtils.HierarchyTraversalMode.TOP_DOWN).get(0);
        setLoggerMock(loggerField, logger);

        when(repository.saveAll(anyCollection())).thenThrow(NullPointerException.class);
        service.importPointOfInterestFromCsvFile();
        verify(logger, times(1)).error(any(String.class), nullable(String.class));
    }

    @Test
    void createPointOfInterestTest_HappyPath() {
        PointOfInterestResource resource = getPointOfInterestResourceMock01();
        PointOfInterest entity = getPointOfInterestEntityMock01();
        when(mapper.map(resource, PointOfInterest.class)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.map(entity, PointOfInterestResource.class)).thenReturn(resource);

        PointOfInterestResource result = service.create(getPointOfInterestResourceMock01());

        assertNotNull(result);
        assertEquals(entity.getName(), result.getName());
        assertEquals(entity.getRadius(), result.getRadius());
        assertEquals(entity.getLatitude(), result.getLatitude());
        assertEquals(entity.getLongitude(), result.getLongitude());
    }

    @Test
    void createPointsOfInterestTest_HappyPath() {
        PointOfInterestResource resource01 = getPointOfInterestResourceMock01();
        PointOfInterestResource resource02 = getPointOfInterestResourceMock02();
        PointOfInterestResource resource03 = getPointOfInterestResourceMock03();
        List<PointOfInterestResource> resources = Arrays.asList(resource01, resource02, resource03);

        PointOfInterest entity01 = getPointOfInterestEntityMock01();
        PointOfInterest entity02 = getPointOfInterestEntityMock02();
        PointOfInterest entity03 = getPointOfInterestEntityMock03();
        List<PointOfInterest> entities = Arrays.asList(entity01, entity02, entity03);

        when(mapper.map(resource01, PointOfInterest.class)).thenReturn(entity01);
        when(mapper.map(resource02, PointOfInterest.class)).thenReturn(entity02);
        when(mapper.map(resource03, PointOfInterest.class)).thenReturn(entity03);
        when(repository.saveAll(entities)).thenReturn(entities);
        when(mapper.map(entity01, PointOfInterestResource.class)).thenReturn(resource01);
        when(mapper.map(entity02, PointOfInterestResource.class)).thenReturn(resource02);
        when(mapper.map(entity03, PointOfInterestResource.class)).thenReturn(resource03);

        List<PointOfInterestResource> result = service.create(resources);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(resources.size(), result.size());

        PointOfInterestResource result01 = result.stream()
                .filter(res -> res.getName().equalsIgnoreCase(entity01.getName()))
                .findFirst().get();
        assertNotNull(result01);
        assertEquals(entity01.getName(), result01.getName());
        assertEquals(entity01.getRadius(), result01.getRadius());
        assertEquals(entity01.getLatitude(), result01.getLatitude());
        assertEquals(entity01.getLongitude(), result01.getLongitude());

        PointOfInterestResource result02 = result.stream()
                .filter(res -> res.getName().equalsIgnoreCase(entity02.getName()))
                .findFirst().get();
        assertNotNull(result02);
        assertEquals(entity02.getName(), result02.getName());
        assertEquals(entity02.getRadius(), result02.getRadius());
        assertEquals(entity02.getLatitude(), result02.getLatitude());
        assertEquals(entity02.getLongitude(), result02.getLongitude());

        PointOfInterestResource result03 = result.stream()
                .filter(res -> res.getName().equalsIgnoreCase(entity03.getName()))
                .findFirst().get();
        assertNotNull(result03);
        assertEquals(entity03.getName(), result03.getName());
        assertEquals(entity03.getRadius(), result03.getRadius());
        assertEquals(entity03.getLatitude(), result03.getLatitude());
        assertEquals(entity03.getLongitude(), result03.getLongitude());
    }

    private PointOfInterestResource getPointOfInterestResourceMock01() {
        PointOfInterestResource resource = new PointOfInterestResource();
        resource.setName("POINT MOCK 01");
        resource.setRadius(111);
        resource.setLatitude(-25.56742701740896);
        resource.setLongitude(-51.47653363645077);

        return resource;
    }

    private PointOfInterestResource getPointOfInterestResourceMock02() {
        PointOfInterestResource resource = new PointOfInterestResource();
        resource.setName("POINT MOCK 02");
        resource.setRadius(222);
        resource.setLatitude(-25.568056);
        resource.setLongitude(-51.480278);

        return resource;
    }

    private PointOfInterestResource getPointOfInterestResourceMock03() {
        PointOfInterestResource resource = new PointOfInterestResource();
        resource.setName("POINT MOCK 03");
        resource.setRadius(333);
        resource.setLatitude(-22.718252406214955);
        resource.setLongitude(-46.78627558343578);

        return resource;
    }

    private PointOfInterest getPointOfInterestEntityMock01() {
        PointOfInterest entity = new PointOfInterest();
        entity.setName("POINT MOCK 01");
        entity.setRadius(111);
        entity.setLatitude(-25.56742701740896);
        entity.setLongitude(-51.47653363645077);

        return entity;
    }

    private PointOfInterest getPointOfInterestEntityMock02() {
        PointOfInterest entity = new PointOfInterest();
        entity.setName("POINT MOCK 02");
        entity.setRadius(222);
        entity.setLatitude(-25.568056);
        entity.setLongitude(-51.480278);

        return entity;
    }

    private PointOfInterest getPointOfInterestEntityMock03() {
        PointOfInterest entity = new PointOfInterest();
        entity.setName("POINT MOCK 03");
        entity.setRadius(333);
        entity.setLatitude(-22.718252406214955);
        entity.setLongitude(-46.78627558343578);

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
