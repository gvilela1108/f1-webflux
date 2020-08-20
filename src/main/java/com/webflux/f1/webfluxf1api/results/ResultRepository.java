package com.webflux.f1.webfluxf1api.results;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ResultRepository extends JpaRepository<Result, String> {
  ArrayList<Result> findBySeasonAndRound(Integer season, String round);
}
