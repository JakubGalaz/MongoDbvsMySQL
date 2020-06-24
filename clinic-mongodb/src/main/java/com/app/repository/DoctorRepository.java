package com.app.repository;

import com.app.model.Doctor;
import com.app.repository.custom.DoctorCustomRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DoctorRepository extends MongoRepository<Doctor, String>, DoctorCustomRepository {

    Doctor findFirstById(String id);
}
