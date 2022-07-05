package com.tbd.control5.backend;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

public interface InstitutionRepository extends MongoRepository<InstitutionModel, String> {
    List<InstitutionModel> findByName(@Param("name") String name);
}

