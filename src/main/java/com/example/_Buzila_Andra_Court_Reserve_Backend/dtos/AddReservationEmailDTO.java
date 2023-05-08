package com.example._Buzila_Andra_Court_Reserve_Backend.dtos;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddReservationEmailDTO
{
    private String email;

    private String courtName;

    private String locationAddress;

    //private LocalDateTime arrivingTime;
    private String arrivingTime;

    //private LocalDateTime leavingTime;
    private String leavingTime;

    private double price;


    //Constructor:
    public AddReservationEmailDTO(
            @JsonProperty("email") String email,
            @JsonProperty("courtName") String courtName,
            @JsonProperty("locationAddress") String locationAddress,
//            @JsonProperty("arrivingTime") LocalDateTime arrivingTime,
//            @JsonProperty("leavingTime") LocalDateTime leavingTime,
            @JsonProperty("arrivingTime") String arrivingTime,
            @JsonProperty("leavingTime") String leavingTime,
            @JsonProperty("price") double price
    )
    {
        this.email = email;
        this.courtName = courtName;
        this.locationAddress = locationAddress;
        this.arrivingTime = arrivingTime;
        this.leavingTime = leavingTime;
        this.price = price;
    }

    //ToString:
    @Override
    public String toString() {
        return "AddReservationEmailDTO{" +
                "email='" + email + '\'' +
                ", courtName='" + courtName + '\'' +
                ", locationAddress='" + locationAddress + '\'' +
                ", arrivingTime=" + arrivingTime + '\'' +
                ", leavingTime=" + leavingTime + '\'' +
                ", price=" + price + '\'' +
                '}';
    }
}
