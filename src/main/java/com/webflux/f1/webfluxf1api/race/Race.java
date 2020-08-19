package com.webflux.f1.webfluxf1api.race;

import com.webflux.f1.webfluxf1api.audit.TimeLog;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "races")
@DynamicUpdate
public class Race {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "season", nullable = false)
    private Integer season;

    @Column(name = "round", nullable = false)
    private String round;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "race_name", nullable = false)
    private String raceName;

    @Column(name = "circuit_id", nullable = false)
    private String circuitId;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "time", nullable = false)
    private String time;

    @Embedded
    @Builder.Default
    @EqualsAndHashCode.Exclude
    private TimeLog timeLog = new TimeLog();

}
