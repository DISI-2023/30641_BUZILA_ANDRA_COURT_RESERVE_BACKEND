package com.example._Buzila_Andra_Court_Reserve_Backend.dtos;

import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Court;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.User;
import org.springframework.hateoas.RepresentationModel;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class ReservationDTO extends RepresentationModel<ReservationDTO> {

    private UUID id;

    private Court court;

    private User user;

    @NotNull
    private LocalDateTime arrivingTime;

    @NotNull
    private LocalDateTime leavingTime;

    @NotNull
    private double price;

    public ReservationDTO(){

    }

    public ReservationDTO(UUID id, Court court, User user, LocalDateTime arrivingTime, LocalDateTime leavingTime, double price) {
        this.id = id;
        this.court = court;
        this.user = user;
        this.arrivingTime = arrivingTime;
        this.leavingTime = leavingTime;
        this.price = price;
    }

    public ReservationDTO(Court court, User user, LocalDateTime arrivingTime, LocalDateTime leavingTime, double price) {
        this.court = court;
        this.user = user;
        this.arrivingTime = arrivingTime;
        this.leavingTime = leavingTime;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Court getCourt() {
        return court;
    }

    public void setCourt(Court court) {
        this.court = court;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getArrivingTime() {
        return arrivingTime;
    }

    public void setArrivingTime(LocalDateTime arrivingTime) {
        this.arrivingTime = arrivingTime;
    }

    public LocalDateTime getLeavingTime() {
        return leavingTime;
    }

    public void setLeavingTime(LocalDateTime leavingTime) {
        this.leavingTime = leavingTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationDTO that = (ReservationDTO) o;
        return Double.compare(that.price, price) == 0 &&
                Objects.equals(id, that.id) && Objects.equals(court, that.court) &&
                Objects.equals(user, that.user) && Objects.equals(arrivingTime, that.arrivingTime) &&
                Objects.equals(leavingTime, that.leavingTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, court, user, arrivingTime, leavingTime, price);
    }
}
