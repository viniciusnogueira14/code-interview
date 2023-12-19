package br.com.mobi.codeinterview.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class LocationUtils {

    private static final int EARTH_RADIUS = 6371;

    private static double haversine(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }

    public static Double getDistanceBetweenGeoCoordinates(double startLat, double startLong, double endLat, double endLong) {

        double dLat = Math.toRadians((endLat - startLat));
        double dLong = Math.toRadians((endLong - startLong));

        startLat = Math.toRadians(startLat);
        endLat = Math.toRadians(endLat);

        double a = haversine(dLat) + Math.cos(startLat) * Math.cos(endLat) * haversine(dLong);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return (EARTH_RADIUS * c) * 1000;
    }
}
