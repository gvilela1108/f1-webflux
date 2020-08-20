package com.webflux.f1.webfluxf1api.circuit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CircuitRepository extends JpaRepository<Circuit, String> {

  Circuit findByCircuitId(String circuitId);
}
