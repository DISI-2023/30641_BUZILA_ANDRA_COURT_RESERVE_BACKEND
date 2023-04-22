package com.example._Buzila_Andra_Court_Reserve_Backend.dtos;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class LocationAndDateDTO
{
    //Location id:
    @NotNull
    private UUID locationId;

    //Date:
    @NotNull
    private LocalDateTime dateForCourts;

    //Constructor without id:
    public LocationAndDateDTO(UUID locationId, LocalDateTime dateForCourts) {
        this.locationId = locationId;
        this.dateForCourts = dateForCourts;
    }

    //Empty constructor:
    public LocationAndDateDTO() {
    }

    //Getters and Setters:
    public UUID getLocationId() {
        return locationId;
    }
    public void setLocationId(UUID locationId) {
        this.locationId = locationId;
    }
    public LocalDateTime getDateForCourts() {
        return dateForCourts;
    }
    public void setDateForCourts(LocalDateTime dateForCourts) {
        this.dateForCourts = dateForCourts;
    }

    //Equals:
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationAndDateDTO that = (LocationAndDateDTO) o;
        return Objects.equals(locationId, that.locationId) &&
                Objects.equals(dateForCourts, that.dateForCourts);
    }

    //Hash:
    @Override
    public int hashCode() {
        return Objects.hash(locationId, dateForCourts);
    }
}
