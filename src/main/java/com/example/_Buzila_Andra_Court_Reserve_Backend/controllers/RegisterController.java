package com.example._Buzila_Andra_Court_Reserve_Backend.controllers;

import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.AddUserDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Location;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Role;
import com.example._Buzila_Andra_Court_Reserve_Backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/register")
public class RegisterController {

    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService)
    {
        this.userService = userService;
    }

//    @PostMapping()
//    public ResponseEntity<UUID> registerUser(@Valid @RequestBody AddUserDTO addUserDTO)
//    {
//        //Find role by id:
//        Role role = roleService.findEntityRoleByRole("client");
//
//        //UUID returnat de la insert:
//        UUID addUserId = userService.registerUser(addUserDTO, role);
//
//        //Return ID if corect:
//        return new ResponseEntity<UUID>(addUserId, HttpStatus.OK);
//    }
}
