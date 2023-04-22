package com.example._Buzila_Andra_Court_Reserve_Backend.controllers;

import com.example._Buzila_Andra_Court_Reserve_Backend.config.RabbitSender;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.EmailDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//Pentru resetare parola:
@RestController
@CrossOrigin
@RequestMapping(value = "/resetPassword")
public class ResetController
{
    private static final Logger log = LoggerFactory.getLogger(ResetController.class);

    @Autowired
    private RabbitSender rabbitSender;

    //Controller:
    public ResetController()
    {
    }

    //Post pentru trimitere email de refacere parola:
    @PostMapping(value = "/changePassword")
    public void sendEmailData(EmailDTO emailDTO)
    {
        //Sent to RMQ Email Data; (Coada asincrona)
        //Cu send pune in coada, in RMQ queue;
        rabbitSender.send(emailDTO);
    }
}
