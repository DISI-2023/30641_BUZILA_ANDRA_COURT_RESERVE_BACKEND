package com.example._Buzila_Andra_Court_Reserve_Backend.services;

import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.AddCourtDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.builders.CourtBuilder;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Court;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Location;
import com.example._Buzila_Andra_Court_Reserve_Backend.repositories.CourtRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CourtService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CourtService.class);
    private final CourtRepository courtRepository;

    //Constructor:
    @Autowired
    public CourtService(CourtRepository courtRepository) {
        this.courtRepository = courtRepository;
    }

    //Insert court:
    public UUID insert(AddCourtDTO addCourtDTO, Location location)
    {
        //Din DTO in Entity;
        Court court = CourtBuilder.toCourtEntityAdd(addCourtDTO, location);
        UUID courtId = court.getId(); //Id;

        //Save object with repository; Return object;
        court = courtRepository.save(court);

        //Court inserted, return id;
        LOGGER.debug("Court with id {} was inserted in the db!", court.getId());
        return court.getId();
    }

    //Find enity court by id:
    public Court findEntityCourtById(UUID id)
    {
        //Find in DB:
        Optional<Court> courtOptional = courtRepository.findById(id);

        //If present, log, if not, throw;
        if (!courtOptional.isPresent()) {
            LOGGER.error("Court with id {} was not found in the db!", id);

            throw new ResourceNotFoundException(Court.class.getSimpleName()
                    + " with id: " + id + " was not found!");
        }

        //Return entity court:
        return courtOptional.get();
    }

    //Find entity courts by location id:
    public List<Court> findCourtsByLocationId(UUID id)
    {
        //Test:
        System.out.println("Location id: " + id + " ;");

        //Find courts by location:
        List<Court> locationCourts = courtRepository.findAllCourtsAtLocation(id);

        //Daca nu sunt courts:
        if(locationCourts.isEmpty()){
            LOGGER.error("The location with id {} does not have any courts in the db!", id);
        }

        return locationCourts;
    }
}
