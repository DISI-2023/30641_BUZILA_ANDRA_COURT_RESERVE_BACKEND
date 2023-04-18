package com.example._Buzila_Andra_Court_Reserve_Backend.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
public class Location implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-char")
    private UUID id;

    //:, unique = true
    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "longitude", nullable = false)
    private double longitude;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @Column(name = "courtsImage", nullable = false)
    private String courtsImage;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Court> courts;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Tariff> tariffs;

    //Empty constructor:
    public Location() {
    }

    //Constructor with id:
    public Location(UUID id, String address, double longitude, double latitude, String courtsImage) {
        this.id = id;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.courtsImage = courtsImage;
    }

    //Constructor without id:
    public Location(String address, double longitude, double latitude, String courtsImage) {
        this.id = id;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.courtsImage = courtsImage;
    }

    //Getters and Setters:
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public String getCourtsImage() {
        return courtsImage;
    }
    public void setCourtsImage(String courtsImage) {
        this.courtsImage = courtsImage;
    }
    public List<Court> getCourts() {
        return courts;
    }
    public void setCourts(List<Court> courts) {
        this.courts = courts;
    }

    public void testFunction(){
        
    }
}