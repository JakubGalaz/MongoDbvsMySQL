package com.app.repository;

import com.app.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person,Integer> {

    public Person findByFirstName(String firstName);
    public List<Person> findByLastName(String lastName);

}
