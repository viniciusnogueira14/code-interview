package br.com.mobi.codeinterview.web.controller;

import br.com.mobi.codeinterview.service.CarPositionService;
import br.com.mobi.codeinterview.web.resource.CarPositionResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Tag(name = "Car Positions", description = "API to manage the Car Positions")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class CarPositionController {

    private final CarPositionService service;

    @Operation(summary = "Create one new CarPosition model")
    @ApiResponse(responseCode = "201", description = "CarPosition successfully created")
    @PostMapping(value = "/car-position")
    public ResponseEntity<CarPositionResource> createPosition(@Valid @RequestBody CarPositionResource resource) {
        String url = getURI() + "?plate=" + resource.getPlate() + "&datePosition=" + resource.getPositionDate();

        return ResponseEntity.created(URI.create(url))
                .body(service.create(resource));
    }

    @Operation(summary = "Create a list of new CarPositions model")
    @ApiResponse(responseCode = "201", description = "CarPositions successfully created")
    @PostMapping(value = "/car-positions")
    public ResponseEntity<List<CarPositionResource>> createPositions(@Valid @RequestBody List<CarPositionResource> resources) {
        return ResponseEntity.created(URI.create(getURI()))
                .body(service.create(resources));
    }

    private String getURI() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (servletRequestAttributes != null) {
            HttpServletRequest request = servletRequestAttributes.getRequest();
            return request.getRequestURI();
        }

        return "";
    }
}
