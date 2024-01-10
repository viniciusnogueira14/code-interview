package br.com.mobi.codeinterview.unit.service;

import br.com.mobi.codeinterview.web.resource.CarPositionResource;
import br.com.mobi.codeinterview.web.resource.PointOfInterestResource;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MockObjects {

    public static List<PointOfInterestResource> getPointsOfInterestMockValues_TwoPositionsInsidePoiTest() {

        PointOfInterestResource poi = new PointOfInterestResource();
        poi.setName("PONTO MOCK");
        poi.setRadius(350);
        poi.setLatitude(-25.363333);
        poi.setLongitude(-51.468333);

        return Collections.singletonList(poi);
    }

    public static List<PointOfInterestResource> getPointsOfInterestMockValues_NoPositionsInsidePoiTest() {

        PointOfInterestResource poi = new PointOfInterestResource();
        poi.setName("PONTO MOCK");
        poi.setRadius(350);
        poi.setLatitude(-25.4405008);
        poi.setLongitude(-49.249949);

        return Collections.singletonList(poi);
    }

    static Stream<List<CarPositionResource>> getCarPositionsMockValue() {
        return Stream.of(
                getCarPositionsMockValues_ThreePositions_AllInsidePOI(),
                getCarPositionsMockValues_ThreePositions_TwoInsidePOI_InTheBeginning(),
                getCarPositionsMockValues_FourPositions_TwoInsidePOI_InTheMiddle(),
                getCarPositionsMockValues_FivePositions_TwoInsidePOI_InTheEnd()
        );
    }

    private static List<CarPositionResource> getCarPositionsMockValues_ThreePositions_AllInsidePOI() {
        CarPositionResource position01 = new CarPositionResource();
        position01.setPlate("TESTE001");
        position01.setPositionDate(LocalDateTime.of(2018, 12, 13, 0, 6, 31));
        position01.setSpeed(0);
        position01.setLongitude(-51.4699545);
        position01.setLatitude(-25.3649083);
        position01.setIgnition(false);

        CarPositionResource position02 = new CarPositionResource();
        position02.setPlate("TESTE001");
        position02.setPositionDate(LocalDateTime.of(2018, 12, 13, 0, 36, 34));
        position02.setSpeed(0);
        position02.setLongitude(-51.4699038);
        position02.setLatitude(-25.3648861);
        position02.setIgnition(false);

        CarPositionResource position03 = new CarPositionResource();
        position03.setPlate("TESTE001");
        position03.setPositionDate(LocalDateTime.of(2018, 12, 13, 1, 6, 37));
        position03.setSpeed(0);
        position03.setLongitude(-51.4699203);
        position03.setLatitude(-25.3648535);
        position03.setIgnition(false);

        return Arrays.asList(position01, position02, position03);
    }

    private static List<CarPositionResource> getCarPositionsMockValues_ThreePositions_TwoInsidePOI_InTheBeginning() {

        CarPositionResource position01 = new CarPositionResource();
        position01.setPlate("TESTE001");
        position01.setPositionDate(LocalDateTime.of(2018, 12, 13, 0, 6, 31));
        position01.setSpeed(0);
        position01.setLongitude(-51.4699545);
        position01.setLatitude(-25.3649083);
        position01.setIgnition(false);

        CarPositionResource position02 = new CarPositionResource();
        position02.setPlate("TESTE001");
        position02.setPositionDate(LocalDateTime.of(2018, 12, 13, 0, 36, 34));
        position02.setSpeed(0);
        position02.setLongitude(-51.4699038);
        position02.setLatitude(-25.3648861);
        position02.setIgnition(false);

        CarPositionResource position03 = new CarPositionResource();
        position03.setPlate("TESTE001");
        position03.setPositionDate(LocalDateTime.of(2018, 12, 13, 1, 6, 37));
        position03.setSpeed(0);
        position03.setLongitude(-51.6582968);
        position03.setLatitude(-25.6920566);
        position03.setIgnition(false);

        return Arrays.asList(position01, position02, position03);
    }

    private static List<CarPositionResource> getCarPositionsMockValues_FourPositions_TwoInsidePOI_InTheMiddle() {

        CarPositionResource position01 = new CarPositionResource();
        position01.setPlate("TESTE001");
        position01.setPositionDate(LocalDateTime.of(2018, 12, 13, 0, 6, 31));
        position01.setSpeed(0);
        position01.setLongitude(-51.6435782);
        position01.setLatitude(-25.7007805);

        position01.setIgnition(false);

        CarPositionResource position02 = new CarPositionResource();
        position02.setPlate("TESTE001");
        position02.setPositionDate(LocalDateTime.of(2018, 12, 13, 0, 36, 34));
        position02.setSpeed(0);
        position02.setLongitude(-51.4699545);
        position02.setLatitude(-25.3649083);
        position02.setIgnition(false);

        CarPositionResource position03 = new CarPositionResource();
        position03.setPlate("TESTE001");
        position03.setPositionDate(LocalDateTime.of(2018, 12, 13, 1, 6, 37));
        position03.setSpeed(0);
        position03.setLongitude(-51.4699038);
        position03.setLatitude(-25.3648861);
        position03.setIgnition(false);

        CarPositionResource position04 = new CarPositionResource();
        position04.setPlate("TESTE001");
        position04.setPositionDate(LocalDateTime.of(2018, 12, 13, 1, 36, 40));
        position04.setSpeed(0);
        position04.setLongitude(-51.5060971);
        position04.setLatitude(-25.5659405);
        position04.setIgnition(false);

        return Arrays.asList(position01, position02, position03, position04);
    }

    private static List<CarPositionResource> getCarPositionsMockValues_FivePositions_TwoInsidePOI_InTheEnd() {

        CarPositionResource position01 = new CarPositionResource();
        position01.setPlate("TESTE001");
        position01.setPositionDate(LocalDateTime.of(2018, 12, 13, 1, 6, 37));
        position01.setSpeed(0);
        position01.setLongitude(-51.6435782);
        position01.setLatitude(-25.7007805);
        position01.setIgnition(false);

        CarPositionResource position02 = new CarPositionResource();
        position02.setPlate("TESTE001");
        position02.setPositionDate(LocalDateTime.of(2018, 12, 13, 1, 36, 40));
        position02.setSpeed(0);
        position02.setLongitude(-51.5060971);
        position02.setLatitude(-25.5659405);
        position02.setIgnition(false);

        CarPositionResource position03 = new CarPositionResource();
        position03.setPlate("TESTE001");
        position03.setPositionDate(LocalDateTime.of(2018, 12, 13, 2, 6, 43));
        position03.setSpeed(0);
        position03.setLongitude(-51.6582968);
        position03.setLatitude(-25.6920566);
        position03.setIgnition(false);

        CarPositionResource position04 = new CarPositionResource();
        position04.setPlate("TESTE001");
        position04.setPositionDate(LocalDateTime.of(2018, 12, 13, 0, 6, 31));
        position04.setSpeed(0);
        position04.setLongitude(-51.4699545);
        position04.setLatitude(-25.3649083);
        position04.setIgnition(false);

        CarPositionResource position05 = new CarPositionResource();
        position05.setPlate("TESTE001");
        position05.setPositionDate(LocalDateTime.of(2018, 12, 13, 0, 36, 34));
        position05.setSpeed(0);
        position05.setLongitude(-51.4699038);
        position05.setLatitude(-25.3648861);
        position05.setIgnition(false);

        return Arrays.asList(position01, position02, position03, position04, position05);
    }
}
