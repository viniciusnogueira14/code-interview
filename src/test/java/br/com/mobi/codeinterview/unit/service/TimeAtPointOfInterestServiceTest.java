package br.com.mobi.codeinterview.unit.service;

import br.com.mobi.codeinterview.service.CarPositionService;
import br.com.mobi.codeinterview.service.PointOfInterestService;
import br.com.mobi.codeinterview.service.TimeAtPointOfInterestService;
import br.com.mobi.codeinterview.web.resource.CarAtPointOfInterestResource;
import br.com.mobi.codeinterview.web.resource.CarPositionResource;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TimeAtPointOfInterestServiceTest {

    @Mock
    private CarPositionService carPositionService;

    @Mock
    private PointOfInterestService pointOfInterestService;

    @InjectMocks
    private TimeAtPointOfInterestService service;


    @ParameterizedTest
    @MethodSource("br.com.mobi.codeinterview.unit.service.MockObjects#getCarPositionsMockValue")
    void getTimeSpentAtPointOfInterestTest_TwoPositionsInsidePOI(List<CarPositionResource> carPositions) {
        when(pointOfInterestService.findAll())
                .thenReturn(MockObjects.getPointsOfInterestMockValues_TwoPositionsInsidePoiTest());
        when(carPositionService.findByParameters(anyString(), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(carPositions);

        Map<String, List<CarAtPointOfInterestResource>> result =
                service.getTimeSpentAtPointOfInterest(LocalDateTime.now(), LocalDateTime.now(), "TESTE001");

        assertNotNull(result);

        List<CarAtPointOfInterestResource> timeSpent = result.get("PONTO MOCK");
        assertNotNull(timeSpent);
        assertEquals(1, timeSpent.size());

        CarAtPointOfInterestResource carAtPoi = timeSpent.get(0);
        assertEquals("TESTE001", carAtPoi.getPlate());
        assertEquals(3606, carAtPoi.getTimeSpent());
    }

    @ParameterizedTest
    @MethodSource("br.com.mobi.codeinterview.unit.service.MockObjects#getCarPositionsMockValue")
    void getTimeSpentAtPointOfInterestTest_NoPositionsInsidePOI(List<CarPositionResource> carPositions) {
        when(pointOfInterestService.findAll())
                .thenReturn(MockObjects.getPointsOfInterestMockValues_NoPositionsInsidePoiTest());
        when(carPositionService.findByParameters(anyString(), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(carPositions);

        Map<String, List<CarAtPointOfInterestResource>> result =
                service.getTimeSpentAtPointOfInterest(LocalDateTime.now(), LocalDateTime.now(), "TESTE001");

        assertNotNull(result);

        List<CarAtPointOfInterestResource> timeSpent = result.get("PONTO MOCK");
        assertNotNull(timeSpent);
        assertEquals(1, timeSpent.size());

        CarAtPointOfInterestResource carAtPoi = timeSpent.get(0);
        assertEquals("TESTE001", carAtPoi.getPlate());
        assertEquals(0, carAtPoi.getTimeSpent());
    }
}
