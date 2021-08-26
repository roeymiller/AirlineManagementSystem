package dev.roeymiller.ams.service;

import dev.roeymiller.ams.exception.ResourceNotFoundException;
import dev.roeymiller.ams.model.*;
import dev.roeymiller.ams.repository.AircraftRepository;
import dev.roeymiller.ams.repository.AirlineRepository;
import dev.roeymiller.ams.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;

@Service
@Transactional
public class AirlineServiceImpl implements AirlineService {

    @Autowired
    private AirlineRepository airlineRepositry;

    @Autowired
    private AircraftRepository aircraftRepository;

    @Autowired
    private AirlineService airlineService;

    @Autowired
    private DestinationRepository destinationRepository;

    EntityManager em;

    @Override
    public Airline createAirline(Airline airline) {
        return airlineRepositry.save(airline);
    }

    @Override
    public Airline updateAirline(Airline airline) {
        Optional<Airline> airlineDb = this.airlineRepositry.findById(airline.getId());
        if(airlineDb.isPresent()){
            Airline airlineUpdate=airlineDb.get();
            airlineUpdate.setId(airline.getId());
            airlineUpdate.setAircrafts(airline.getAircrafts());
            airlineUpdate.setName(airline.getName());
            airlineUpdate.setBudget(airline.getBudget());
            airlineUpdate.setHome_base_location(airline.getHome_base_location());
            return airlineUpdate;
        }
        else{
            throw new ResourceNotFoundException("Airline not found with id:"+airline.getId());
        }
    }
    @Override
    public List<Airline> getAllAirlines() {
        return this.airlineRepositry.findAll();
    }

    @Override
    public Aircraft addAircraftToAirline(Aircraft aircraft,long airline_id) {
        aircraft.setAirline_Id(airline_id);
        Optional<Airline> airline=this.airlineRepositry.findById(airline_id);
        if (airline.isPresent()){
            Airline airline1=airline.get();
            airline1.addAircraft(aircraft);
            airlineService.updateAirline(airline1);
        }
        return aircraftRepository.save(aircraft);
    }

    @Override
    public Aircraft sellAircraft(long airline_id, long aircraft_id) {
        Optional<Aircraft> aircraftDB=this.aircraftRepository.findById(aircraft_id);
        Optional<Airline> airlineDB=this.airlineRepositry.findById(airline_id);
        if(airlineDB.isPresent() && aircraftDB.isPresent()) {
            Airline airlineupdate=airlineDB.get();
            Aircraft aircraftUpdate=aircraftDB.get();
            String date_parser[]=aircraftUpdate.getCreated_at().toString().split("-",3);//2021-08-25
            String[] today = LocalDate.now().toString().split("-",3);//2017-01-23  - parser
            double monthsofuse= (Double.parseDouble(today[0])-Double.parseDouble(date_parser[0])*12)+(Double.parseDouble(today[1])-Double.parseDouble(date_parser[1]));
            airlineupdate.setBudget(airlineupdate.getBudget()+(aircraftUpdate.getprice()*(1-(monthsofuse)*0.02)));
            aircraftUpdate=removeAircraftFromAirline(aircraft_id,airlineupdate);
            return aircraftUpdate;
        }
        else{
             throw new ResourceNotFoundException("Aircraft" +aircraft_id+"was not found in Airline"+airline_id+"sell failed");
         }
    }

    @Override
    public List<Aircraft> getAllAircraftsbyAirlineId(long airline_id) {
        Optional<Airline> airlineDb=this.airlineRepositry.findById(airline_id);
        if (airlineDb.isPresent()){
            Airline airline=airlineDb.get();
            List<Aircraft> aircrafts=airline.getAircrafts();
            if(aircrafts!=null){
                return aircrafts;
            }
            else{
                throw new ResourceNotFoundException("Airline not found with id:"+airline_id);
            }
        }
        else{
            throw new ResourceNotFoundException("Airline not found with id:"+airline_id);
        }
    }

    @Override
    public Airline getAirlineById(long airlineId) {
        Optional<Airline> airlineDb = this.airlineRepositry.findById(airlineId);
        if(airlineDb.isPresent()){
           return airlineDb.get();
        }
        else{
            throw new ResourceNotFoundException("Airline not found with id:"+airlineId);
        }
    }

    @Override
    public void deleteAirline(long airlineId) {
        Optional<Airline> airlineDb = this.airlineRepositry.findById(airlineId);
        if(airlineDb.isPresent()){
            this.airlineRepositry.delete(airlineDb.get());
        }
        else{
            throw new ResourceNotFoundException("Airline not found with id:"+airlineId);
        }
    }

