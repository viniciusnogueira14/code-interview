package br.com.mobi.codeinterview.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CarPositionKey implements Serializable {

    private String plate;
    private LocalDateTime positionDate;
}
