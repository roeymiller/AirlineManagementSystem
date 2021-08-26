package dev.roeymiller.ams.service;

import dev.roeymiller.ams.model.Aircraft;
import dev.roeymiller.ams.model.Airline;
import dev.roeymiller.ams.model.Destination;

import java.util.List;


public interface AirlineService {
    Airline createAirline(Airline airline);

    Airline updateAirline(Airline airline);

    List<Airline> getAllAirlines();

    Aircraft addAircraftToAirline(Aircraft aircraft,long airline_id);

    Aircraft sellAircraft(long airline_id,long aircraft_id);

    List<Aircraft> getAllAircraftsbyAirlineId(long airline_id);

    Airline getAirlineById(long airlineId);

    void deleteAirline(long id);

    Aircraft removeAircraftFromAirline(long aircraft_id,Airline airline);

    Aircraft buyAircraftFromAirline(long aircraft_id,long buyer_airline_id,long seller_airline_id);

    List<Destination> getAllDestinations();

    List<Destination> getAllDestinationsandDistancebyAirlineId(long airline_id);

    double haversine(String location1,String location2);

    List<Destination> getListOfAvailDests(long airline_id);
}
