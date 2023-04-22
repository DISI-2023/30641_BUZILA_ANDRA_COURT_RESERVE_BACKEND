package com.example._Buzila_Andra_Court_Reserve_Backend.repositories;

import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
}
