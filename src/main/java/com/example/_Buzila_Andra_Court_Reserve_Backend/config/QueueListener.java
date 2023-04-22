package com.example._Buzila_Andra_Court_Reserve_Backend.config;

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
import javax.mail.internet.MimeMessage;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

//Pentru primire email:
@Component
public class QueueListener
{
    private static final Logger log = LoggerFactory.getLogger(QueueListener.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    Configuration fmConfiguration;

    //For sending email template:
    public void sendEmailTemplate(Email email) {

        //Pachet pentru trimis mail:
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
            e.printStackTrace();
        }
    }

    //Content:
    public String geContentFromTemplate(Map< String, Object > model)
    {
        StringWriter stringWriter = new StringWriter();

        try {
            //From templates:
            Template template = fmConfiguration.getTemplate("resetPasswordEmail.html");
            template.process(model, stringWriter);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stringWriter.toString();
    }

    //Queue rabbit: listening for messages:
    @RabbitListener(queues = "my.queue")
    public void listen(String payloadEmailData) throws JsonProcessingException
    {
        System.out.println("Email data string: " + payloadEmailData);

        //Get the email data:
        ObjectMapper objectMapper = new ObjectMapper();
        EmailDTO emailDTO = objectMapper.readValue(payloadEmailData, EmailDTO.class);

        //Mesaj:
        log.info("Email data primit: " + emailDTO);

        //To / From / Subject;
        Email email = new Email();
        email.setTo(emailDTO.getEmail()); //Sent to;
        email.setFrom("Administrator@yahoo.com");
        email.setSubject("Reset Password Request");

        //Map data:
        String emailData = emailDTO.getEmail();

        //Name from email:
        int indexA = emailData.indexOf("@");
        String emailName = emailData.substring(0, indexA - 1);

        //Trimitere name email:
        Map<String, Object> map = new HashMap<String, Object>();
        //map.put("emailName", emailName);
        map.put("email", emailName);

        email.setModel(map);
        sendEmailTemplate(email);
    }
}