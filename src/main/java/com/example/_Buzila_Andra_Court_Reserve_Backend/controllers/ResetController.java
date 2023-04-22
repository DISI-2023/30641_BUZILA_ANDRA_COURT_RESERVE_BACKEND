package com.example._Buzila_Andra_Court_Reserve_Backend.controllers;

import com.example._Buzila_Andra_Court_Reserve_Backend.config.Email;
import com.example._Buzila_Andra_Court_Reserve_Backend.config.RabbitSender;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.EmailDTO;
import freemarker.template.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.*;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

//Pentru resetare parola:
@RestController
@CrossOrigin
@RequestMapping(value = "/resetPassword")
public class ResetController
{
    private static final Logger log = LoggerFactory.getLogger(ResetController.class);

    @Autowired
    private RabbitSender rabbitSender;

//    @Autowired
//    private JavaMailSender javaMailSender;

//    @Autowired
//    Configuration fmConfiguration;

    //Controller:
    public ResetController()
    {
    }

    //Post pentru trimitere email de refacere parola: SEND: Valid Request:
    @PostMapping(value = "/changePassword")
    public void sendEmailData(@Valid @RequestBody EmailDTO emailDTO)
    {
        //Sent to RMQ Email Data; (Coada asincrona)
        //Cu send pune in coada, in RMQ queue;
        //System.out.println("The email data " + emailDTO + " was sent.");
        //System.out.println("The email data " + emailDTO.getEmail() + " was sent.");

        //Pentru JSon Property:
        EmailDTO rmqEmailDTO = new EmailDTO(emailDTO.getEmail());

        rabbitSender.send(rmqEmailDTO);
    }
}
