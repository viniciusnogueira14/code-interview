package br.com.mobi.codeinterview.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "POINT_OF_INTEREST")
public class PointOfInterest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_point-of-interest")
    @SequenceGenerator(name = "seq_point-of-interest", sequenceName = "SEQ_POI", allocationSize = 1)
    @Column(name = "POI_ID", precision = 18, nullable = false, unique = true)
    private Long id;

    @Column(name = "POI_NAME", length = 100, nullable = false)
    private String name;

    @Column(name = "POI_RADIUS", precision = 9, nullable = false)
    private Integer radius;

    @Column(name = "POI_LATITUDE", precision = 18, scale = 10, nullable = false)
    private Double latitude;

    @Column(name = "POI_LONGITUDE", precision = 18, scale = 10, nullable = false)
    private Double longitude;
}
