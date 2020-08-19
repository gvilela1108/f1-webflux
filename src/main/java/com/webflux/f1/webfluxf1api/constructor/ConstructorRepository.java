package com.webflux.f1.webfluxf1api.constructor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConstructorRepository extends JpaRepository<Constructor, String> {

    Constructor findByConstructorId(String constructor_id);
}
