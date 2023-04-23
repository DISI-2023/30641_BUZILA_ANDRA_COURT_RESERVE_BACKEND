package com.example._Buzila_Andra_Court_Reserve_Backend.services;

import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.AddUserDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.builders.CourtBuilder;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.builders.UserBuilder;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Court;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Role;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.User;
import com.example._Buzila_Andra_Court_Reserve_Backend.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID registerUser(AddUserDTO addUserDTO, Role role)
    {
        //Din DTO in Entity;
        User user = UserBuilder.toUserEntityAdd(addUserDTO, role);

        //Save object with repository; Return object;
        user = userRepository.save(user);

        //Court inserted, return id;
        LOGGER.debug("User with id {} was inserted in the db!", user.getId());
        return user.getId();
    }

    public User findEntityUserByEmail(String email)
    {
        //Find in DB:
        Optional<User> userOptional = userRepository.findByEmail(email);

        //If present, log, if not, throw;
        if (userOptional.isPresent()) {
            LOGGER.error("User with email {} already exists!", email);

            return userOptional.get();
        }

        //If not present, nothing:
        return null;
    }

    //Update user password:
    public User updateUser(User user, String newPassword)
    {
        //Exista user, parola este buna, acum mai trebuie doar salvata:

        //Salvare parola:
        //Nu conteaza daca este aceeasi parola ca cea veche;
        user.setPassword(newPassword);

        //Salvare user:
        User newUser = userRepository.save(user);

        LOGGER.debug("User with id {} was updated in the db!", newUser.getId());
        return newUser;
    }

    public User findEntityUserByEmailLogin(String email)
    {
        //Find in DB:
        Optional<User> userOptional = userRepository.findByEmail(email);

        //If present, log, if not, throw;
        if (!userOptional.isPresent()) {
            LOGGER.error("User with email {} was not found in db", email);
            throw new ResourceNotFoundException(User.class.getSimpleName() + " with email: " + email);
        }

        return userOptional.get();
    }
}
