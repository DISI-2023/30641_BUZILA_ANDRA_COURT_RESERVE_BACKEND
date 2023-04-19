package com.example._Buzila_Andra_Court_Reserve_Backend.services;

import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.AddLocationDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.builders.LocationBuilder;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Location;
import com.example._Buzila_Andra_Court_Reserve_Backend.repositories.LocationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class LocationService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(LocationService.class);
    private final LocationRepository locationRepository;

    //Constructor:
    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    //Insert location:
    public UUID insert(AddLocationDTO addLocationDTO)
    {
        //Din DTO in Eintity;
        Location location = LocationBuilder.toLocationEntityAdd(addLocationDTO);
        UUID locationId = location.getId(); //Id;

        //Save object with repository; Return object;
        location = locationRepository.save(location);

        //Location inserted, return id;
        LOGGER.debug("Location with id {} was inserted in the db!", location.getId());
        return location.getId();
    }

    //Find enity location by id:
    public Location findEntityLocationById(UUID id)
    {
        //Find in DB:
        Optional<Location> locationOptional = locationRepository.findById(id);

        //If present, log, if not, throw;
        if (!locationOptional.isPresent()) {
            LOGGER.error("Location with id {} was not found in the db!", id);

            throw new ResourceNotFoundException(Location.class.getSimpleName()
                    + " with id: " + id + " was not found!");
        }

        //Return entity location:
        return locationOptional.get();
    }
}
