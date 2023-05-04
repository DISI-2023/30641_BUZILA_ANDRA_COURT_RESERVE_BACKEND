package com.example._Buzila_Andra_Court_Reserve_Backend.controllers;

import com.example._Buzila_Andra_Court_Reserve_Backend.config.Email;
import com.example._Buzila_Andra_Court_Reserve_Backend.config.RabbitSender;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.EmailDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.ResetPasswordDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.Location;
import com.example._Buzila_Andra_Court_Reserve_Backend.entities.User;
import com.example._Buzila_Andra_Court_Reserve_Backend.services.CourtService;
import com.example._Buzila_Andra_Court_Reserve_Backend.services.LocationService;
import com.example._Buzila_Andra_Court_Reserve_Backend.services.UserService;
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
import java.util.UUID;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

//Pentru resetare parola:
@RestController
@CrossOrigin
@RequestMapping(value = "/resetPassword")
public class ResetController
{
    private static final Logger log = LoggerFactory.getLogger(ResetController.class);

    private final UserService userService;

    @Autowired
    private RabbitSender rabbitSender;

//    @Autowired
//    private JavaMailSender javaMailSender;

//    @Autowired
//    Configuration fmConfiguration;

    //Controller:
    @Autowired
    public ResetController(UserService userService)
    {
        this.userService = userService;
    }

    //Post pentru trimitere email de refacere parola: SEND: Valid Request:
    @PostMapping(value = "/changePassword")
    public ResponseEntity sendEmailData(@Valid @RequestBody EmailDTO emailDTO)
    {
        //Sent to RMQ Email Data; (Coada asincrona)
        //Cu send pune in coada, in RMQ queue;
        //System.out.println("The email data " + emailDTO + " was sent.");
        //System.out.println("The email data " + emailDTO.getEmail() + " was sent.");

        //Pentru JSon Property:
        EmailDTO rmqEmailDTO = new EmailDTO(emailDTO.getEmail());

        rabbitSender.send(rmqEmailDTO);

        //Trimis status:
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Trimiti email + parola + parola confirmare, le compari, daca sunt la fel, save new password, else return bad:
    @PostMapping(value = "/resetPasswordUser")
    public ResponseEntity<UUID> resetUserPassword(@Valid @RequestBody ResetPasswordDTO resetPasswordDTO)
    {
        //Find user by email:
        User user = userService.findEntityUserByEmail(resetPasswordDTO.getEmail());

        //Nu exista user:
        if(user == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        //Daca parolele sunt la fel, update la parola:
        if(resetPasswordDTO.getPassword().equals(resetPasswordDTO.getConfirmPassword()))
        {
            //Update parola:
            //New criptare parola:
            Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(32,64,1,15*1024,2);
            //Salvare in String parola:
            String encodedPassword = encoder.encode(resetPasswordDTO.getPassword());

            //Update in service:
            userService.updateUser(user, encodedPassword);
        }
        else
        {
            //Parolele nu sunt la fel, return not found:
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        //Return user id if corect:
        return new ResponseEntity<UUID>(user.getId(), HttpStatus.OK);
    }
}
