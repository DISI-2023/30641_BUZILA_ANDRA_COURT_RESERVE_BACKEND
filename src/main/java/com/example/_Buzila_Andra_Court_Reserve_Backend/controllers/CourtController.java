package com.example._Buzila_Andra_Court_Reserve_Backend.controllers;

import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.*;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.builders.CourtBuilder;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Court;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.AddCourtDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.DeleteCourtDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.UpdateCourtDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Court;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Location;
import com.example._Buzila_Andra_Court_Reserve_Backend.services.CourtService;
import com.example._Buzila_Andra_Court_Reserve_Backend.services.LocationService;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.Month;
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
    public ResponseEntity<List<GetAllCourtsFromLocationDTO>> getAvailableCourts(@PathVariable("id") UUID locationId)
    {
        //Find location by id, id is correct:
        Location location = locationService.findEntityLocationById(locationId);

        //Find courts that belong to that location:
        List<CourtDTO> courtsFromLocation = courtService.findCourtsByLocationIdDTO(location.getId());

        //New DTO for court + location fields (and id)
        List<GetAllCourtsFromLocationDTO> courtsFromLocationNew = new ArrayList<>();

        //Convert:
        for(CourtDTO courtDTO: courtsFromLocation)
        {
            //Generate new DTO:
            GetAllCourtsFromLocationDTO newCourtDTO = new GetAllCourtsFromLocationDTO(
                    courtDTO.getType(), courtDTO.getName(), location.getId(),
                    location.getAddress(), location.getLongitude(), location.getLatitude(),
                    location.getCourtsImage()
            );
            courtsFromLocationNew.add(newCourtDTO);
        }

        //Return all the courts from location:
        return new ResponseEntity<>(courtsFromLocationNew, HttpStatus.OK);
    }

    //Search for courts:
    //Receive LocationAndDateDTO, Send AvailableCourtsDTO + ok;
    @PostMapping(value = "/searchForCourts")
    public ResponseEntity<AvailableCourtsDTO> searchForCourts
            (@Valid
             @RequestBody
             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
             LocationAndDateDTO locationAndDateDTO)
    {
        //Find location by id:
        Location location = locationService.findEntityLocationById(locationAndDateDTO.getLocationId());

        //Courts list in functie de locatie si de data:
        //First filtrare dupa locatie:
        List<Court> courtsList = courtService.findCourtsByLocationId(location.getId());

        //An, Month, Day, Hour;
        /*
        //1)
        LocalDateTime year;
        LocalDateTime month;
        LocalDateTime day;
        LocalDateTime hour;
         */

        //2)
        int year;
        Month month;
        int day;
        int hour;

        //if date null => folosesc data now;
        if(locationAndDateDTO == null)
        {
            //2)
            year = LocalDateTime.now().getYear();
            month = LocalDateTime.now().getMonth();
            day = LocalDateTime.now().getDayOfMonth();
            hour = LocalDateTime.now().getHour();

            /*
            //1)
            //Anul in care vrem sa fie available:
            year = LocalDateTime.now().truncatedTo(ChronoUnit.YEARS);
            //Luna in care vrem sa fie available:
            month = LocalDateTime.now().truncatedTo(ChronoUnit.MONTHS);
            //Ziua in care vrem sa fie available:
            day = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
            //Ora in care vrem sa fie available:
            hour = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS);
            */
        }
        //Altfel folosesc data data:
        else {
            //2)
            year = locationAndDateDTO.getDateForCourts().getYear();
            month = locationAndDateDTO.getDateForCourts().getMonth();
            day = locationAndDateDTO.getDateForCourts().getDayOfMonth();
            hour = locationAndDateDTO.getDateForCourts().getHour();

            /*
            //1)
            //Anul in care vrem sa fie available:
            year = locationAndDateDTO.getDateForCourts().truncatedTo(ChronoUnit.YEARS);
            //Luna in care vrem sa fie available:
            month = locationAndDateDTO.getDateForCourts().truncatedTo(ChronoUnit.MONTHS);
            //Ziua in care vrem sa fie available:
            day = locationAndDateDTO.getDateForCourts().truncatedTo(ChronoUnit.DAYS);
            //Ora in care vrem sa fie available:
            hour = locationAndDateDTO.getDateForCourts().truncatedTo(ChronoUnit.HOURS);
            */
        }

        //ToDo:
        //Dupa filtrare dupa data: (Folosind controllerul de reservation)
        //if year diff => good;
        //if month diff => good;
        //if day diff => good;
        //if hour diff => good;
        //else daca toate 4 sunt egale, atunci inseamna ca acel court nu este disponibil, nu se adauga;


        //Test 1:
//        System.out.println(
//                "\n1) Data: " + locationAndDateDTO.getDateForCourts() +
//                "\n2) Court 1: " + courtsList.get(0).getName() +
//                "\n3) Court 2: " + courtsList.get(1).getName() +
//                "\n4) Year: " + year +
//                "\n5) Month: " + month);

        //Return courts list in DTO (after creating list of courts + DTO):
        //Create json list: din 3 in 3 sunt courturile:
        JSONArray sendCourtsList = new JSONArray();
        for(Court court: courtsList)
        {
            //Adaug fiecare element in lista noua: Din Entity in DTO:
            //sendCourtsList.add(CourtBuilder.toCourtDTO(court.));

            //Adaug element cu element din fiecare court:
            sendCourtsList.add(court.getId());
            sendCourtsList.add(court.getType());
            sendCourtsList.add(court.getName());
//            sendCourtsList.add(court.getLocation());
        }

        //Create DTO:
        AvailableCourtsDTO availableCourtsDTO = new AvailableCourtsDTO(location.getId(),
                location.getAddress(), location.getLongitude(), location.getLatitude(),
                sendCourtsList);

        //Test 2:
//        System.out.println(
//                "\n1) LocationId: " + location.getId() + availableCourtsDTO.getLocationId() +
//                "\n2) LocationAddress: " + location.getAddress() + availableCourtsDTO.getLocationAddress() +
//                "\n3) LocationLong: " + location.getLongitude() + availableCourtsDTO.getLocationLongitude() +
//                "\n4) LocationLat: " + location.getLatitude() + availableCourtsDTO.getLocationLatitude() +
//                "\n5) LocationCourts: " + availableCourtsDTO.getAvailableCourts().get(1).getName());

        return new ResponseEntity<>(availableCourtsDTO, HttpStatus.OK);
    }

    @PostMapping(value="/deleteCourt")
    public ResponseEntity<UUID> deleteCourt(@Valid @RequestBody DeleteCourtDTO court) {
        UUID deletedCourtID = courtService.delete(court.getId());
        return new ResponseEntity<>(deletedCourtID, HttpStatus.OK);
    }

    @PostMapping(value="/updateCourt")
    public ResponseEntity<UUID> updateCourt(@Valid @RequestBody UpdateCourtDTO updateCourtDTO) {

        Court court = courtService.findEntityCourtById(updateCourtDTO.getId());
        court.setName(updateCourtDTO.getName());
        court.setType(updateCourtDTO.getType());

        UUID courtID = courtService.update(court);
        return new ResponseEntity<>(courtID, HttpStatus.OK);
    }
}
