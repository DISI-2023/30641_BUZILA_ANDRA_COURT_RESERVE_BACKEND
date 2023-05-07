package com.example._Buzila_Andra_Court_Reserve_Backend.dtos;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class GetAllReservationForUserDTO {

    //Reservation id:
    @NotNull
    private UUID id;

    //User data:
    @NotNull
    private UUID user_id;

    @NotNull
    private String user_firstname;

    @NotNull
    private String user_lastname;

    @NotNull
    private String user_email;

    //No password / role;

    //Court data:
    @NotNull
    private UUID court_id;

    @NotNull
    private String court_type;

    @NotNull
    private String court_name;

    @NotNull
    private UUID court_location_id;

    @NotNull
    private String court_location_address;

    @NotNull
    private LocalDateTime arrivingTime;

    @NotNull
    private LocalDateTime leavingTime;

    @NotNull
    private double price;

    //Constructor:
    public GetAllReservationForUserDTO(UUID id, UUID user_id, String user_firstname, String user_lastname, String user_email,
                                       UUID court_id, String court_type, String court_name,
                                       UUID court_location_id, String court_location_address,
                                       LocalDateTime arrivingTime, LocalDateTime leavingTime,
                                       double price) {
        this.id = id;
        this.user_id = user_id;
        this.user_firstname = user_firstname;
        this.user_lastname = user_lastname;
        this.user_email = user_email;
        this.court_id = court_id;
        this.court_type = court_type;
        this.court_name = court_name;
        this.court_location_id = court_location_id;
        this.court_location_address = court_location_address;
        this.arrivingTime = arrivingTime;
        this.leavingTime = leavingTime;
        this.price = price;
    }

    //Empty constructor:
    public GetAllReservationForUserDTO(){

    }

    //Setters and Getters:
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public UUID getUser_id() {
        return user_id;
    }
    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }
    public String getUser_firstname() {
        return user_firstname;
    }
    public void setUser_firstname(String user_firstname) {
        this.user_firstname = user_firstname;
    }
    public String getUser_lastname() {
        return user_lastname;
    }
    public void setUser_lastname(String user_lastname) {
        this.user_lastname = user_lastname;
    }
    public String getUser_email() {
        return user_email;
    }
    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }
    public UUID getCourt_id() {
        return court_id;
    }
    public void setCourt_id(UUID court_id) {
        this.court_id = court_id;
    }
    public String getCourt_type() {
        return court_type;
    }
    public void setCourt_type(String court_type) {
        this.court_type = court_type;
    }
    public String getCourt_name() {
        return court_name;
    }
    public void setCourt_name(String court_name) {
        this.court_name = court_name;
    }
    public UUID getCourt_location_id() {
        return court_location_id;
    }
    public void setCourt_location_id(UUID court_location_id) {
        this.court_location_id = court_location_id;
    }
    public String getCourt_location_address() {
        return court_location_address;
    }
    public void setCourt_location_address(String court_location_address) {
        this.court_location_address = court_location_address;
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
        GetAllReservationForUserDTO that = (GetAllReservationForUserDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(user_id, that.user_id) &&
                Objects.equals(user_firstname, that.user_firstname) &&
                Objects.equals(user_lastname, that.user_lastname) &&
                Objects.equals(user_email, that.user_email) &&
                Objects.equals(court_id, that.court_id) &&
                Objects.equals(court_type, that.court_type) &&
                Objects.equals(court_name, that.court_name) &&
                Objects.equals(court_location_id, that.court_location_id) &&
                Objects.equals(court_location_address, that.court_location_address) &&
                Objects.equals(arrivingTime, that.arrivingTime) &&
                Objects.equals(leavingTime, that.leavingTime) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user_id, user_firstname, user_lastname, user_email,
                court_id, court_type, court_name, court_location_id, court_location_address,
                arrivingTime, leavingTime, price);
    }
}