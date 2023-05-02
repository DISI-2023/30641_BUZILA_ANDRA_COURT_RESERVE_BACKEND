package com.example._Buzila_Andra_Court_Reserve_Backend.services;

import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.AddCourtDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.CourtDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.GetAllCourtsFromLocationDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.CourtDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.builders.CourtBuilder;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Court;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Location;
import com.example._Buzila_Andra_Court_Reserve_Backend.repositories.CourtRepository;
import com.example._Buzila_Andra_Court_Reserve_Backend.repositories.LocationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourtService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CourtService.class);
    private final CourtRepository courtRepository;
    private final LocationRepository locationRepository;

    //Constructor:
    @Autowired
    public CourtService(CourtRepository courtRepository, LocationRepository locationRepository) {
        this.courtRepository = courtRepository;
        this.locationRepository = locationRepository;
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

    //Find all courts: Include the ones that are empty, care nu au courts:
    public List<GetAllCourtsFromLocationDTO> findAllCourts() {
        //Get courts from repo:
        List<Court> allCourts = courtRepository.findAll();

        //Get locations from repo:
        List<Location> allLocations = locationRepository.findAll();

        //Convert:
        //Get courts list:
        List<GetAllCourtsFromLocationDTO> allCourtsNew = new ArrayList<>();

        //Pe rand:
        for(Court court: allCourts)
        {
            //Find location for court:
            //Find in DB:
            Optional<Location> locationOptional = locationRepository.findById(court.getLocation().getId());

            //If present, log, if not, throw;
            if (!locationOptional.isPresent()) {
                LOGGER.error("Location with id {} was not found in the db!", court.getLocation().getId());

                throw new ResourceNotFoundException(Location.class.getSimpleName()
                        + " with id: " + court.getLocation().getId() + " was not found!");
            }

            //Generate new DTO:
            GetAllCourtsFromLocationDTO newCourtDTO = new GetAllCourtsFromLocationDTO(
                    court.getId(),
                    court.getType(), court.getName(), locationOptional.get().getId(),
                    locationOptional.get().getAddress(), locationOptional.get().getLongitude(),
                    locationOptional.get().getLatitude(),
                    locationOptional.get().getCourtsImage()
            );

            allCourtsNew.add(newCourtDTO);
        }

        //Add locations that do not have courts: La final:
        for(Location location: allLocations)
        {
            //Noua locatie:
            int newLocation = 0;

            //Daca gasesc o locatie ce nu apare in lista deja creata, o adaug cu null la courts:
            for(GetAllCourtsFromLocationDTO court: allCourtsNew)
            {
                //Daca sunt egale:
                if(Objects.equals(location.getId(), court.getLocation_id()))
                {
                    //Nu este noua locatie:
                    newLocation = 1;

                    break;
                }
            }

            //Generate new DTO if new location:
            if(newLocation == 0)
            {
                GetAllCourtsFromLocationDTO newCourtDTO = new GetAllCourtsFromLocationDTO(
                        null, null, null, location.getId(),
                        location.getAddress(), location.getLongitude(),
                        location.getLatitude(),
                        location.getCourtsImage()
                );

                allCourtsNew.add(newCourtDTO);
            }
        }

        return allCourtsNew;

//        return allCourts.stream()
//                .map(CourtBuilder::toCourtDTO)
//                .collect(Collectors.toList());
    }

    //Find entity courts by location id:
    public List<CourtDTO> findCourtsByLocationIdDTO(UUID id)
    {
        //Test:
        System.out.println("Location id: " + id + " ;");

        //Find courts by location:
        List<Court> locationCourts = courtRepository.findAllCourtsAtLocation(id);

        //Daca nu sunt courts:
        if(locationCourts.isEmpty())
        {
            //Old:
            //LOGGER.error("The location with id {} does not have any courts in the db!", id);

            //Adaug datele location-ului si atat:

            //Find location:
            Optional<Location> locationOptional = locationRepository.findById(id);

            //If present, log, if not, throw;
            if (!locationOptional.isPresent()) {
                LOGGER.error("Location with id {} was not found in the db!", id);

                throw new ResourceNotFoundException(Location.class.getSimpleName()
                        + " with id: " + id + " was not found!");
            }

            //If present, add it to list:
            List<CourtDTO> locationCourtsDTO = new ArrayList<>();

            locationCourtsDTO.add(new CourtDTO(
                    null, "", "")
            );

            //Return list with 1 element:
            return locationCourtsDTO;
        }

        //Else we have courts:
        //Convert to DTO:
        List<CourtDTO> locationCourtsDTO = new ArrayList<>();
        for(Court court: locationCourts)
        {
            locationCourtsDTO.add(CourtBuilder.toCourtDTO(court));
        }

        //Return DTOs;
        return locationCourtsDTO;
    }

    public UUID delete(UUID courtId) {
        courtRepository.deleteById(courtId);
        LOGGER.debug("Court with id {} was deleted from db", courtId);
        return courtId;
    }

    public UUID update(Court court) {
        //User user = UserBuilder.toUserEntityUpdate(userDTO);
        //System.out.println(user.getId());
        Court courtUpdated = courtRepository.save(court);
        LOGGER.debug("Court with id {} was updated in db", courtUpdated.getId());
        return courtUpdated.getId();
    }
}
