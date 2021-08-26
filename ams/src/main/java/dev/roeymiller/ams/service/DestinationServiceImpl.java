package dev.roeymiller.ams.service;

import dev.roeymiller.ams.exception.ResourceNotFoundException;
import dev.roeymiller.ams.model.Destination;
import dev.roeymiller.ams.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class DestinationServiceImpl implements DestinationService {
    @Autowired
    private DestinationRepository destinationRepository;
    @Override
    public Destination createDestination(Destination destination) {
        return destinationRepository.save(destination);
    }

    @Override
    public Destination updateDestination(Destination destination) {
        Optional<Destination> destinationDb = this.destinationRepository.findById(destination.getId());
        if(destinationDb.isPresent()){
            Destination destinationUpdate=destinationDb.get();
            destinationUpdate.setId(destination.getId());
            destinationUpdate.setLocation(destination.getLocation());
            destinationUpdate.setName(destination.getName());
            return destinationUpdate;
        }
        else{
            throw new ResourceNotFoundException("Destination not found with id:"+destination.getId());
        }
    }

    @Override
    public List<Destination> getAllDestination() {
        return destinationRepository.findAll();
    }

    @Override
    public Destination getDestinationById(long destinationId) {
        Optional<Destination> destinationDb = this.destinationRepository.findById(destinationId);
        if(destinationDb.isPresent()){
            return destinationDb.get();
        }
        else{
            throw new ResourceNotFoundException("Destination not found with id:"+destinationId);
        }
    }

    @Override
    public void deleteDestination(long destinationId) {
        Optional<Destination> destinationDb = this.destinationRepository.findById(destinationId);
        if(destinationDb.isPresent()){
            this.destinationRepository.delete(destinationDb.get());
        }
        else{
            throw new ResourceNotFoundException("Destination not found with id:"+destinationId);
        }

    }
}
