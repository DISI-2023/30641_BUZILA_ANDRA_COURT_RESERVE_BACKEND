package com.example._Buzila_Andra_Court_Reserve_Backend.dtos;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class UpdateCourtDTO {

    @NotNull
    private UUID id;

    @NotNull
    private String type;

    @NotNull
    private String name;

    public UpdateCourtDTO(){

    }

    public UpdateCourtDTO(UUID id, String type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public UpdateCourtDTO(String type, String name) {
        this.type = type;
        this.name = name;
    }

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
}
