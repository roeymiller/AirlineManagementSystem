package dev.roeymiller.ams.service;

import dev.roeymiller.ams.exception.ResourceNotFoundException;
import dev.roeymiller.ams.model.Aircraft;
import dev.roeymiller.ams.repository.AircraftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AircraftServiceImpl implements AircraftService{
    @Autowired
    private AircraftRepository aircraftRepository;
    @Override
    public Aircraft createAircraft(Aircraft aircraft) {
        return aircraftRepository.save(aircraft);
    }

    @Override
    public Aircraft updateAircraft(Aircraft aircraft) {
        Optional<Aircraft> aircraftDb=this.aircraftRepository.findById(aircraft.getId());
        if(aircraftDb.isPresent()){
            Aircraft aircraftUpdate=aircraftDb.get();
            aircraftUpdate.setId(aircraft.getId());
            aircraftUpdate.setprice(aircraft.getprice());
            aircraftUpdate.setMax_distance(aircraft.getMax_distance());
            aircraftUpdate.setCreated_at(aircraft.getCreated_at());
            aircraftUpdate.setAirline_Id(aircraft.getAirline_Id());
            return aircraftUpdate;
        }
        else{
            throw new ResourceNotFoundException("Airline not found with id:"+aircraft.getId());
        }

    }


    @Override
    public List<Aircraft> getAllAircrafts() {
        return aircraftRepository.findAll();
    }

    @Override
    public Aircraft getAirlineById(long aircraftId) {
        Optional<Aircraft> aircraftDb = this.aircraftRepository.findById(aircraftId);
        if (aircraftDb.isPresent()){
            return aircraftDb.get();
        }
        else{
            throw new ResourceNotFoundException("Aircraft not found with id:"+aircraftId);
        }
    }

    @Override
    public void deleteAircraft(long aircraftId) {
        Optional<Aircraft> aircraftDb = this.aircraftRepository.findById(aircraftId);
        if (aircraftDb.isPresent()){
            this.aircraftRepository.delete(aircraftDb.get());
        }
        else{
            throw new ResourceNotFoundException("Aircraft not found with id:"+aircraftId);
        }

    }
}
