package com.app.repository;

import com.app.model.PharmaceuticalCompany;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PharmaceuticalCompanyRepository extends MongoRepository<PharmaceuticalCompany, String> {

}

