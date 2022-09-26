package com.dh.movie.service.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConsumerConfig {


    @Value("${queue.movie.name}")
    private String queueMovieName;

    @Bean
    public Queue queue() {
        return new Queue(this.queueMovieName, true);
    }


}
