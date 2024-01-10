package br.com.mobi.codeinterview.web.controller;

import br.com.mobi.codeinterview.service.PointOfInterestService;
import br.com.mobi.codeinterview.web.resource.PointOfInterestResource;
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

@Tag(name = "Point of Interest", description = "API to manage the Points of Interest")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class PointOfInterestController {
    private final PointOfInterestService service;

    @Operation(summary = "Create one new PointOfInterest model")
    @ApiResponse(responseCode = "201", description = "PointOfInterest successfully created")
    @PostMapping(value = "/point-of-interest")
    public ResponseEntity<PointOfInterestResource> createPosition(@Valid @RequestBody PointOfInterestResource resource) {
        PointOfInterestResource savedResource = service.create(resource);
        String url = getURI() + "/" + savedResource.getId();

        return ResponseEntity.created(URI.create(url))
                .body(savedResource);
    }

    @Operation(summary = "Create a list of new PointOfInterest model")
    @ApiResponse(responseCode = "201", description = "PointsOfInterest successfully created")
    @PostMapping(value = "/points-of-interest")
    public ResponseEntity<List<PointOfInterestResource>> createPositions(@Valid @RequestBody List<PointOfInterestResource> resources) {
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
