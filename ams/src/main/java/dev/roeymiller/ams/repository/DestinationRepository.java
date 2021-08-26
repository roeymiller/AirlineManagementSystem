package dev.roeymiller.ams.repository;

import dev.roeymiller.ams.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepository extends JpaRepository<Destination,Long> {
}
