package com.dh.catalog.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQSenderConfig {

    @Value("${queue.movie.name}")
    private String queueMovieName;

    @Value("${queue.series.name}")
    private String queueSerieName;

    @Bean
    public Queue queue() {
        return new Queue(this.queueMovieName, true);
    }

    @Bean
    public Queue queueSerie() {
        return new Queue(this.queueSerieName, true);
    }
}
