package com.example._Buzila_Andra_Court_Reserve_Backend.services;

import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.AddReservationDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.AddUserDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.builders.ReservationBuilder;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.builders.UserBuilder;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.*;
import com.example._Buzila_Andra_Court_Reserve_Backend.repositories.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public List<Reservation> findReservationsByCourt(Court court)
    {
        //Find courts by location:
        List<Reservation> reservationsAtCourt = reservationRepository.findByCourt(court);

        return reservationsAtCourt;
    }

    public double calculatePrice(List<Tariff> tariffs, LocalDateTime arriving, LocalDateTime leaving)
    {
        double price = 0;
        for(Tariff t:tariffs)
        {
            System.out.println(t.getCriterion());
            if(t.getCriterion().equals("pretDeBaza"))
            {
                price += t.getValue();
            }
            //cazul in care e vara, e un pret in plus
            if(arriving.getMonth().getValue() >= 6 && arriving.getMonth().getValue() <= 8 && t.getCriterion().equals("vara"))
            {
                price += t.getValue();
            }
            //la fel caz pentru iarna
            if((arriving.getMonth().getValue() == 12 ||arriving.getMonth().getValue() == 1 || arriving.getMonth().getValue() == 2) &&
                    t.getCriterion().equals("iarna"))
            {
                price += t.getValue();
            }
            //pentru weekend
            if(arriving.getDayOfWeek().getValue() > 5 && t.getCriterion().equals("weekend"))
            {
                price += t.getValue();
            }
            //pentru nocturna, consider de la ora 18 incolo
            if(arriving.getHour() >= 18 && t.getCriterion().equals("nocturna"))
            {
                price += t.getValue();
            }
        }

        //consider ca pretul este per ora, deci mai inmultesc cu cate ore
        int hours = leaving.getHour() - arriving.getHour();

        price *= hours;

        return price;
    }
}
