package com.example._Buzila_Andra_Court_Reserve_Backend.dtos.builders;

import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.AddLocationDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.LocationDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Location;

public class LocationBuilder
{
    //Constructor empty:
    private LocationBuilder() {
    }

    //Enitity to LocationDTO:
    public static LocationDTO toLocationDTO(Location location)
    {
        //Cu courts:
//        return new LocationDTO(location.getId(), location.getAddress(),
//                location.getLongitude(), location.getLatitude(), location.getCourtsImage(),
//                location.getCourts()
//        );

        //Fara courts:
        return new LocationDTO(location.getId(), location.getAddress(),
                location.getLongitude(), location.getLatitude(), location.getCourtsImage()
        );
    }

    //Entity to AddLocationDTO:
    public static AddLocationDTO toAddLocationDTO(Location location) {
        return new AddLocationDTO(location.getAddress(),
                location.getLongitude(), location.getLatitude(), location.getCourtsImage()
        );
    }

    //LocationDTO to Entity:
    public static Location toLocationEntity(LocationDTO locationDTO) {
        return new Location(
                locationDTO.getId(),
                locationDTO.getAddress(),
                locationDTO.getLongitude(),
                locationDTO.getLatitude(),
                locationDTO.getCourtsImage()
        );
    }

    //AddLocationDTO to Entity:
    public static Location toLocationEntityAdd(AddLocationDTO addLocationDTO) {
        return new Location(
                addLocationDTO.getAddress(),
                addLocationDTO.getLongitude(),
                addLocationDTO.getLatitude(),
                addLocationDTO.getCourtsImage()
        );
    }
}
