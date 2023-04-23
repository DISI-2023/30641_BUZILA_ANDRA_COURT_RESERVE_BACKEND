package com.example._Buzila_Andra_Court_Reserve_Backend.dtos;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class DeleteCourtDTO {
    @NotNull
    private UUID id;

    public DeleteCourtDTO(UUID id) {
        this.id = id;
    }

    public DeleteCourtDTO() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
