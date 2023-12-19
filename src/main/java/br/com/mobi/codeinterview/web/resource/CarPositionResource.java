package br.com.mobi.codeinterview.web.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CarPositionResource {

    @NotBlank(message = "The PLATE must not be NULL or EMPTY")
    @Size(max = 10, message = "The PLATE must be lower than 10 characters")
    private String plate;

    @NotNull(message = "The Position Date must not be NULL")
    @PastOrPresent
    private LocalDateTime positionDate;

    private Integer speed;

    private Double longitude;

    private Double latitude;

    @NotNull(message = "The Ignition flag must not be NULL")
    private Boolean ignition;

    @JsonIgnore
    private Double distance;
}
