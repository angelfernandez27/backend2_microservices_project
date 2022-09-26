package com.dh.serie.service.repository;

import com.dh.serie.service.model.Serie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeriesRepository extends MongoRepository<Serie, String> {
}
