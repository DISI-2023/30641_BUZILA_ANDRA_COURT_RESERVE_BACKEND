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
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.InternetAddress;
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
    public void sendEmailTemplate(Email email)
    {
        //Pachet pentru trimis mail:
        MimeMessage mimeMessage =javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setSubject(email.getSubject());
            //mimeMessageHelper.setFrom(new InternetAddress(email.getFrom()));
            mimeMessageHelper.setFrom(email.getFrom());
            mimeMessageHelper.setTo(email.getTo());

            email.setContent(geContentFromTemplate(email.getModel()));
            mimeMessageHelper.setText(email.getContent(), true);

            //Test:
            //System.out.println("From: " + email.getFrom());
            //System.out.println("To: " + email.getTo());
            //System.out.println("Subject: " + email.getSubject());
            //System.out.println("Content: " + email.getContent()); //Este HTML;
            //System.out.println("From: " + mimeMessageHelper.getMimeMessage().getFrom().toString());
            //System.out.println("To: " + mimeMessageHelper.getMimeMessage().getReplyTo().toString());
            //System.out.println("Subject: " + mimeMessageHelper.getMimeMessage().getSubject());
            //System.out.println("Content: " + mimeMessageHelper.getMimeMessage().getContent());

            javaMailSender.send(mimeMessageHelper.getMimeMessage());

            //Daca nu ajunge aici, infinite loop;
            //System.out.println("P2;");

        } catch (javax.mail.MessagingException e) {
            System.out.println("Error at send email.");
            e.printStackTrace();
        }
    }

    //Content:
    public String geContentFromTemplate(Map< String, Object > model)
    {
        //StringWriter stringWriter = new StringWriter();
        StringBuffer content = new StringBuffer();

        try {
            //From templates:
            //1)
            //Template template = fmConfiguration.getTemplate("resetPasswordEmail.html");
            //template.process(model, stringWriter);
            //2)
            content.append(FreeMarkerTemplateUtils.
                    processTemplateIntoString(fmConfiguration.
                            getTemplate("resetPasswordEmail.html"), model));

        } catch (Exception e) {
            e.printStackTrace();
        }

        //1)
        //return stringWriter.toString();
        //2)
        return content.toString();
    }

    //Queue rabbit: listening for messages:
    //Daca sunt elemente in lista, atunci le asculta;
    @RabbitListener(queues = "my.queue")
    //String vs Object;
    //public void listen(String payloadEmailData) throws JsonProcessingException
    public void listen(EmailDTO payloadEmailData) throws JsonProcessingException
    {
        //Get the email data:
        //ObjectMapper objectMapper = new ObjectMapper();
        //EmailDTO emailDTO = objectMapper.readValue(payloadEmailData, EmailDTO.class);
        EmailDTO emailDTO = new EmailDTO(payloadEmailData.getEmail());
        //String emailDTO = payloadEmailData;

        //Mesaj:
        //log.info("Email data primit: " + emailDTO);
        System.out.println("Email data primit: " + emailDTO);

        //To / From / Subject;
        Email email = new Email();
        email.setTo(emailDTO.getEmail()); //Sent to;
        //email.setTo(emailDTO);
        email.setFrom("Administrator@yahoo.com");
        email.setSubject("Reset Password Request");

        //Map data:
        String emailData = emailDTO.getEmail();
        //String emailData = emailDTO;

        //Name from email:
        int indexA = emailData.indexOf("@");
        String emailName = emailData.substring(0, indexA);
        //System.out.println("Email name: " + emailName);

        //Trimitere name email:
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("emailName", emailName);
        //map.put("email", emailName);
        //map.put("email", email);

        email.setModel(map);
        sendEmailTemplate(email);

        //rabbitMQ.purgeQueue("queueName", false);
        //System.out.println("P1;");
    }
}