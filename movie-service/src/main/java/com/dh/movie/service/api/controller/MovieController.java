package com.dh.movie.service.api.controller;

import com.dh.movie.service.api.service.MovieService;
import com.dh.movie.service.domain.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {


    private final MovieService movieService;
    @Autowired
    public MovieController(MovieService movieService){
        this.movieService = movieService;
    }

    @GetMapping("/{genre}")
    ResponseEntity<List<Movie>> getMovieByGenre(@PathVariable String genre){
        return ResponseEntity.ok().body(movieService.findByGenre(genre));
    }

    @GetMapping("/withErrors/{genre}")
    ResponseEntity<List<Movie>> getMovieByGenre(@PathVariable String genre, @RequestParam("throwError") boolean throwError){
        return ResponseEntity.ok().body(movieService.findByGenre(genre, throwError));
    }

}
