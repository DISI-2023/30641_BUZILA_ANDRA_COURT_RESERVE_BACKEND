package com.example._Buzila_Andra_Court_Reserve_Backend.dtos;

import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Court;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Location;
import org.springframework.hateoas.RepresentationModel;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class CourtDTO extends RepresentationModel<CourtDTO> {

    private UUID id;

    @NotNull
    private String type;

    @NotNull
    private String name;

    private Location location;

    //Constructor with id:
    public CourtDTO(UUID id, String type, String name, Location location) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.location = location;
    }

    //Constructor without id:
    public CourtDTO(String type, String name, Location location) {
        this.type = type;
        this.name = name;
        this.location = location;
    }

    //Empty constructor:
    public CourtDTO() {
    }

    //Getters and Setters:
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
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
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }

    //Equals:
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourtDTO that = (CourtDTO) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(name, that.name);
    }

    //Hash:
    @Override
    public int hashCode() {
        return Objects.hash(type, name);
    }
}