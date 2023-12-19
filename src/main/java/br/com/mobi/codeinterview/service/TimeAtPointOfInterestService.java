package br.com.mobi.codeinterview.service;

import br.com.mobi.codeinterview.util.LocationUtils;
import br.com.mobi.codeinterview.web.resource.CarAtPointOfInterestResource;
import br.com.mobi.codeinterview.web.resource.CarPositionResource;
import br.com.mobi.codeinterview.web.resource.PointOfInterestResource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
@RequiredArgsConstructor
public class TimeAtPointOfInterestService {

    private final CarPositionService carPositionService;
    private final PointOfInterestService pointOfInterestService;

    public Map<String, List<CarAtPointOfInterestResource>> getTimeSpentAtPointOfInterest(
            LocalDateTime startAt, LocalDateTime endAt, String plate) {

        List<PointOfInterestResource> pointsOfInterest = pointOfInterestService.findAll();
        List<CarPositionResource> carPositions = carPositionService.findByParameters(plate, startAt, endAt);

        Map<String, List<CarAtPointOfInterestResource>> result = new ConcurrentHashMap<>();

        pointsOfInterest.forEach(pointOfInterestResource ->
                        result.put(pointOfInterestResource.getName(),
                                getTimesByPointOfInterest(pointOfInterestResource, carPositions))
                );

        return result;
    }

    private List<CarAtPointOfInterestResource> getTimesByPointOfInterest(
            PointOfInterestResource point, List<CarPositionResource> carPositions) {

        carPositions.forEach(carPosition ->
                carPosition.setDistance(
                        LocationUtils.getDistanceBetweenGeoCoordinates(point.getLatitude(), point.getLongitude(),
                                carPosition.getLatitude(), carPosition.getLongitude())));

        Map<String, List<CarPositionResource>> result = carPositions.stream()
                .collect(groupingBy(CarPositionResource::getPlate, Collectors.toList()));

        return result.values().stream()
                .map(positions -> calculateTimeSpentInsidePointOfInterest(positions, point))
                .collect(Collectors.toList());
    }

    private CarAtPointOfInterestResource calculateTimeSpentInsidePointOfInterest(
            List<CarPositionResource> carPositions, PointOfInterestResource pointOfInterest) {

        boolean isIntoThePoint = false;
        LocalDateTime gotIntoThePointTimestamp = LocalDateTime.now();
        long totalTimeSpent = 0L;

        List<CarPositionResource> sortedCarPositions = carPositions.stream()
                .sorted(Comparator.comparing(CarPositionResource::getPositionDate)).collect(Collectors.toList());

        for (CarPositionResource position : sortedCarPositions) {
            if (isInsideThePointOfInterest(position.getDistance(), pointOfInterest.getRadius())) {
                if (!isIntoThePoint) {
                    isIntoThePoint = true;
                } else {
                    totalTimeSpent = totalTimeSpent + Duration.between(gotIntoThePointTimestamp, position.getPositionDate()).getSeconds();
                }

                gotIntoThePointTimestamp = position.getPositionDate();
            } else {
                if (isIntoThePoint) {
                    totalTimeSpent = totalTimeSpent + Duration.between(gotIntoThePointTimestamp, position.getPositionDate()).getSeconds();
                    isIntoThePoint = false;
                }
            }
        }

        String plate = carPositions.stream()
                .findAny()
                .map(CarPositionResource::getPlate)
                .orElseGet(String::new);

        return new CarAtPointOfInterestResource(plate, totalTimeSpent);
    }

    private boolean isInsideThePointOfInterest(Double positionDistance, Integer pointRadius) {
        return positionDistance < pointRadius;
    }

}
