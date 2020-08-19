package com.webflux.f1.webfluxf1api.fatestlap;

import com.webflux.f1.webfluxf1api.audit.TimeLog;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fatests_lap")
@DynamicUpdate
public class FastestLap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "season", nullable = false)
    private Integer season;

    @Column(name = "round", nullable = false)
    private String round;

    @Column(name = "rank", nullable = false)
    private Integer rank;

    @Column(name = "lap", nullable = false)
    private Integer lap;

    @Column(name = "time", nullable = false)
    private String time;

    @Column(name = "average_speed_units", nullable = false)
    private String units;

    @Column(name = "average_speed", nullable = false)
    private Float speed;

    @Embedded
    @Builder.Default
    @EqualsAndHashCode.Exclude
    private TimeLog timeLog = new TimeLog();

}
