package com.example._Buzila_Andra_Court_Reserve_Backend.repositories;


import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Role;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);
}
