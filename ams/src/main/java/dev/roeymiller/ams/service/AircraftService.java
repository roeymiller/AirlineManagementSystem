package dev.roeymiller.ams.service;
import dev.roeymiller.ams.model.Aircraft;
import java.util.List;

public interface AircraftService {

    Aircraft createAircraft(Aircraft aircraft);

    Aircraft updateAircraft(Aircraft aircraft);

    List<Aircraft> getAllAircrafts();

    Aircraft getAirlineById(long aircraftId);

    void deleteAircraft(long id);
}
