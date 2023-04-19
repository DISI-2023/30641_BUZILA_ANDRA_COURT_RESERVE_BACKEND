package com.example._Buzila_Andra_Court_Reserve_Backend.dtos;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class AddLocationDTO
{
    @NotNull
    private String address;

    @NotNull
    private double longitude;

    @NotNull
    private double latitude;

    @NotNull
    private String courtsImage;

    //Constructor without id:
    public AddLocationDTO(String address, double longitude, double latitude, String courtsImage) {
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.courtsImage = courtsImage;
    }

    //Empty constructor:
    public AddLocationDTO()
    {
    }

    //Getters and Setters:
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

    //Equals:
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddLocationDTO that = (AddLocationDTO) o;
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
