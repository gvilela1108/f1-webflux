package com.webflux.f1.webfluxf1api.driver;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "drivers")
@DynamicUpdate
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "driver_id", unique = true, nullable = false)
    private String driverId;

    @Column(name = "permanent_number", nullable = false)
    private Integer permanentNumber;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "given_name", nullable = false)
    private String givenName;

    @Column(name = "family_name", nullable = false)
    private String familyName;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "nationality", nullable = false)
    private String nationality;

    @Embedded
    @Builder.Default
    @EqualsAndHashCode.Exclude
    private TimeLog timeLog = new TimeLog();




}
