package com.example._Buzila_Andra_Court_Reserve_Backend.dtos.builders;

import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.AddCourtDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.CourtDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Court;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Location;

public class CourtBuilder
{
    //Constructor empty:
    private CourtBuilder() {
    }

    //Enitity to CourtDTO:
    public static CourtDTO toCourtDTO(Court court)
    {
        return new CourtDTO(court.getId(), court.getType(),
                court.getName(), court.getLocation()
        );
    }

    //Entity to AddCourtDTO:
    //Ai locatia in entity, vrei doar un id:
    public static AddCourtDTO toAddCourtDTO(Court court) {
        return new AddCourtDTO(court.getType(),
                court.getName(), court.getLocation().getId()
        );
    }

    //CourtDTO to Entity:
    public static Court toCourtEntity(CourtDTO courtDTO) {
        return new Court(
                courtDTO.getId(),
                courtDTO.getType(),
                courtDTO.getName(),
                courtDTO.getLocation()
        );
    }

    //AddCourtDTO to Entity:
    public static Court toCourtEntityAdd(AddCourtDTO addCourtDTO, Location location) {
        return new Court(
                addCourtDTO.getType(),
                addCourtDTO.getName(),
                location
        );
    }
}
