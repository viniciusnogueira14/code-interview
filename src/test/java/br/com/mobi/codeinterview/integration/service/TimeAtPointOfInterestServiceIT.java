package br.com.mobi.codeinterview.integration.service;

import br.com.mobi.codeinterview.service.TimeAtPointOfInterestService;
import br.com.mobi.codeinterview.web.resource.CarAtPointOfInterestResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class TimeAtPointOfInterestServiceIT {

    @Autowired
    private TimeAtPointOfInterestService service;

    @Test
    void getTimeSpentAtPointOfInterestTest_PlateIsNull() {
        LocalDateTime startDate = LocalDateTime.of(2018, 12, 13, 0, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2018, 12, 13, 23, 59, 59);
        Map<String, List<CarAtPointOfInterestResource>> result =
                service.getTimeSpentAtPointOfInterest(startDate, endDate, null);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(24, result.size());
        List<CarAtPointOfInterestResource> resources = result.get("PONTO 24");
        assertEquals(2, resources.size());
        resources.forEach(res -> {
            assertTrue(res.getPlate().equalsIgnoreCase("TESTE001")
                    || res.getPlate().equalsIgnoreCase("CAR0012"));
            assertEquals(84742, res.getTimeSpent());
        });

        result.remove("PONTO 24");
        assertTrue(result.values().stream().allMatch(point -> point.stream().allMatch(car -> car.getTimeSpent() == 0)));
    }

    @Test
    void getTimeSpentAtPointOfInterestTest_WithPlateAndDates() {
        LocalDateTime startDate = LocalDateTime.of(2018, 12, 13, 0, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2018, 12, 13, 23, 59, 59);
        Map<String, List<CarAtPointOfInterestResource>> result =
                service.getTimeSpentAtPointOfInterest(startDate, endDate, "TESTE001");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(24, result.size());
        List<CarAtPointOfInterestResource> resources = result.get("PONTO 24");
        assertEquals(1, resources.size());
        resources.forEach(res -> {
            assertTrue(res.getPlate().equalsIgnoreCase("TESTE001"));
            assertEquals(84742, res.getTimeSpent());
        });

        result.remove("PONTO 24");
        assertTrue(result.values().stream().allMatch(point -> point.stream().allMatch(car -> car.getTimeSpent() == 0)));
    }

    @Test
    void getTimeSpentAtPointOfInterestTest_DatesAreNull() {
        Map<String, List<CarAtPointOfInterestResource>> result =
                service.getTimeSpentAtPointOfInterest(null, null, "TESTE001");
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(24, result.size());

        List<CarAtPointOfInterestResource> point01 = result.get("PONTO 1");
        assertEquals(1, point01.size());
        point01.forEach(res -> {
            assertTrue(res.getPlate().equalsIgnoreCase("TESTE001"));
            assertEquals(4367, res.getTimeSpent());
        });

        List<CarAtPointOfInterestResource> point02 = result.get("PONTO 2");
        assertEquals(1, point02.size());
        point02.forEach(res -> {
            assertTrue(res.getPlate().equalsIgnoreCase("TESTE001"));
            assertEquals(8611, res.getTimeSpent());
        });

        List<CarAtPointOfInterestResource> point24 = result.get("PONTO 24");
        assertEquals(1, point24.size());
        point24.forEach(res -> {
            assertTrue(res.getPlate().equalsIgnoreCase("TESTE001"));
            assertEquals(659774, res.getTimeSpent());
        });

        result.remove("PONTO 1");
        result.remove("PONTO 2");
        result.remove("PONTO 24");
        assertTrue(result.values().stream().allMatch(point -> point.stream().allMatch(car -> car.getTimeSpent() == 0)));
    }
}
