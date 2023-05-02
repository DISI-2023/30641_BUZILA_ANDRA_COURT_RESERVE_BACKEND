package com.example._Buzila_Andra_Court_Reserve_Backend.services;

import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.AddUserDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.builders.UserBuilder;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Court;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Role;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.User;
import com.example._Buzila_Andra_Court_Reserve_Backend.repositories.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReservationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationService.class);
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

//    public UUID insertReservation(AddReservationDTO addReservationDTO, Court court, User user)
//    {
//        //Din DTO in Entity;
//        //User user = UserBuilder.toUserEntityAdd(addUserDTO, role);
//
//        //Save object with repository; Return object;
//        //user = userRepository.save(user);
//
//        //LOGGER.debug("Reservation with id {} was inserted in the db!", user.getId());
//        return user.getId();
//    }
}
