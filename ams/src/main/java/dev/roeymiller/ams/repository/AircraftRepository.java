package dev.roeymiller.ams.repository;

import dev.roeymiller.ams.model.Aircraft;
import dev.roeymiller.ams.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AircraftRepository extends JpaRepository<Aircraft,Long> {
}
