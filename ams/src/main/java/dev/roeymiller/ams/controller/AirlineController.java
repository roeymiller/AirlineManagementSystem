package dev.roeymiller.ams.controller;

import dev.roeymiller.ams.model.Aircraft;
import dev.roeymiller.ams.model.Airline;

import dev.roeymiller.ams.model.Destination;
import dev.roeymiller.ams.service.AirlineService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AirlineController {

    @Autowired
    private AirlineService airlineService;

    @GetMapping("airlines/{airline_id}/aircrafts")
    @ApiOperation(value="getAllAircraftsByAirlineId",notes="Finds all aircrafts of a specific airline by given an airline id",response = List.class)
    public ResponseEntity<List<Aircraft>> getAllAircraftsbyAirlineId(@ApiParam(value = "airline id",required = true)@PathVariable  long airline_id){
        return ResponseEntity.ok().body(airlineService.getAllAircraftsbyAirlineId(airline_id));
    }

    @GetMapping("/airlines")
    @ApiOperation(value="getAllAirlines",notes="Finds all airlines",response = List.class)
    public ResponseEntity<List<Airline>> getAllAirlines(){
        return ResponseEntity.ok().body(airlineService.getAllAirlines());
    }

    @PostMapping("/airlines/{id}/aircraft")
    @ApiOperation(value="addAircraftToAirline",notes="Adds an Aircraft to an Airline by given airline id and Aircraft",response = Aircraft.class)
    public ResponseEntity<Aircraft> addAircraftToAirline(@ApiParam(value = "airline id",required = true)@PathVariable long id,@ApiParam(value = "Aircraft-(double)max_distance,price,(String)created_at-Example:2021-08-25,airline_id(nullable),generates id automaticly",required = true)@RequestBody Aircraft aircraft){
        return ResponseEntity.ok(airlineService.addAircraftToAirline(aircraft,id));

    }

    @PostMapping("/airlines/{airline_id}/sell/{aircraft_id}")
//    @ApiOperation(value="sellAircraft",notes="Airline can sell an aircraft by given airline id and aircraft id",response = Aircraft.class)
//    public ResponseEntity<Aircraft> sellAircraft(@ApiParam(value = "airline id",required = true)@PathVariable long airline_id,@ApiParam(value = "aircraft id",required = true)@PathVariable long aircraft_id){
    public ResponseEntity<Aircraft> sellAircraft(@PathVariable long airline_id,@PathVariable long aircraft_id){
        return ResponseEntity.ok(airlineService.sellAircraft(airline_id,aircraft_id));
    }
    @PostMapping("/airlines/{airline_buyer_id}/buy/{airline_seller_id}/aircraft/{aircraft_id}")
//    @ApiOperation(value = "buyAircraft",notes = "Airline can buy aircraft from another airline by given aircraft id,buyer airline id,seller airline id",response = Aircraft.class)
//    public ResponseEntity<Aircraft> buyAircraft(@ApiParam(value = "buyer_airline_id",required = true)@PathVariable long airline_buyer_id,@ApiParam(value = "seller airline id",required = true)@PathVariable long airline_seller_id,@ApiParam(value = "aircraft id",required = true)@PathVariable long aircraft_id){
    public ResponseEntity<Aircraft> buyAircraft(@ApiParam(value = "buyer_airline_id",required = true)@PathVariable long airline_buyer_id,@PathVariable long airline_seller_id,@PathVariable long aircraft_id){
        return ResponseEntity.ok().body(airlineService.buyAircraftFromAirline(aircraft_id,airline_buyer_id,airline_seller_id));
    }

    @GetMapping("/airlines/{id}")
    @ApiOperation(value="getAirlineById",notes = "Finds Airlines by id",response = Airline.class)
    public ResponseEntity<Airline> getAirlineById(@ApiParam(value="ID value for the Airline you want to retrieve",required = true)
            @PathVariable long id){
        return ResponseEntity.ok().body(airlineService.getAirlineById(id));
    }

    @PostMapping("/airlines")
    @ApiOperation(value="createAirline",notes="Create an airline with given parameters of (String)name, (String)location-Example-lat,long 63.735854/116.279111 and (double)budget,generates an id automatically",response = Airline.class)
    public ResponseEntity<Airline> createAirline(@ApiParam(value="name,location,budget,id",required = true)@RequestBody Airline airline){
        return ResponseEntity.ok().body(this.airlineService.createAirline(airline));
    }

    @PutMapping("/airlines/{id}")
    @ApiOperation(value="updateAirline",notes="update Airline fields",response=Airline.class)
    public ResponseEntity<Airline> updateAirline(@ApiParam(value="airline id",required = true)@PathVariable long id,@ApiParam(value="name,location,budget",required = true)@RequestBody Airline airline){
        airline.setId(id);
        return ResponseEntity.ok().body(this.airlineService.updateAirline(airline));

    }
//    @DeleteMapping("/airlines/{id}")
//    public ResponseEntity<?> deleteAirline(@PathVariable long id){
//        this.airlineService.deleteAirline(id);
//        return (ResponseEntity<?>)ResponseEntity.status(HttpStatus.OK);
//
//    }
    @DeleteMapping("/airlines/{id}")
    @ApiOperation(value="deleteAirlineById",notes = "delete an Airline from airlines table by given id",response = HttpStatus.class)
    public HttpStatus deleteAirline(@ApiParam(value="airline id",required = true)@PathVariable long id){
        this.airlineService.deleteAirline(id);
        return HttpStatus.OK;

    }

    @PostMapping("/airlines/{id}/distances")
    @ApiOperation(value="getDistanceFromAllByAirlineId",notes = "Calculate the distance from each destination in the system from a specific airline",response = List.class)
    public ResponseEntity<List<Destination>> getDistancebyAirlineId(@ApiParam(value="airline id",required = true)@PathVariable long id){
        return ResponseEntity.ok().body(airlineService.getAllDestinationsandDistancebyAirlineId(id));
    }

    @PostMapping("airlines/{id}/availableDests")
    @ApiOperation(value="getListofAvailableDestinationsbyAirlineId",notes="Get list of available destinations by Airline id calculate the distance from each one and then return only the availables",response = List.class)
    public ResponseEntity<List<Destination>> getListofAvailDests(@ApiParam(value = "airline id",required = true)@PathVariable long id){
        return ResponseEntity.ok().body(airlineService.getAllDestinationsandDistancebyAirlineId(id));
    }

}
