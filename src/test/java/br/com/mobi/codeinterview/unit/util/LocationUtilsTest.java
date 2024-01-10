package br.com.mobi.codeinterview.unit.util;

import br.com.mobi.codeinterview.util.LocationUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Website used to validate the distance between GeoCoordinates:
 *      https://gps-coordinates.org/distance-between-coordinates.php
 */
class LocationUtilsTest {

    @ParameterizedTest
    @CsvSource({
            "0, 0, 0, 0, 0, 0",
            "-90, -180, -90, -180, 0, 0",
            "90, 180, 90, 180, 0, 0",
            "0, 0, -90, -180, 10007540, 10007550",
            "-90, -180, 0, 0, 10007540, 10007550",
            "0, 0, 90, 180, 10007540, 10007550",
            "90, 180, 0, 0, 10007540, 10007550",
            "-25.38934, -51.46089, -25.3927624, -51.457283, 520, 530",
            "-25.3649083, -51.4699545, -25.363333, -51.468333, 235, 245",
            "-25.3649083, -51.4699545, -25.56742701740896, -51.47653363645077, 22525, 22535"
    })
    void getDistanceBetweenGeoCoordinatesTest(
            double startLatitude, double startLongitude,
            double endLatitude, double endLongitude,
            double lowestVariation, double highestVariation) {
        double distance = LocationUtils.getDistanceBetweenGeoCoordinates(startLatitude, startLongitude, endLatitude, endLongitude);
        assertTrue(distance >= lowestVariation);
        assertTrue(distance <= highestVariation);
    }
}
