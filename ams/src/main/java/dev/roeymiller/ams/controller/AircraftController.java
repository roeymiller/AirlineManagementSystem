package dev.roeymiller.ams.controller;

import dev.roeymiller.ams.model.Aircraft;
import dev.roeymiller.ams.service.AircraftService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AircraftController {
    @Autowired
    private AircraftService aircraftService;

    @GetMapping("/aircrafts")
    @ApiOperation(value="getAllAircrafts",notes="Finds all aircrafts from airrcrafts table",response = List.class)
    public ResponseEntity<List<Aircraft>> getAllAircrafts(){
        return ResponseEntity.ok().body(aircraftService.getAllAircrafts());
    }


    @GetMapping("/aircrafts/{id}")
    @ApiOperation(value="getAircraftById",notes="Finds a specific aircraft by given an aircraft id",response = Aircraft.class)
    public ResponseEntity<Aircraft> getAircraftById(@ApiParam(value = "aircraft id",required = true) @PathVariable long id){
        return ResponseEntity.ok().body(this.aircraftService.getAirlineById(id));
    }

    @PostMapping("/aircrafts")
    @ApiOperation(value = "createAircraft",notes = "Create an Aircraft in aircrafts table",response = Aircraft.class)
    public ResponseEntity<Aircraft> createAircraft(@ApiParam(value = "Aircraft:max_distance(double),price(double),created_at(String),airline_id(nullable)",required = true)@RequestBody Aircraft aircraft){
        return ResponseEntity.ok().body(this.aircraftService.createAircraft(aircraft));
    }
    @PutMapping("/aircrafts/{id}")
    @ApiOperation(value = "updateAircraft",notes = "Create an Aircraft in aircrafts table",response = Aircraft.class)
    public ResponseEntity<Aircraft> updateAircraft(@ApiParam(value = "aircraft id",required = true)@PathVariable long id,@ApiParam(value = "Aircraft:max_distance(double),price(double),created_at(String),airline_id(nullable)",required = true)@RequestBody Aircraft aircraft){
        aircraft.setId(id);
        return ResponseEntity.ok().body(this.aircraftService.updateAircraft(aircraft));
    }

//    @DeleteMapping("/aircrafts/{id}")
//    public ResponseEntity<?> deleteAircraft(@PathVariable long id){
//        this.aircraftService.deleteAircraft(id);
//        return (ResponseEntity<?>)ResponseEntity.status(HttpStatus.OK);
//    }

    @DeleteMapping("/aircrafts/{id}")
    @ApiOperation(value="deleteAircraftbyId",notes = "Delete an Aircraft by Aircraft Id",response = HttpStatus.class)
    public HttpStatus deleteAircraft(@ApiParam(value = "aircraft id",required = true)@PathVariable long id){
        this.aircraftService.deleteAircraft(id);
        return HttpStatus.OK;
    }
}
