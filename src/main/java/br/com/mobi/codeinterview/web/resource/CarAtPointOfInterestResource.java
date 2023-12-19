package br.com.mobi.codeinterview.web.resource;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarAtPointOfInterestResource {

    private String plate;
    private Long timeSpent;
}
