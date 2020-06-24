package com.app.repository;

import com.app.model.PrescriptionItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PrescriptionItemRepository extends MongoRepository<PrescriptionItem, String> {

}
