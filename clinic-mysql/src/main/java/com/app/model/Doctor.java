/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Wojtek
 */
@Entity
@Table(name = "doctor")
@XmlRootElement

public class Doctor extends Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Size(max = 45)
    @Column(name = "specialization")
    private String specialization;
    @OneToMany(mappedBy = "idDoctor")
    private List<MedicalVisit> medicalVisitList;

    public Doctor() {
        super();
    }

    public Doctor(String firstName, String lastName, String peselNumber,String email, Address address, String specialization) {
        super(firstName, lastName, peselNumber, email, address);
        this.specialization = specialization;
    }


    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @XmlTransient
    public List<MedicalVisit> getMedicalVisitList() {
        return medicalVisitList;
    }

    public void setMedicalVisitList(List<MedicalVisit> medicalVisitList) {
        this.medicalVisitList = medicalVisitList;
    }

    
}
