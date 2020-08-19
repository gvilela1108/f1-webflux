package com.webflux.f1.webfluxf1api.audit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeLog {

  @Column(name = "dat_creation", nullable = false)
  private LocalDateTime createAt;

  @Column(name = "dat_update", nullable = false)
  private LocalDateTime updatedAt;

  @PrePersist
  public void prePersist() {
    createAt = LocalDateTime.now();
    updatedAt = LocalDateTime.now();
  }

  @PreUpdate
  public void preData() {
    if (createAt == null) {
      createAt = LocalDateTime.now();
    }
    updatedAt = LocalDateTime.now();
  }

  @PostUpdate
  @PostLoad
  public void postModifications() {
    updatedAt = LocalDateTime.now();
  }
}