    @Override
    public Aircraft removeAircraftFromAirline(long aircraft_id,Airline airline) {
        int index=-1;
        Optional<Airline> airlineDb = this.airlineRepositry.findById(airline.getId());
        if(airlineDb.isPresent()){
            List<Aircraft> aircrafts=airlineDb.get().getAircrafts();
            for(int i=0;i<aircrafts.size();i++){
                Aircraft aircraft=aircrafts.get(i);
                if(aircraft.getId()==aircraft_id){
                    index=i;
                }
            }
            if(index!=-1)
                aircrafts.remove(index);
            Airline airline1=airlineDb.get();
            airline1.setAircrafts(aircrafts);
            Optional<Aircraft> aircraftDb = this.aircraftRepository.findById(aircraft_id);
            if(aircraftDb.isPresent()){
                Aircraft aircraft=aircraftDb.get();
                aircraft.setAirline_Id(0);
                return aircraft;
            }
            else{
                throw new ResourceNotFoundException("Airline not found with id:"+airline.getId());
            }

        }
        else{
            throw new ResourceNotFoundException("Airline not found with id:"+airline.getId());
        }
    }

    @Override
    public Aircraft buyAircraftFromAirline(long aircraft_id, long buyer_airline_id, long seller_airline_id) {
        Aircraft aircraft=sellAircraft(seller_airline_id,aircraft_id);
        Optional<Airline> airlineDB=this.airlineRepositry.findById(buyer_airline_id);
        if(airlineDB.isPresent()){
            Airline airline=airlineDB.get();
            String date_parser[]=aircraft.getCreated_at().toString().split("-",3);//2021-08-25
            String[] today = LocalDate.now().toString().split("-",3);//2017-01-23  - parser
            double monthsofuse= (Double.parseDouble(today[0])-Double.parseDouble(date_parser[0])*12)+(Double.parseDouble(today[1])-Double.parseDouble(date_parser[1]));
            airline.setBudget(airline.getBudget()-(aircraft.getprice()*(1-(monthsofuse)*0.02)));
            aircraft=airline.addAircraft(aircraft);
            return aircraft;
        }
        else{
            throw new ResourceNotFoundException("Aircraft" +aircraft_id+"was not found in Airline"+aircraft_id+"sell failed");
        }
    }

    @Override
    public List<Destination> getAllDestinations() {
        return this.destinationRepository.findAll();
    }

    @Override
    public List<Destination> getAllDestinationsandDistancebyAirlineId(long airline_id) {
        Optional<Airline> airlineDb=this.airlineRepositry.findById(airline_id);
        if (airlineDb.isPresent()){
            Airline airline=airlineDb.get();
            String location=airline.getHome_base_location();
            List<Destination> destinations=this.getAllDestinations();
            if(destinations!=null){
                for(int i=0;i<destinations.size();i++)
                    destinations.get(i).setDistance(airlineService.haversine(location,destinations.get(i).getLocation()));
                return destinations;
            }
            else{
                throw new ResourceNotFoundException("Airline not found with id:"+airline.getId());
            }
        }
        else{
            throw new ResourceNotFoundException("Airline not found with id:"+airline_id);
        }
    }

    @Override
    public double haversine(String location1, String location2) {
        double lat1 ,lon1,lat2,lon2;
        String[] location_parser =location1.split("/",2);
        lat1=Double.parseDouble(location_parser[0]);
        lon1=Double.parseDouble(location_parser[1]);
        location_parser=location2.split("/",2);
        lat2=Double.parseDouble(location_parser[0]);
        lon2=Double.parseDouble(location_parser[1]);

        // distance between latitudes and longitudes
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        // convert to radians
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // apply formulae
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(lat1) *
                        Math.cos(lat2);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        return rad * c;
    }

    @Override
    public List<Destination> getListOfAvailDests(long airline_id) {
        List<Aircraft> aircrafts = getAllAircraftsbyAirlineId(airline_id);
        List<Destination> destinationsList = getAllDestinationsandDistancebyAirlineId(airline_id);
        List<Destination> availDests=null;
        for(int i=0;i<aircrafts.size();i++){
            for(int j=0;j<destinationsList.size();j++){
                if(aircrafts.get(i).getMax_distance()<=destinationsList.get(j).getDistance()){
                    availDests.add(destinationsList.get(i));
                }
            }
        }
        return availDests;
    }
}
