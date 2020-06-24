package com.app.repository;

import com.app.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,Integer> {

    Doctor findFirstById(Integer id);
}
