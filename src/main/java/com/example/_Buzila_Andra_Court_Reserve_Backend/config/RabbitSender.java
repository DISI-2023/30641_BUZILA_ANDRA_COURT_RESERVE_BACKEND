package com.example._Buzila_Andra_Court_Reserve_Backend.config;

import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.EmailDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitSender
{
    @Autowired
    private AmqpTemplate rabbitTemplate;

    private static final Logger log = LoggerFactory.getLogger(RabbitSender.class);

    @Value("my.exchange")
    private String exchange;

    @Value("my.routingkey")
    private String routingKey;

    //Send data:
    public void send(EmailDTO payloadEmailDataIn)
    {
        rabbitTemplate.convertAndSend(exchange, routingKey, payloadEmailDataIn);

        //rabbitTemplate.convertAndSend(exchange, routingKey, payloadEmailDataIn.toString());
        //rabbitTemplate.convertAndSend(exchange, routingKey, payloadEmailDataIn.getEmail());
        //rabbitTemplate.send(exchange, routingKey, payloadEmailDataIn.getEmail());

        //log.info("The email data " + payloadEmailDataIn + " was sent.");
        //System.out.println("The email data " + payloadEmailDataIn + " was sent.");
    }
}
