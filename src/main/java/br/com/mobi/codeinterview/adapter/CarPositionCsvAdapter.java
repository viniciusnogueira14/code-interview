package br.com.mobi.codeinterview.adapter;

import br.com.mobi.codeinterview.database.entity.CarPosition;
import br.com.mobi.codeinterview.util.DateUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class CarPositionCsvAdapter {

    public List<CarPosition> toEntity(List<String[]> csvLines) {
        return csvLines.stream()
                .filter(line -> Objects.nonNull(line[0]) && Objects.nonNull(line[1]))
                .map(line -> {
                    String trimmedDate = line[1]
                            .substring(4, line[1].indexOf("("))
                            .replace("GMT", "")
                            .trim();

                    return CarPosition.builder()
                            .plate(line[0])
                            .positionDate(DateUtils.fromString(trimmedDate, "MMM dd uuuu HH:mm:ss ZZZ"))
                            .speed(Integer.valueOf(line[2]))
                            .longitude(Double.valueOf(line[3]))
                            .latitude(Double.valueOf(line[4]))
                            .ignition(Boolean.valueOf(line[5]))
                            .build();
                })
                .collect(Collectors.toList());
    }
}
