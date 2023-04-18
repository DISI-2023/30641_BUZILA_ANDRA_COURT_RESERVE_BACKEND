package com.example._Buzila_Andra_Court_Reserve_Backend.controllers;

import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.AddCourtDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.CourtDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Location;
import com.example._Buzila_Andra_Court_Reserve_Backend.services.CourtService;
import com.example._Buzila_Andra_Court_Reserve_Backend.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/court")
public class CourtController
{
    private final CourtService courtService;
    private final LocationService locationService;

    //Constructor:
    @Autowired
    public CourtController(CourtService courtService, LocationService locationService)
    {
        this.courtService = courtService;
        this.locationService = locationService;
    }

    //Add Court:
    //Receive AddCourtDTO, Send Ok + id;
    @PostMapping(value = "/addCourt")
    public ResponseEntity<UUID> addCourt(@Valid @RequestBody AddCourtDTO addCourtDTO)
    {
        //Find location by id:
        Location location = locationService.findEntityLocationById(addCourtDTO.getLocation_id());

        //UUID returnat de la insert: Add the location here:
        UUID addCourtId = courtService.insert(addCourtDTO, location);

        //Return ID if corect:
        return new ResponseEntity<UUID>(addCourtId, HttpStatus.OK);
    }

    //Get courts from 1 location:
    //Receive id of location, Send all courts from location + Ok;
    @GetMapping(value = "/getAvailableCourts" + "/{id}")
    public ResponseEntity<List<CourtDTO>> getUser(@PathVariable("id") UUID locationId)
    {
        //Find location by id, id is correct:
        Location location = locationService.findEntityLocationById(locationId);

        //Find courts that belong to that location:
        List<CourtDTO> courtsFromLocation = courtService.findCourtsByLocationIdDTO(location.getId());

        //Return all the courts from location:
        return new ResponseEntity<>(courtsFromLocation, HttpStatus.OK);
    }
}
