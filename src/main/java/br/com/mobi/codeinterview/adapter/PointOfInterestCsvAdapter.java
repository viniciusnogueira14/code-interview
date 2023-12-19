package br.com.mobi.codeinterview.adapter;

import br.com.mobi.codeinterview.database.entity.PointOfInterest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PointOfInterestCsvAdapter {

    public List<PointOfInterest> toEntity(List<String[]> csvLines) {
        return csvLines.stream().map(line ->
                PointOfInterest.builder()
                        .name(line[0])
                        .radius(Integer.valueOf(line[1]))
                        .latitude(Double.valueOf(line[2]))
                        .longitude(Double.valueOf(line[3]))
                        .build())
                .collect(Collectors.toList());
    }
}
