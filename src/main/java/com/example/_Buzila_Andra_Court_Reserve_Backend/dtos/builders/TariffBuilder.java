package com.example._Buzila_Andra_Court_Reserve_Backend.dtos.builders;

import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.AddTariffDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.TariffDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Location;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Tariff;

public class TariffBuilder {

    public TariffBuilder() {
    }

    public static TariffDTO toTariffDTO(Tariff tariff) {
        return new TariffDTO(tariff.getId(), tariff.getCriterion(), tariff.getValue(), tariff.getLocation());
    }

    public static AddTariffDTO toAddTariffDTO(Tariff tariff) {
        return new AddTariffDTO(tariff.getCriterion(), tariff.getValue(), tariff.getLocation().getId());
    }

    public static Tariff toTariffEntityWithID(TariffDTO tariffDTO) {
        return new Tariff(tariffDTO.getId(), tariffDTO.getCriterion(), tariffDTO.getValue(),
                tariffDTO.getLocation());
    }

    public static Tariff toTariffEntity(AddTariffDTO addTariffDTO, Location location) {
        return new Tariff(addTariffDTO.getCriterion(), addTariffDTO.getValue(),
                location);
    }
}
