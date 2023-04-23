package com.example._Buzila_Andra_Court_Reserve_Backend.services;

import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Role;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.User;
import com.example._Buzila_Andra_Court_Reserve_Backend.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    //Controller:
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Find Email:
    public User findEntityUserByEmail(String email)
    {
        //Find user by email:
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
}
