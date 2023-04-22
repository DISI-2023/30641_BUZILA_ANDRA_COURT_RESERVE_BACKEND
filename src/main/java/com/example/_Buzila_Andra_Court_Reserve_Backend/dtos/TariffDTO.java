package com.example._Buzila_Andra_Court_Reserve_Backend.dtos;

import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Location;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

public class TariffDTO extends RepresentationModel<TariffDTO> {

    private UUID id;
    @NotNull
    private String criterion;
    @NotNull
    private float value;
    private Location location;

    public TariffDTO(){

    }

    public TariffDTO(String criterion, float value, Location location) {
        this.criterion = criterion;
        this.value = value;
        this.location = location;
    }

    public TariffDTO(UUID id, String criterion, float value, Location location) {
        this.id = id;
        this.criterion = criterion;
        this.value = value;
        this.location = location;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCriterion() {
        return criterion;
    }

    public void setCriterion(String criterion) {
        this.criterion = criterion;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TariffDTO that = (TariffDTO) o;
        return  Objects.equals(criterion, that.criterion) &&
                value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(criterion, value);
    }
}
