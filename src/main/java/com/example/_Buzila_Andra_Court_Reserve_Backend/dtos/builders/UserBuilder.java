package com.example._Buzila_Andra_Court_Reserve_Backend.dtos.builders;

import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.AddCourtDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.AddUserDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.CourtDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.UserDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Court;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Location;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Role;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.User;

public class UserBuilder {

    private UserBuilder() {

    }
    //Enitity to UserDTO:
    public static UserDTO toUserDTO(User user)
    {
        return new UserDTO(user.getId(), user.getFirstname(),
                user.getLastname(), user.getEmail(), user.getPassword(),
                user.getRole());
    }
    //Entity to AddUserDTO:
    public static AddUserDTO toAddUserDTO(User user) {
        return new AddUserDTO(user.getFirstname(), user.getLastname(),
                user.getEmail(), user.getPassword()
        );
    }

    //UserDTO to Entity:
    public static User toUserEntity(UserDTO userDTO) {
        return new User(
                userDTO.getId(),
                userDTO.getFirstname(),
                userDTO.getLastname(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getRole()
        );
    }

    //AddUserDTO to Entity:
    public static User toUserEntityAdd(AddUserDTO addUserDTO, Role role) {
        return new User(
                addUserDTO.getFirstname(),
                addUserDTO.getLastname(),
                addUserDTO.getEmail(),
                addUserDTO.getPassword(),
                role
        );
    }
}
