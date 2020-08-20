package com.webflux.f1.webfluxf1api.constructor;

import com.webflux.f1.webfluxf1api.audit.TimeLog;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "constructors")
@DynamicUpdate
public class Constructor {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "constructor_id", unique = true, nullable = false)
  private String constructorId;

  @Column(name = "url", nullable = false)
  private String url;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "nationality", nullable = false)
  private String nationality;

  @Embedded @Builder.Default @EqualsAndHashCode.Exclude private TimeLog timeLog = new TimeLog();
}
