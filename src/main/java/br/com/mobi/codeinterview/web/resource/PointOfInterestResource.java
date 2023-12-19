package br.com.mobi.codeinterview.web.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PointOfInterestResource {

    private Long id;

    @NotBlank(message = "The point must have a name")
    private String name;

    @NotNull(message = "The radius must not be NULL")
    private Integer radius;

    @NotNull(message = "Latitude must not be NULL")
    private Double latitude;

    @NotNull(message = "Longitude must not be NULL")
    private Double longitude;
}
