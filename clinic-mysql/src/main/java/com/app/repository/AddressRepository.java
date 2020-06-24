package com.app.repository;

import com.app.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Integer> {

    public List<Address> findAllByCity(String city);

}
