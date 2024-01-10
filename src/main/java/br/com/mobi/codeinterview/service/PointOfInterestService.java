package br.com.mobi.codeinterview.service;

import br.com.mobi.codeinterview.adapter.PointOfInterestCsvAdapter;
import br.com.mobi.codeinterview.database.entity.PointOfInterest;
import br.com.mobi.codeinterview.database.repository.PointOfInterestRepository;
import br.com.mobi.codeinterview.util.CsvUtils;
import br.com.mobi.codeinterview.web.resource.PointOfInterestResource;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PointOfInterestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PointOfInterestService.class);

    private final ModelMapper mapper;
    private final PointOfInterestCsvAdapter csvAdapter;
    private final PointOfInterestRepository repository;

    @Bean
    public void importPointOfInterestFromCsvFile() {
        try {
            URI csvPath = ClassLoader.getSystemResource("db-import/base_pois_def.csv").toURI();
            List<String[]> csvLines = CsvUtils.readAll(Paths.get(csvPath));

            repository.saveAll(csvAdapter.toEntity(csvLines));
        } catch (Exception ex) {
            LOGGER.error("ERROR while loading PointsOfInterest fromCSV File: {}", ex.getMessage());
        }
    }

    public PointOfInterestResource create(PointOfInterestResource resource) {
        PointOfInterest entity = mapper.map(resource, PointOfInterest.class);
        PointOfInterest persisted = repository.save(entity);
        return mapper.map(persisted, PointOfInterestResource.class);
    }

    public List<PointOfInterestResource> create(List<PointOfInterestResource> resources) {
        List<PointOfInterest> entities = resources.stream()
                .map(resource -> mapper.map(resource, PointOfInterest.class))
                .collect(Collectors.toList());
        List<PointOfInterest> savedEntities = repository.saveAll(entities);
        return savedEntities.stream()
                .map(entity -> mapper.map(entity, PointOfInterestResource.class))
                .collect(Collectors.toList());
    }

    public List<PointOfInterestResource> findAll() {
        List<PointOfInterest> entities = repository.findAll();
        return entities.stream()
                .map(entity -> mapper.map(entity, PointOfInterestResource.class))
                .collect(Collectors.toList());
    }
}
