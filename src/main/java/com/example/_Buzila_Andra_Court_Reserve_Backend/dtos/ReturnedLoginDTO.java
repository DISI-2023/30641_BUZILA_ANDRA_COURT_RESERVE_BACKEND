package com.example._Buzila_Andra_Court_Reserve_Backend.dtos;

import java.util.UUID;

public class ReturnedLoginDTO {

    private UUID id;

    private String role;

    public ReturnedLoginDTO(UUID id, String role) {
        this.id = id;
        this.role = role;
    }

    public ReturnedLoginDTO(){

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
