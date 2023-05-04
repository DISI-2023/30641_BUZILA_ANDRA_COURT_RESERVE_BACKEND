package com.example._Buzila_Andra_Court_Reserve_Backend.dtos;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class GetAllReservationForUserDTO {

    @NotNull
    private UUID user_id;
    @NotNull
    private UUID court_id;
    @NotNull
    private LocalDateTime arrivingTime;
    @NotNull
    private LocalDateTime leavingTime;

    public GetAllReservationForUserDTO(){

    }

    public GetAllReservationForUserDTO(UUID user_id, UUID court_id, LocalDateTime arrivingTime, LocalDateTime leavingTime) {
        this.user_id = user_id;
        this.court_id = court_id;
        this.arrivingTime = arrivingTime;
        this.leavingTime = leavingTime;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    public UUID getCourt_id() {
        return court_id;
    }

    public void setCourt_id(UUID court_id) {
        this.court_id = court_id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetAllReservationForUserDTO that = (GetAllReservationForUserDTO) o;
        return Objects.equals(user_id, that.user_id) &&
                Objects.equals(court_id, that.court_id) &&
                Objects.equals(arrivingTime, that.arrivingTime) &&
                Objects.equals(leavingTime, that.leavingTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, court_id, arrivingTime, leavingTime);
    }
}
