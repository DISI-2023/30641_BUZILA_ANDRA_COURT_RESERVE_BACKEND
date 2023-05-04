package com.example._Buzila_Andra_Court_Reserve_Backend.controllers;

import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.AddReservationDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.AddUserDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Court;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Reservation;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Role;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.User;
import com.example._Buzila_Andra_Court_Reserve_Backend.services.CourtService;
import com.example._Buzila_Andra_Court_Reserve_Backend.services.ReservationService;
import com.example._Buzila_Andra_Court_Reserve_Backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/reservation")
public class ReservationController {

    private final ReservationService reservationService;
    private final UserService  userService;
    private final CourtService courtService;

    @Autowired
    public ReservationController(ReservationService reservationService, UserService userService, CourtService courtService) {
        this.reservationService = reservationService;
        this.userService = userService;
        this.courtService = courtService;
    }

    @PostMapping()
    public ResponseEntity<UUID> insertReservation(@Valid @RequestBody AddReservationDTO addReservationDTO)
    {
        //find court by id
        Court court = courtService.findEntityCourtById(addReservationDTO.getCourt_id());

        //find user by id
        User user = userService.findEntityUserById(addReservationDTO.getUser_id());

        if(user == null || court == null)
        {
            return new ResponseEntity<UUID>(UUID.randomUUID(), HttpStatus.NOT_FOUND);
        }

        //am toate rezervarile de la acest court, verific daca una dintre ele se intercaleaza din punct de vedere al
        //timpului cu cea actuala
        List<Reservation> reservationsAtCourt = reservationService.findReservationsByCourt(court);
        LocalDateTime dataActualaArriving = addReservationDTO.getArrivingTime();
        LocalDateTime dataActualaLeaving = addReservationDTO.getLeavingTime();
        for(Reservation r:reservationsAtCourt)
        {
            LocalDateTime dataRezervareBDArriving = r.getArrivingTime();
            LocalDateTime dataRezervareBDLeaving = r.getLeavingTime();
            if(dataRezervareBDArriving.getYear()==dataActualaArriving.getYear() &&
                    dataRezervareBDArriving.getMonth().equals(dataActualaArriving.getMonth()) &&
                    dataRezervareBDArriving.getDayOfMonth()==dataActualaArriving.getDayOfMonth())
            {
                if((dataActualaArriving.getHour() <= dataRezervareBDArriving.getHour() && dataActualaLeaving.getHour() >
                dataRezervareBDArriving.getHour()) || (dataActualaArriving.getHour() < dataRezervareBDLeaving.getHour()
                        && dataActualaLeaving.getHour() >= dataRezervareBDLeaving.getHour()))
                {

                    return new ResponseEntity<UUID>(UUID.randomUUID(), HttpStatus.CONFLICT);
                }
            }
        }

        //trebuie calculat si un price
        double price=0;

        //UUID returnat de la insert:
        UUID addReservationId = reservationService.insertReservation(addReservationDTO, court, user, price);

        //Return ID if corect:
        return new ResponseEntity<UUID>(addReservationId, HttpStatus.OK);
    }
}
