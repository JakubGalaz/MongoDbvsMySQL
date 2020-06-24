package com.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Doctor extends Person{

    @Id
    private String id;

    private String specialization;

    public Doctor(){
    }

    public Doctor(String firstName, String lastName, String peselNumber, String email, Address address, String specialization) {
        super(firstName, lastName, peselNumber, email, address);
        this.specialization = specialization;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id='" + id + '\'' +
                ", specialization='" + specialization + '\'' +
                '}';
    }
}
