package com.example._Buzila_Andra_Court_Reserve_Backend.services;

import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.AddTariffDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.builders.TariffBuilder;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Location;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Tariff;
import com.example._Buzila_Andra_Court_Reserve_Backend.repositories.TariffRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TariffService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TariffService.class);
    private final TariffRepository tariffRepository;

    @Autowired
    public TariffService(TariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
    }

    public UUID insert(AddTariffDTO addTariffDTO, Location location) {
        Tariff tariff = TariffBuilder.toTariffEntity(addTariffDTO, location);
        tariff = tariffRepository.save(tariff);
        LOGGER.debug("Tariff with id {} was inserted in db", tariff.getId());
        return tariff.getId();
    }
}
