package com.app.repository;

import com.app.model.OutpatientClinic;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OutpatientClinicRepository extends MongoRepository<OutpatientClinic, String> {

}