package com.dh.movie.service.api.service;

import com.dh.movie.service.domain.models.Movie;
import com.dh.movie.service.domain.repositories.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private static final Logger LOG = LoggerFactory.getLogger(MovieService.class);
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> findByGenre(String genre) {
        LOG.info("Find Movie by Genre " + genre);
        return movieRepository.findByGenre(genre);
    }


    public List<Movie> findByGenre(String genre, Boolean throwError) {
        LOG.info("Find Movie by Genre " + genre);
        if (throwError) {
            LOG.error("Error in find Movie by Genre " + genre);
            throw new RuntimeException("Error forzado");
        }
        return movieRepository.findByGenre(genre);
    }

    @RabbitListener(queues = "${queue.movie.name}")
    public Movie saveMovie(Movie movie) {
        LOG.info("Save movie via RabbitMQ " + movie.toString());
        return movieRepository.save(movie);
    }

}
