package com.example._Buzila_Andra_Court_Reserve_Backend.dtos;

import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Location;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

public class GetAllCourtsFromLocationDTO
{
    @NotNull
    private String type;

    @NotNull
    private String name;

    @NotNull
    private UUID location_id;

    @NotNull
    private String location_address;

    @NotNull
    private double location_longitude;

    @NotNull
    private double location_latitude;

    @NotNull
    private String location_courtsImage;

    //Constructor without id (personal id):
    public GetAllCourtsFromLocationDTO(String type, String name, UUID location_id,
                                       String location_address,
                                       double location_longitude, double location_latitude,
                                       String location_courtsImage) {
        this.type = type;
        this.name = name;
        this.location_id = location_id;
        this.location_address = location_address;
        this.location_longitude = location_longitude;
        this.location_latitude = location_latitude;
        this.location_courtsImage = location_courtsImage;
    }

    //Empty constructor:
    public GetAllCourtsFromLocationDTO()
    {
    }

    //Getters and Setters:
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public UUID getLocation_id() {
        return location_id;
    }
    public void setLocation_id(UUID location_id) {
        this.location_id = location_id;
    }
    public String getLocation_address() {
        return location_address;
    }
    public void setLocation_address(String location_address) {
        this.location_address = location_address;
    }
    public double getLocation_longitude() {
        return location_longitude;
    }
    public void setLocation_longitude(double location_longitude) {
        this.location_longitude = location_longitude;
    }
    public double getLocation_latitude() {
        return location_latitude;
    }
    public void setLocation_latitude(double location_latitude) {
        this.location_latitude = location_latitude;
    }
    public String getLocation_courtsImage() {
        return location_courtsImage;
    }
    public void setLocation_courtsImage(String location_courtsImage) {
        this.location_courtsImage = location_courtsImage;
    }

    //Equals:
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetAllCourtsFromLocationDTO that = (GetAllCourtsFromLocationDTO) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(name, that.name) &&
                Objects.equals(location_id, that.location_id) &&
                Objects.equals(location_address, that.location_address) &&
                location_longitude == location_longitude && //Double;
                location_latitude == location_latitude &&
                Objects.equals(location_courtsImage, that.location_courtsImage);
    }

    //Hash:
    @Override
    public int hashCode() {
        return Objects.hash(type, name,
                location_id, location_address,
                location_longitude, location_latitude,
                location_courtsImage);
    }
}
