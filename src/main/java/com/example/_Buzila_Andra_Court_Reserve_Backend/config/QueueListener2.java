package com.example._Buzila_Andra_Court_Reserve_Backend.config;

import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.AddReservationEmailDTO;
import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.EmailDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class QueueListener2 {
    private static final Logger log = LoggerFactory.getLogger(QueueListener.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    Configuration fmConfiguration;

    public void sendEmailTemplate(Email email)
    {
        MimeMessage mimeMessage =javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setSubject(email.getSubject());
            mimeMessageHelper.setFrom(email.getFrom());
            mimeMessageHelper.setTo(email.getTo());

            email.setContent(geContentFromTemplate(email.getModel()));
            mimeMessageHelper.setText(email.getContent(), true);

            javaMailSender.send(mimeMessageHelper.getMimeMessage());


        } catch (javax.mail.MessagingException e) {
            System.out.println("Error at send email.");
            e.printStackTrace();
        }
    }
    public String geContentFromTemplate(Map< String, Object > model)
    {
        StringBuffer content = new StringBuffer();

        try {
            content.append(FreeMarkerTemplateUtils.
                    processTemplateIntoString(fmConfiguration.
                            getTemplate("addReservationEmail.html"), model));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    @RabbitListener(queues = "my.queue2")
    public void listen(AddReservationEmailDTO payloadEmailData) throws JsonProcessingException
    {
        AddReservationEmailDTO emailDTO = new AddReservationEmailDTO(payloadEmailData.getEmail(),
                payloadEmailData.getCourtName(), payloadEmailData.getLocationAddress(), payloadEmailData.getArrivingTime(),
                payloadEmailData.getLeavingTime(), payloadEmailData.getPrice());

        System.out.println("Email data primit: " + emailDTO);

        Email email = new Email();
        email.setTo(emailDTO.getEmail());
        email.setFrom("Administrator@yahoo.com");
        email.setSubject("Add Reservation");

        String emailData = emailDTO.getEmail();

        int indexA = emailData.indexOf("@");
        String emailName = emailData.substring(0, indexA);

        //replace in data T cu spatiu
        String newArrivingDate = emailDTO.getArrivingTime().replace("T", ", ");
        String newLeavingDate = emailDTO.getLeavingTime().replace("T", ", ");

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("emailName", emailName);
        map.put("locationAddress", emailDTO.getLocationAddress());
        map.put("courtName", emailDTO.getCourtName());
        map.put("arrivingTime", newArrivingDate);
        map.put("leavingTime", newLeavingDate);
        map.put("price", emailDTO.getPrice());

        email.setModel(map);
        sendEmailTemplate(email);
    }
}
