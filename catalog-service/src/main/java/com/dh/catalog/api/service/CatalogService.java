package com.dh.catalog.api.service;

import com.dh.catalog.api.client.MovieClient;
import com.dh.catalog.domain.dto.MovieDTO;
import com.dh.catalog.domain.dto.SerieDTO;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogService {

    @Value("${queue.movie.name}")
    private String queueName;

    @Value("${queue.series.name}")
    private String queueSeriesName;

    private final RabbitTemplate rabbitTemplate;

    private final Logger LOG = LoggerFactory.getLogger(CatalogService.class);
    private final MovieClient movieClient;

    @Autowired
    public CatalogService(MovieClient movieClient, RabbitTemplate rabbitTemplate) {
        this.movieClient = movieClient;
        this.rabbitTemplate = rabbitTemplate;
    }

    public ResponseEntity<List<MovieDTO>> findMovieByGenre(String genre) {
        LOG.info("Find movie by Genre " + genre);
        return movieClient.getMovieByGenre(genre);
    }


    @CircuitBreaker(name = "movies", fallbackMethod = "moviesFallBackMethod")
    public ResponseEntity<List<MovieDTO>> findMovieByGenre(String genre, Boolean throwError) {
        LOG.info("Find movie by Genre " + genre);
        return movieClient.getMovieByGenreWithThrowError(genre, throwError);
    }

    //metodo de fallback
    public ResponseEntity<List<MovieDTO>> moviesFallBackMethod(CallNotPermittedException exception) {
        LOG.info("Error. Circuit Breaker Activated");
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    public void saveMovie(MovieDTO movieDTO) {
        rabbitTemplate.convertAndSend(queueName, movieDTO);
    }

    public void saveSeries(SerieDTO serieDTO) {
        rabbitTemplate.convertAndSend(queueSeriesName, serieDTO);
    }

}
