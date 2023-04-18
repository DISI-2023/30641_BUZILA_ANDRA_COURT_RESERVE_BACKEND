package com.example._Buzila_Andra_Court_Reserve_Backend.dtos;

import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Court;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class AvailableCourtsDTO
{
    //Location fields:
    @NotNull
    private UUID locationId;

    @NotNull
    private String locationAddress;

    @NotNull
    private double locationLongitude;

    @NotNull
    private double locationLatitude;

    //Available courts:
    @NotNull
    private List<Court> availableCourts;


    //Constructor without id:
    public AvailableCourtsDTO(UUID locationId, String locationAddress,
                              double locationLongitude, double locationLatitude,
                              List<Court> availableCourts) {
        this.locationId = locationId;
        this.locationAddress = locationAddress;
        this.locationLongitude = locationLongitude;
        this.locationLatitude = locationLatitude;
        this.availableCourts = availableCourts;
    }

    //Empty constructor:
    public AvailableCourtsDTO()
    {
    }

    //Getters and Setters:
    public UUID getLocationId() {
        return locationId;
    }
    public void setLocationId(UUID locationId) {
        this.locationId = locationId;
    }
    public String getLocationAddress() {
        return locationAddress;
    }
    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }
    public double getLocationLongitude() {
        return locationLongitude;
    }
    public void setLocationLongitude(double locationLongitude) {
        this.locationLongitude = locationLongitude;
    }
    public double getLocationLatitude() {
        return locationLatitude;
    }
    public void setLocationLatitude(double locationLatitude) {
        this.locationLatitude = locationLatitude;
    }
    public List<Court> getAvailableCourts() {
        return availableCourts;
    }
    public void setAvailableCourts(List<Court> availableCourts) {
        this.availableCourts = availableCourts;
    }

    //Equals:
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvailableCourtsDTO that = (AvailableCourtsDTO) o;
        return Objects.equals(locationId, that.locationId) &&
                Objects.equals(locationAddress, that.locationAddress) &&
                locationLongitude == that.locationLongitude &&
                locationLatitude == that.locationLatitude &&
                Objects.equals(availableCourts, that.availableCourts); //List;
    }

    //Hash:
    @Override
    public int hashCode() {
        return Objects.hash(locationId, locationAddress,
                locationLongitude, locationLatitude,
                availableCourts);
    }
}
