package com.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Patient extends Person {

    @Id
    private String id;

    private String telephoneNumber;


    public Patient() {
    }

    public Patient(String firstName, String lastName, String peselNumber, String email, Address address, String telephoneNumber) {
        super(firstName, lastName, peselNumber, email, address);
        this.telephoneNumber = telephoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id='" + id + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                '}';
    }
}
