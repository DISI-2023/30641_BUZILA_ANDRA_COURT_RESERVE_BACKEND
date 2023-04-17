package com.example._Buzila_Andra_Court_Reserve_Backend.dtos;

import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Court;
import org.springframework.hateoas.RepresentationModel;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class LocationDTO extends RepresentationModel<LocationDTO> {

    private UUID id;

    @NotNull
    private String address;

    @NotNull
    private double longitude;

    @NotNull
    private double latitude;

    @NotNull
    private String courtsImage;

    private List<Court> courts;

    //Constructor with id:
    public LocationDTO(UUID id, String address, double longitude, double latitude, String courtsImage) {
        this.id = id;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.courtsImage = courtsImage;
    }

    //Constructor without id:
    public LocationDTO(String address, int longitude, int latitude, String courtsImage) {
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.courtsImage = courtsImage;
    }

    //Empty constructor:
    public LocationDTO() {
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

    //Equals:
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationDTO that = (LocationDTO) o;
        return Objects.equals(address, that.address) &&
                longitude == that.longitude &&
                latitude == that.latitude &&
                Objects.equals(courtsImage, that.courtsImage);
    }

    //Hash:
    @Override
    public int hashCode() {
        return Objects.hash(address, longitude, latitude, courtsImage);
    }
}