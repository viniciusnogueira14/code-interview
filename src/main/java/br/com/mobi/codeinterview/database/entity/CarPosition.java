package br.com.mobi.codeinterview.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CAR_POSITION")
@IdClass(CarPositionKey.class)
public class CarPosition implements Serializable {

    @Id
    @Column(name = "PLATE", length = 10, nullable = false)
    private String plate;

    @Id
    @Column(name = "POSITION_DATE", nullable = false)
    private LocalDateTime positionDate;

    @Column(name = "SPEED", precision = 3)
    private Integer speed;

    @Column(name = "LONGITUDE", precision = 18, scale = 10)
    private Double longitude;

    @Column(name = "LATITUDE", precision = 18, scale = 10)
    private Double latitude;

    @Column(name = "IGNITION", nullable = false)
    private Boolean ignition;
}
