package com.example._Buzila_Andra_Court_Reserve_Backend.config;

import com.example._Buzila_Andra_Court_Reserve_Backend.dtos.AddReservationEmailDTO;
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
public class RabbitSender2 {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    private static final Logger log = LoggerFactory.getLogger(RabbitSender2.class);

    @Value("my.exchange2")
    private String exchange;

    @Value("my.routingkey2")
    private String routingKey;

    //Send data:
    public void send(AddReservationEmailDTO payloadEmailDataIn)
    {
        rabbitTemplate.convertAndSend(exchange, routingKey, payloadEmailDataIn);
    }
}
