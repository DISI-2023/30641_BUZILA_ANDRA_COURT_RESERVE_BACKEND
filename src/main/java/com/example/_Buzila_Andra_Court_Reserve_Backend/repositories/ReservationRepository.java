package com.example._Buzila_Andra_Court_Reserve_Backend.repositories;

import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Court;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID>
{
    //Find all reservations with user id;
    @Query(value = "SELECT r " +
            "FROM Reservation r " +
            "WHERE r.user.id = :id")
    List<Reservation> findAllReservationsAtUser(@Param("id") UUID id);

    //Pentru find reservations by court:
    List<Reservation> findByCourt(Court court);
}
