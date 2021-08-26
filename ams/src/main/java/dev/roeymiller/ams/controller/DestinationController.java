package dev.roeymiller.ams.controller;

import dev.roeymiller.ams.model.Destination;
import dev.roeymiller.ams.service.DestinationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DestinationController {
    @Autowired
    private DestinationService destinationService;

    @GetMapping("/destinations")
    @ApiOperation(value="getAllDestinations",notes="Finds all Destinations",response = List.class)
    public ResponseEntity<List<Destination>> getAllDestinations(){
        return ResponseEntity.ok().body(destinationService.getAllDestination());
    }

    @GetMapping("/destinations/{id}")
    @ApiOperation(value="getDestinationsById",notes="Finds a spicific Destination by destination id",response = Destination.class)
    public ResponseEntity<Destination> getDestinationById(@ApiParam(value = "destination id",required = true) @PathVariable long id){
        return ResponseEntity.ok().body(destinationService.getDestinationById(id));
    }

    @PostMapping("/destinations")
    @ApiOperation(value="createDestination",notes="Creates a spicific Destination by name(String),location(String),distance(double,nullable),generates id automaticlly id",response = Destination.class)
    public ResponseEntity<Destination> createDestination(@ApiParam(value = "Destination by name(String),location(String),distance(double,nullable)",required = true)@RequestBody Destination destination){
        return ResponseEntity.ok().body(this.destinationService.createDestination(destination));
    }

    @PutMapping("/destinations/{id}")
    @ApiOperation(value="updateDestination",notes="updates a spicific Destination by name(String),location(String),distance(double,nullable)",response = Destination.class)
    public ResponseEntity<Destination> updateDestination(@ApiParam(value = "Destination id",required = true)@PathVariable long id,@ApiParam(value="name(String),location(String),distance(double,nullable)",required = true)@RequestBody Destination destination){
        destination.setId(id);
        return ResponseEntity.ok().body(this.destinationService.updateDestination(destination));
    }

//    @DeleteMapping("/destinations/{id}")
//    public ResponseEntity<?> deleteDestination(@PathVariable long id){
//        this.destinationService.deleteDestination(id);
//        return(ResponseEntity<?>) ResponseEntity.status(HttpStatus.OK);
//    }

    @DeleteMapping("/destinations/{id}")
    @ApiOperation(value="deleteDestination",notes="Deletes a spicific Destination by id",response = HttpStatus.class)
    public HttpStatus deleteDestination(@ApiParam(value = "Destination id")@PathVariable long id){
        this.destinationService.deleteDestination(id);
        return HttpStatus.OK;
    }
}
