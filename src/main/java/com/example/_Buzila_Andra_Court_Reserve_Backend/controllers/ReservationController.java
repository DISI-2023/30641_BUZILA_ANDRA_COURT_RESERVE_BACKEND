package com.example._Buzila_Andra_Court_Reserve_Backend.controllers;

import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.AddReservationDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.AddUserDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.GetAllReservationForUserDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.LocationDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Court;
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
    public ResponseEntity insertReservation(@Valid @RequestBody AddReservationDTO addReservationDTO)
    {
        //find court by id
        Court court = courtService.findEntityCourtById(addReservationDTO.getCourt_id());

        //find user by id
        User user = userService.findEntityUserById(addReservationDTO.getUser_id());

        if(user == null || court == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        //trebuie calculat si un price
        double price=0;

        //UUID returnat de la insert:
        UUID addReservationId = reservationService.insertReservation(addReservationDTO, court, user, price);

        //Return ID if corect:
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Get all reservations:
    @GetMapping(value = "/getAllReservations" + "/{id}")
    public ResponseEntity<List<GetAllReservationForUserDTO>> getAllReservationsForUser(@PathVariable("id") UUID userId)
    {
        //All reservations from DB, for that user:
        List<GetAllReservationForUserDTO> allReservations = reservationService.findAllReservationsForUser(userId);

        //Return all reservations:
        return new ResponseEntity<>(allReservations, HttpStatus.OK);
    }
}
