package com.example._Buzila_Andra_Court_Reserve_Backend.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.http.ResponseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    //@Type(type = "uuid-binary")
    @Type(type = "uuid-char")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "court_id")
    private Court court;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "arrivingTime", nullable = false)
    private LocalDateTime arrivingTime;

    @Column(name = "leavingTime", nullable = false)
    private LocalDateTime leavingTime;

    @Column(name = "price", nullable = false)
    private double price;

    public Reservation() {

    }

    public Reservation(UUID id, Court court, User user, LocalDateTime arrivingTime, LocalDateTime leavingTime, double price) {
        this.id = id;
        this.court = court;
        this.user = user;
        this.arrivingTime = arrivingTime;
        this.leavingTime = leavingTime;
        this.price = price;
    }

    public Reservation(Court court, User user, LocalDateTime arrivingTime, LocalDateTime leavingTime, double price) {
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


}
