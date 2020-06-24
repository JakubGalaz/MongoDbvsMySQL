package com.app.repository;

import com.app.model.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AddressRepository extends MongoRepository<Address, String> {

    public List<Address> findAllByCity(String city);

}
