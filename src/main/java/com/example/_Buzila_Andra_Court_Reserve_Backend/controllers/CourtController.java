package com.example._Buzila_Andra_Court_Reserve_Backend.controllers;

import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.*;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.builders.CourtBuilder;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Court;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.AddCourtDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.DeleteCourtDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.UpdateCourtDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Court;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Location;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Reservation;
import com.example._Buzila_Andra_Court_Reserve_Backend.services.CourtService;
import com.example._Buzila_Andra_Court_Reserve_Backend.services.LocationService;
import com.example._Buzila_Andra_Court_Reserve_Backend.services.ReservationService;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.*;
import java.util.List;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/court")
public class CourtController
{
    private final CourtService courtService;
    private final LocationService locationService;

    private final ReservationService reservationService;

    //Constructor:
    @Autowired
    public CourtController(CourtService courtService, LocationService locationService, ReservationService reservationService)
    {
        this.courtService = courtService;
        this.locationService = locationService;
        this.reservationService = reservationService;
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

    //Get all courts:
    //Receive nothing, Send Ok + all courts from db;
    @GetMapping(value = "/getCourtsForAdmin")
    public ResponseEntity<List<GetAllCourtsFromLocationDTO>> getAllCourts()
    {
        //All courts from DB:
        List<GetAllCourtsFromLocationDTO> allCourts = courtService.findAllCourts();

        //Return all courts: Primesc din service DTO corect:
        return new ResponseEntity<>(allCourts, HttpStatus.OK);
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
            //If null, "", "", atunci return 1 element, doar location:
            if(Objects.equals(courtDTO.getId(), null) && Objects.equals(courtDTO.getType(), "") && Objects.equals(courtDTO.getName(), ""))
            {
                //Se pun date goale la court, dar se pun datele bune la locatie:
                GetAllCourtsFromLocationDTO newCourtDTO = new GetAllCourtsFromLocationDTO(
                        courtDTO.getId(), courtDTO.getType(), courtDTO.getName(),
                        location.getId(),
                        location.getAddress(),
                        location.getLongitude(),
                        location.getLatitude(),
                        location.getCourtsImage()
                );

                courtsFromLocationNew.add(newCourtDTO);

                //Nu mai adauga in lista:
                break;
            }

            //Generate new DTO:
            GetAllCourtsFromLocationDTO newCourtDTO = new GetAllCourtsFromLocationDTO(
                    courtDTO.getId(), courtDTO.getType(), courtDTO.getName(), location.getId(),
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
        //Nu consider mai putin de ora;

        //If date null => folosesc data now;
        //if(Objects.equals(locationAndDateDTO.getDateForCourts(), ""))
        if(locationAndDateDTO.getDateForCourts() == null)
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




        //Noua lista, cu courturile ramase:
        List<Court> newCourtsList = new ArrayList<>();

        //Am toate courts de la locatie;
        //Acum toate courts care nu sunt disponibile la acea ora nu se afiseaza:
        for(Court court: courtsList)
        {
            //Ma uit la reservarile fiecarui cort pe rand:
            List<Reservation> reservationsAtCourt = reservationService.findReservationsByCourt(court);

            //Pentru courtul acesta:
            int noIntersection = 0;

            //Pentru reservarile court-ului:
            for(Reservation reservation : reservationsAtCourt)
            {
                //Data begin and end pentru reservare:
                LocalDateTime beginDate = reservation.getArrivingTime();
                LocalDateTime endDate = reservation.getLeavingTime();

                //Pentru begin:
                int yearBegin = beginDate.getYear();
                Month monthBegin = beginDate.getMonth();
                int dayBegin = beginDate.getDayOfMonth();
                int hourBegin = beginDate.getHour();

                //Pentru end:
                int yearEnd = endDate.getYear();
                Month monthEnd = endDate.getMonth();
                int dayEnd = endDate.getDayOfMonth();
                int hourEnd = endDate.getHour();

                //Daca nu se intercaleaza cu nici o alta rezervare, atunci ii dau add:
                //Daca se intercaleaza, termin cu acest court si trec la urmatorul:
                if(year == yearBegin && Objects.equals(month, monthBegin)
                && day == dayBegin)
                {
                    //Daca sunt in acelasi an, luna, zi:
                    if(hour >= hourBegin && hour <= hourEnd)
                    {
                        //Exista intersectie:
                        noIntersection = 1;
                        break;

                        //AvailableCourtsDTO availableCourtsDTO = new AvailableCourtsDTO();
                        //return new ResponseEntity<>(availableCourtsDTO, HttpStatus.CONFLICT);
                    }
                }
            }

            //Dau add daca nu sunt intersectii:
            if(noIntersection == 0)
            {
                //Dupa add, trec la urmatorul court:
                newCourtsList.add(court);
            }
        }



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
        for(Court court: newCourtsList)
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
