package br.com.mobi.codeinterview.service;

import br.com.mobi.codeinterview.adapter.CarPositionCsvAdapter;
import br.com.mobi.codeinterview.database.entity.CarPosition;
import br.com.mobi.codeinterview.database.repository.CarPositionRepository;
import br.com.mobi.codeinterview.util.CsvUtils;
import br.com.mobi.codeinterview.web.resource.CarPositionResource;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarPositionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarPositionService.class);

    private final ModelMapper mapper;
    private final CarPositionCsvAdapter csvAdapter;
    private final CarPositionRepository repository;

    @Bean
    public void importCarPositionsFromCsvFile() {
        try {
            URI csvPath = ClassLoader.getSystemResource("db-import/posicoes.csv").toURI();
            List<String[]> csvLines = CsvUtils.readAll(Paths.get(csvPath));

            repository.saveAll(csvAdapter.toEntity(csvLines));
        } catch (Exception ex) {
            LOGGER.error("ERROR while loading CarPositions fromCSV File: {}", ex.getMessage());
        }
    }

    public CarPositionResource create(CarPositionResource resource) {
        CarPosition entity = mapper.map(resource, CarPosition.class);
        CarPosition persisted = repository.save(entity);
        return mapper.map(persisted, CarPositionResource.class);
    }

    public List<CarPositionResource> create(List<CarPositionResource> resources) {
        List<CarPosition> entities = resources.stream()
                .map(carPositionResource -> mapper.map(carPositionResource, CarPosition.class))
                .collect(Collectors.toList());
        List<CarPosition> savedEntities = repository.saveAll(entities);
        return savedEntities.stream()
                .map(carPosition -> mapper.map(carPosition, CarPositionResource.class))
                .collect(Collectors.toList());
    }

    public List<CarPositionResource> findByParameters(String plate, LocalDateTime startAt, LocalDateTime endAt) {
        List<CarPosition> carPositions = new ArrayList<>();
        if (plate != null && startAt != null && endAt != null) {
            carPositions = repository.findByPlateAndPositionDateBetween(plate, startAt, endAt);
        } else if (plate == null && startAt != null && endAt != null) {
            carPositions = repository.findByPositionDateBetween(startAt, endAt);
        } else if (plate != null && startAt == null && endAt == null) {
            carPositions = repository.findByPlate(plate);
        }

        return carPositions.stream()
                .map(carPosition -> mapper.map(carPosition, CarPositionResource.class))
                .collect(Collectors.toList());
    }
}
