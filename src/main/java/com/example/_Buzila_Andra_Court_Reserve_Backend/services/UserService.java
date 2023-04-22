package com.example._Buzila_Andra_Court_Reserve_Backend.services;

import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.AddUserDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.builders.CourtBuilder;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Court;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Role;
import com.example._Buzila_Andra_Court_Reserve_Backend.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    public UUID registerUser(AddUserDTO addUserDTO, Role role)
//    {
//        //Din DTO in Entity;
//        Court court = CourtBuilder.toCourtEntityAdd(addCourtDTO, location);
//        UUID courtId = court.getId(); //Id;
//
//        //Save object with repository; Return object;
//        court = courtRepository.save(court);
//
//        //Court inserted, return id;
//        LOGGER.debug("Court with id {} was inserted in the db!", court.getId());
//        return court.getId();
//    }
}
