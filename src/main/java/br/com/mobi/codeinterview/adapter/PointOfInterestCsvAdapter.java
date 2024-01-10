package br.com.mobi.codeinterview.adapter;

import br.com.mobi.codeinterview.database.entity.PointOfInterest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class PointOfInterestCsvAdapter {

    public List<PointOfInterest> toEntity(List<String[]> csvLines) {
        return csvLines.stream()
                .filter(line -> Objects.nonNull(line[0])
                        && Objects.nonNull(line[1])
                        && Objects.nonNull(line[2])
                        && Objects.nonNull(line[3]))
                .map(line ->
                PointOfInterest.builder()
                        .name(line[0])
                        .radius(Integer.valueOf(line[1]))
                        .latitude(Double.valueOf(line[2]))
                        .longitude(Double.valueOf(line[3]))
                        .build())
                .collect(Collectors.toList());
    }
}
