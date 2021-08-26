package dev.roeymiller.ams.service;
import dev.roeymiller.ams.model.Destination;
import java.util.List;

public interface DestinationService {
    Destination createDestination(Destination destination);

    Destination updateDestination(Destination destination);

    List<Destination> getAllDestination();

    Destination getDestinationById(long destinationID);

    void deleteDestination(long id);
}
