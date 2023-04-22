package com.example._Buzila_Andra_Court_Reserve_Backend.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Configuration
public class AMQPConfig
{
    @Value("my.queue")
    private String queueName;

    @Value("my.exchange")
    private String exchange;

    @Value("my.routingkey")
    private String routingKey;

    @Bean
    Queue queue()
    {
        return new Queue(queueName, false);
    }

    @Bean
    DirectExchange exchange()
    {
        return new DirectExchange(exchange);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange)
    {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

    //Converter:
    @Bean
    public MessageConverter jsonMessageConverter()
    {
        ObjectMapper objectMapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory)
    {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }


}
