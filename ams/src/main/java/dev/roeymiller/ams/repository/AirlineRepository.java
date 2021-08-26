package dev.roeymiller.ams.repository;

import dev.roeymiller.ams.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AirlineRepository extends JpaRepository<Airline,Long> { }