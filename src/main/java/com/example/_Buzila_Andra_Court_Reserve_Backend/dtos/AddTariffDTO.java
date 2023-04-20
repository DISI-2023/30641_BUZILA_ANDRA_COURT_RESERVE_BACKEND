package com.example._Buzila_Andra_Court_Reserve_Backend.dtos;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

public class AddTariffDTO {
    @NotNull
    private String criterion;
    @NotNull
    private float value;
    @NotNull
    private UUID location_id;

    public AddTariffDTO() {
    }

    public AddTariffDTO(String criterion, float value, UUID location_id) {
        this.criterion = criterion;
        this.value = value;
        this.location_id = location_id;
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

    public UUID getLocation_id() {
        return location_id;
    }

    public void setLocation_id(UUID location_id) {
        this.location_id = location_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddTariffDTO that = (AddTariffDTO) o;
        return  Objects.equals(criterion, that.criterion) &&
                value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(criterion, value);
    }
}
