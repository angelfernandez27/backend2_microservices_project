package com.dh.serie.service.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConsumerConfig {

    @Value("${queue.series.name}")
    private String queueSeriesName;


    @Bean
    public Queue queueSeries(){
        return new Queue(this.queueSeriesName, true);
    }
}
