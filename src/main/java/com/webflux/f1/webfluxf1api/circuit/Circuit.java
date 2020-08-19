package com.webflux.f1.webfluxf1api.circuit;

import com.webflux.f1.webfluxf1api.audit.TimeLog;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "circuits")
@DynamicUpdate
public class Circuit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "circuit_id", unique = true, nullable = false)
    private String circuitId;

    @Column(name = "circuit_url", nullable = false)
    private String url;

    @Column(name = "circuit_name", nullable = false)
    private String circuitName;

    @Column(name = "circuit_latitude", nullable = false)
    private Float latitude;

    @Column(name = "circuit_longitude", nullable = false)
    private Float longitude;

    @Column(name = "circuit_localization", nullable = false)
    private String locality;

    @Column(name = "circuit_country", nullable = false)
    private String country;

    @Embedded
    @Builder.Default
    @EqualsAndHashCode.Exclude
    private TimeLog timeLog = new TimeLog();
}
