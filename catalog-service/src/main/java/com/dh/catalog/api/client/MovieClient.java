package com.dh.catalog.api.client;


import com.dh.catalog.domain.dto.MovieDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "movie-service")
public interface MovieClient {

    @GetMapping("/movies/{genre}")
    ResponseEntity<List<MovieDTO>> getMovieByGenre(@PathVariable(value = "genre") String genre);

    @GetMapping("/movies/withErrors/{genre}")
    ResponseEntity<List<MovieDTO>> getMovieByGenreWithThrowError(@PathVariable(value = "genre") String genre,
                                                                 @RequestParam("throwError") boolean throwError);

}
