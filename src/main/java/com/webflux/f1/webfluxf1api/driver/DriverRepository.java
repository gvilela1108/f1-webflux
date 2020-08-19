package com.webflux.f1.webfluxf1api.driver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DriverRepository extends JpaRepository<Driver, String> {

    Driver findByDriverId(String driverId);
}
