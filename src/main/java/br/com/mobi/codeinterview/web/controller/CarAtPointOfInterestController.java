package br.com.mobi.codeinterview.web.controller;

import br.com.mobi.codeinterview.service.TimeAtPointOfInterestService;
import br.com.mobi.codeinterview.web.resource.CarAtPointOfInterestResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Tag(name = "Time at Points of Interest", description = "API to calculate and return the time that each Vehicle spent inside the radius of each Point of Interest")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class CarAtPointOfInterestController {

    private final TimeAtPointOfInterestService service;

    @Operation(summary = "Return a map with each Point of Interest as the key and a list of Vehicles with the time spent inside the Point of Interest radius in Seconds")
    @ApiResponse(responseCode = "200", description = "Search executed successfully")
    @GetMapping(value = "/time-at-poi")
    public ResponseEntity<Map<String, List<CarAtPointOfInterestResource>>> getTimeAtPointOfInterest(
            @RequestParam(name = "startAt", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startAt,
            @RequestParam(name = "endAt", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endAt,
            @RequestParam(name = "plate", required = false) String plate) {

        Map<String, List<CarAtPointOfInterestResource>> result = service.getTimeSpentAtPointOfInterest(startAt, endAt, plate);

        return ResponseEntity.ok().body(result);
    }
}
