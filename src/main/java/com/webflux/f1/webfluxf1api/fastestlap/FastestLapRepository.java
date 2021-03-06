package com.webflux.f1.webfluxf1api.fastestlap;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FastestLapRepository extends JpaRepository<FastestLap, String> {

  FastestLap findBySeasonAndRoundAndDriverId(Integer season, String round, String driverId);
}
