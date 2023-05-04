package com.example._Buzila_Andra_Court_Reserve_Backend.repositories;

import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Court;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Reservation;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

    List<Reservation> findByCourt(Court court);
}
