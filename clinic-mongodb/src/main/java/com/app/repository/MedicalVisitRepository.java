package com.app.repository;

import com.app.model.MedicalVisit;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MedicalVisitRepository extends MongoRepository<MedicalVisit, String> {
    MedicalVisit findFirstById(String id);
}
