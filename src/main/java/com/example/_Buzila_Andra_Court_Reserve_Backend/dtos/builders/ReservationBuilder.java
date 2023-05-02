package com.example._Buzila_Andra_Court_Reserve_Backend.dtos.builders;

import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.AddReservationDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.ReservationDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Court;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Reservation;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.User;

public class ReservationBuilder {

    public ReservationBuilder(){

    }

    public static ReservationDTO toReservationDTO(Reservation reservation){
        return new ReservationDTO(reservation.getId(), reservation.getCourt(), reservation.getUser(), reservation.getArrivingTime(),
                reservation.getLeavingTime(), reservation.getPrice());
    }

    public static AddReservationDTO toAddReservationDTO(Reservation reservation){
        return new AddReservationDTO(reservation.getUser().getId(), reservation.getCourt().getId(),
                reservation.getArrivingTime(), reservation.getLeavingTime());
    }

    public static Reservation toReservationEntityWithID(ReservationDTO reservationDTO){
        return new Reservation(reservationDTO.getId(), reservationDTO.getCourt(), reservationDTO.getUser(),
                reservationDTO.getArrivingTime(), reservationDTO.getLeavingTime(), reservationDTO.getPrice());
    }

    public static Reservation toReservationEntity(AddReservationDTO addReservationDTO, Court court, User user, double price){
        return new Reservation(court, user, addReservationDTO.getArrivingTime(), addReservationDTO.getLeavingTime(),
                price);
    }
}
