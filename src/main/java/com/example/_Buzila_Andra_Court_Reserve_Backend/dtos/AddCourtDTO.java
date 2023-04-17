package com.example._Buzila_Andra_Court_Reserve_Backend.dtos;

import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Location;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

public class AddCourtDTO
{
    @NotNull
    private String type;

    @NotNull
    private String name;

    @NotNull
    private UUID location_id;

    //Constructor without id (personal id):
    public AddCourtDTO(String type, String name, UUID location_id) {
        this.type = type;
        this.name = name;
        this.location_id = location_id;
    }

    //Empty constructor:
    public AddCourtDTO()
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

    //Equals:
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddCourtDTO that = (AddCourtDTO) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(name, that.name);
    }

    //Hash:
    @Override
    public int hashCode() {
        return Objects.hash(type, name);
    }
}
