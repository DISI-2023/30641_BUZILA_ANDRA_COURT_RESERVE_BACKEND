package com.example._Buzila_Andra_Court_Reserve_Backend.repositories;

import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface LocationRepository extends JpaRepository<Location, UUID>
{
    //Save predefinit;
}
