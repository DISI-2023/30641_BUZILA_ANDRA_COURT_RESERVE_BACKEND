package com.example._Buzila_Andra_Court_Reserve_Backend.dtos;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

public class EmailDTO
{
    @NotNull
    private String email;

    //Constructor without id:
    public EmailDTO(String email) {
        this.email = email;
    }

    //Empty constructor:
    public EmailDTO() {
    }

    //Getters and Setters:
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    //Equals:
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailDTO that = (EmailDTO) o;
        return Objects.equals(email, that.email);
    }

    //Hash:
    @Override
    public int hashCode() {
        return Objects.hash();
    }
}
