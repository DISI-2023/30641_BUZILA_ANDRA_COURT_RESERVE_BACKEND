package com.example._Buzila_Andra_Court_Reserve_Backend.repositories;

import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Court;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.UUID;

public interface CourtRepository extends JpaRepository<Court, UUID>
{
    //Save predefinit;

    //Find all courts with location id;
    @Query(value = "SELECT c " +
            "FROM Court c " +
            "WHERE c.location.id = :id")
    List<Court> findAllCourtsAtLocation(@Param("id") UUID id);
}
