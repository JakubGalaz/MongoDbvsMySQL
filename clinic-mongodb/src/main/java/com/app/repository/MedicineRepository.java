package com.app.repository;

import com.app.model.Medicine;
import com.app.repository.custom.MedicineCustomRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MedicineRepository extends MongoRepository<Medicine, String>, MedicineCustomRepository {

    Medicine findFirstById(String id);
}
