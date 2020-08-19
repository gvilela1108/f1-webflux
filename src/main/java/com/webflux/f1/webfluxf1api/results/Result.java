package com.webflux.f1.webfluxf1api.results;

import com.webflux.f1.webfluxf1api.audit.TimeLog;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "results")
@DynamicUpdate
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "season", nullable = false)
    private Integer season;

    @Column(name = "round", nullable = false)
    private String round;

    @Column(name = "numero", nullable = false)
    private String number;

    @Column(name = "position", nullable = false)
    private String position;

    @Column(name = "position_text", nullable = false)
    private String positionText;

    @Column(name = "points", nullable = false)
    private Integer points;

    @Column(name = "driver_id", nullable = false)
    private String driverId;

    @Column(name = "constructor_id", nullable = false)
    private String constructorId;

    @Column(name = "grid", nullable = false)
    private String grid;

    @Column(name = "laps", nullable = false)
    private Integer laps;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "time_millis")
    private Integer millis;

    @Column(name = "time", nullable = false)
    private String time;

    @Embedded
    @Builder.Default
    @EqualsAndHashCode.Exclude
    private TimeLog timeLog = new TimeLog();
}
