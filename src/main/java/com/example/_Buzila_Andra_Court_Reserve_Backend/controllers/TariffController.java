package com.example._Buzila_Andra_Court_Reserve_Backend.controllers;

import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.AddTariffDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Location;
import com.example._Buzila_Andra_Court_Reserve_Backend.services.LocationService;
import com.example._Buzila_Andra_Court_Reserve_Backend.services.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/tariff")
public class TariffController {

    private final TariffService tariffService;
    private final LocationService locationService;

    @Autowired
    public TariffController(TariffService tariffService, LocationService locationService) {
        this.tariffService = tariffService;
        this.locationService = locationService;
    }

    @PostMapping()
    public ResponseEntity<UUID> insertTariff(@Valid @RequestBody AddTariffDTO addTariffDTO) {
        Location location = locationService.findEntityLocationById(addTariffDTO.getLocation_id());
        UUID tariffID = tariffService.insert(addTariffDTO, location);
        return new ResponseEntity<>(tariffID, HttpStatus.OK);
    }
}
