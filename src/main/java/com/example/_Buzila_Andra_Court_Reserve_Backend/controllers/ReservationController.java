package com.example._Buzila_Andra_Court_Reserve_Backend.controllers;

import com.example._Buzila_Andra_Court_Reserve_Backend.services.CourtService;
import com.example._Buzila_Andra_Court_Reserve_Backend.services.ReservationService;
import com.example._Buzila_Andra_Court_Reserve_Backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
