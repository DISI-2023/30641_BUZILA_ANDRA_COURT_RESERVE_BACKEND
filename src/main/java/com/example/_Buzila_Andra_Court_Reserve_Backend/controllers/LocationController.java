package com.example._Buzila_Andra_Court_Reserve_Backend.controllers;

import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.AddLocationDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/location")
public class LocationController
{
    private final LocationService locationService;

    //Constructor:
    @Autowired
    public LocationController(LocationService locationService)
    {
        this.locationService = locationService;
    }

    //Add Location:
    //Receive AddLocationDTO, Send Ok + id;
    @PostMapping(value = "/addLocation")
    public ResponseEntity<UUID> addLocation(@Valid @RequestBody AddLocationDTO addLocationDTO)
    {
        //UUID returnat de la insert:
        UUID addLocationId = locationService.insert(addLocationDTO);

        //Return ID if corect:
        return new ResponseEntity<UUID>(addLocationId, HttpStatus.OK);
    }
}
