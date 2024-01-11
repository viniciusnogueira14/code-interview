package br.com.mobi.codeinterview.integration.service;

import br.com.mobi.codeinterview.database.entity.PointOfInterest;
import br.com.mobi.codeinterview.database.repository.PointOfInterestRepository;
import br.com.mobi.codeinterview.service.PointOfInterestService;
import br.com.mobi.codeinterview.web.resource.PointOfInterestResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class PointOfInterestServiceIT {

    @Autowired
    private PointOfInterestService service;

    @Autowired
    private PointOfInterestRepository repository;


    @Test
    void createPointOfInterestTest_HappyPath() {
        PointOfInterestResource resource = getPointOfInterestResourceMock();
        PointOfInterestResource persisted = service.create(resource);

        Optional<PointOfInterest> result = repository.findById(persisted.getId());
        assertTrue(result.isPresent());

        PointOfInterest entity = result.get();
        assertEquals(persisted.getId(), entity.getId());
        assertEquals(resource.getName(), entity.getName());
        assertEquals(resource.getRadius(), entity.getRadius());
        assertEquals(resource.getLatitude(), entity.getLatitude());
        assertEquals(resource.getLongitude(), entity.getLongitude());
    }

    @Test
    void createPointOfInterestTest_NameIsNull() {
        PointOfInterestResource resource = getPointOfInterestResourceMock();
        resource.setName(null);

        assertThrows(DataIntegrityViolationException.class,
                () -> service.create(resource));
    }

    @Test
    void createPointOfInterestTest_RadiusIsNull() {
        PointOfInterestResource resource = getPointOfInterestResourceMock();
        resource.setRadius(null);

        assertThrows(DataIntegrityViolationException.class,
                () -> service.create(resource));
    }

    @Test
    void createPointOfInterestTest_LatitudeIsNull() {
        PointOfInterestResource resource = getPointOfInterestResourceMock();
        resource.setLatitude(null);

        assertThrows(DataIntegrityViolationException.class,
                () -> service.create(resource));
    }

    @Test
    void createPointOfInterestTest_LongitudeIsNull() {
        PointOfInterestResource resource = getPointOfInterestResourceMock();
        resource.setLongitude(null);

        assertThrows(DataIntegrityViolationException.class,
                () -> service.create(resource));
    }

    private PointOfInterestResource getPointOfInterestResourceMock() {
        PointOfInterestResource resource = new PointOfInterestResource();
        resource.setName("POINT MOCK 01");
        resource.setRadius(111);
        resource.setLatitude(-25.56742701740896);
        resource.setLongitude(-51.47653363645077);

        return resource;
    }

}
