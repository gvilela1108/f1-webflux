package com.webflux.f1.webfluxf1api.race;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
public interface RaceRepository extends JpaRepository<Race, String> {

    Race findBySeasonAndRound(Integer season, String round);

    ArrayList<Race> findRacesBySeason(Integer season);
}
