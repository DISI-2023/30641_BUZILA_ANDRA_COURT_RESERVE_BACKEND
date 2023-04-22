package com.example._Buzila_Andra_Court_Reserve_Backend.dtos;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Builder
//@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailDTO
{
//    @NotNull //Not for email;
    @JsonProperty("email")
    private String email;

    //Constructor without id: JSon Property for conversion:
//    public EmailDTO(@JsonProperty("email") String email)
//    {
//        this.email = email;
//    }

//    @JsonProperty("email")
    public EmailDTO(String email)
    {
        this.email = email;
    }

    //Empty constructor:
//    public EmailDTO() {
//    }

    //Getters and Setters:
//    @JsonProperty("email")
//    public String getEmail() {
//        return email;
//    }

//    @JsonProperty("email")
//    public void setEmail(@JsonProperty("email") String email) {
//        this.email = email;
//    }

    //Equals:
//    @Override
//    public boolean equals(Object o)
//    {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        EmailDTO that = (EmailDTO) o;
//        return Objects.equals(email, that.email);
//    }

    //Hash:
//    @Override
//    public int hashCode() {
//        return Objects.hash(email);
//    }

    //ToString:
    @Override
    public String toString() {
        return "EmailDTO{" +
                "email='" + email + '\'' +
                '}';
    }
}
