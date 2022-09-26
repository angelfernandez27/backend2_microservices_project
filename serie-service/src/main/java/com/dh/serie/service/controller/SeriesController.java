package com.dh.serie.service.controller;


import com.dh.serie.service.model.Serie;
import com.dh.serie.service.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping
public class SeriesController {

    private final SeriesService seriesService;
    @Autowired
    public SeriesController(SeriesService seriesService){
        this.seriesService = seriesService;
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        List<Serie> series = seriesService.findAll();
        return series.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(series);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id){
        Serie serie = seriesService.findById(id);
        return Objects.isNull(serie)
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(serie);
    }

    @PostMapping
    public ResponseEntity<?> saveSeries(@RequestBody Serie serie){
        return ResponseEntity.ok(seriesService.saveSeries(serie));
    }

}
