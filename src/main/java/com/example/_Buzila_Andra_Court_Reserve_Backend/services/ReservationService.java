package com.example._Buzila_Andra_Court_Reserve_Backend.services;

import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.AddReservationDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.AddUserDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.CourtDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.GetAllReservationForUserDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.builders.CourtBuilder;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.builders.ReservationBuilder;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.builders.UserBuilder;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Court;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Reservation;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Role;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.User;
import com.example._Buzila_Andra_Court_Reserve_Backend.repositories.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationService.class);
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public UUID insertReservation(AddReservationDTO addReservationDTO, Court court, User user, double price)
    {
        //Din DTO in Entity;
        Reservation reservation = ReservationBuilder.toReservationEntity(addReservationDTO, court, user, price);

        //Save object with repository; Return object;
        reservation = reservationRepository.save(reservation);

        //LOGGER.debug("Reservation with id {} was inserted in the db!", user.getId());
        return reservation.getId();
    }

    //Find reservations by court:
    public List<Reservation> findReservationsByCourt(Court court)
    {
        //Find courts by location:
        List<Reservation> reservationsAtCourt = reservationRepository.findByCourt(court);

        return reservationsAtCourt;
    }

    //Get all reservations for a user:
    public List<GetAllReservationForUserDTO> findAllReservationsForUser(UUID id)
    {
        //Find all reservations for user id:
        List<Reservation> userReservations = reservationRepository.findAllReservationsAtUser(id);

        //List DTO:
        List<GetAllReservationForUserDTO> userReservationsDTO = new ArrayList<>();

        //If nothing, return empty list:
        if(userReservations.isEmpty())
        {
            return userReservationsDTO;
        }

        //Daca are reservations, return list with data:
        for(Reservation reservation: userReservations)
        {
            //Datele din user and court puse:
            userReservationsDTO.add(new GetAllReservationForUserDTO(
                    reservation.getId(), reservation.getUser().getId(), reservation.getUser().getFirstname(),
                    reservation.getUser().getLastname(), reservation.getUser().getEmail(),
                    reservation.getCourt().getId(), reservation.getCourt().getType(),
                    reservation.getCourt().getName(), reservation.getCourt().getLocation().getId(),
                    reservation.getCourt().getLocation().getAddress(), reservation.getArrivingTime(),
                    reservation.getLeavingTime(), reservation.getPrice()
            ));
        }

        return userReservationsDTO;
    }
}
