package com.example._Buzila_Andra_Court_Reserve_Backend.services;

import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Location;
import com.example._Buzila_Andra_Court_Reserve_Backend.repositories.LocationRepository;
import com.example._Buzila_Andra_Court_Reserve_Backend.repositories.TariffRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class LocationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TariffService.class);
    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location findEntityLocationById(UUID id) {
        Optional<Location> locationOptional = locationRepository.findById(id);
        if (!locationOptional.isPresent()) {
            LOGGER.error("Location with id {} was not found in db", id);
            throw new ResourceNotFoundException(Location.class.getSimpleName() + " with id: " + id);
        }
        return locationOptional.get();
    }
}
