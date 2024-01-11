package br.com.mobi.codeinterview.integration.service;

import br.com.mobi.codeinterview.database.entity.CarPosition;
import br.com.mobi.codeinterview.database.repository.CarPositionRepository;
import br.com.mobi.codeinterview.service.CarPositionService;
import br.com.mobi.codeinterview.web.resource.CarPositionResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CarPositionServiceIT {

    @Autowired
    private CarPositionService service;

    @Autowired
    private CarPositionRepository repository;


    @Test
    void createCarPositionTest_HappyPath() {
        CarPositionResource resource = getResourceMock();
        service.create(resource);

        List<CarPosition> found = repository.findByPlate(resource.getPlate());
        assertNotNull(found);
        assertEquals(1, found.size());

        CarPosition entity = found.get(0);
        assertEquals(resource.getPlate(), entity.getPlate());
        assertEquals(resource.getPositionDate(), entity.getPositionDate());
        assertEquals(resource.getSpeed(), entity.getSpeed());
        assertEquals(resource.getLatitude(), entity.getLatitude());
        assertEquals(resource.getLongitude(), entity.getLongitude());
        assertEquals(resource.getIgnition(), entity.getIgnition());
    }

    @Test
    void createCarPositionTest_PlateIsNull() {
        CarPositionResource resource = getResourceMock();
        resource.setPlate(null);

        assertThrows(DataIntegrityViolationException.class,
                () -> service.create(resource));
    }

    @Test
    void createCarPositionTest_PositionDateIsNull() {
        CarPositionResource resource = getResourceMock();
        resource.setPositionDate(null);

        assertThrows(DataIntegrityViolationException.class,
                () -> service.create(resource));
    }

    @Test
    void createCarPositionTest_SpeedIsNull() {
        CarPositionResource resource = getResourceMock();
        resource.setSpeed(null);

        service.create(resource);

        List<CarPosition> found = repository.findByPlate(resource.getPlate());
        assertNotNull(found);
        assertEquals(1, found.size());

        CarPosition entity = found.get(0);
        assertNull(entity.getSpeed());
        assertEquals(resource.getPlate(), entity.getPlate());
        assertEquals(resource.getPositionDate(), entity.getPositionDate());
        assertEquals(resource.getLatitude(), entity.getLatitude());
        assertEquals(resource.getLongitude(), entity.getLongitude());
        assertEquals(resource.getIgnition(), entity.getIgnition());
    }

    @Test
    void createCarPositionTest_LatitudeIsNull() {
        CarPositionResource resource = getResourceMock();
        resource.setLatitude(null);

        service.create(resource);

        List<CarPosition> found = repository.findByPlate(resource.getPlate());
        assertNotNull(found);
        assertEquals(1, found.size());

        CarPosition entity = found.get(0);
        assertNull(entity.getLatitude());
        assertEquals(resource.getPlate(), entity.getPlate());
        assertEquals(resource.getPositionDate(), entity.getPositionDate());
        assertEquals(resource.getSpeed(), entity.getSpeed());
        assertEquals(resource.getLongitude(), entity.getLongitude());
        assertEquals(resource.getIgnition(), entity.getIgnition());
    }

    @Test
    void createCarPositionTest_LongitudeIsNull() {
        CarPositionResource resource = getResourceMock();
        resource.setLongitude(null);

        service.create(resource);

        List<CarPosition> found = repository.findByPlate(resource.getPlate());
        assertNotNull(found);
        assertEquals(1, found.size());

        CarPosition entity = found.get(0);
        assertNull(entity.getLongitude());
        assertEquals(resource.getPlate(), entity.getPlate());
        assertEquals(resource.getPositionDate(), entity.getPositionDate());
        assertEquals(resource.getSpeed(), entity.getSpeed());
        assertEquals(resource.getLatitude(), entity.getLatitude());
        assertEquals(resource.getIgnition(), entity.getIgnition());
    }

    @Test
    void createCarPositionTest_IgnitionIsNull() {
        CarPositionResource resource = getResourceMock();
        resource.setIgnition(null);

        assertThrows(DataIntegrityViolationException.class,
                () -> service.create(resource));
    }

    private CarPositionResource getResourceMock() {
        CarPositionResource resource = new CarPositionResource();
        resource.setPlate("MOCK001");
        resource.setPositionDate(LocalDateTime.of(2018, 12, 13, 0, 6, 31));
        resource.setSpeed(111);
        resource.setLatitude(-25.3649141);
        resource.setLongitude(-51.469891);
        resource.setIgnition(false);

        return resource;
    }
}
