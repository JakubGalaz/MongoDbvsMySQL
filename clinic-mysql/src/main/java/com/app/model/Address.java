/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.model;

import javax.persistence.*;
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
@Table(name = "address")
@XmlRootElement

public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "city")
    private String city;
    @Size(max = 45)
    @Column(name = "street")
    private String street;
    @Size(max = 45)
    @Column(name = "house_number")
    private String houseNumber;
    @Size(max = 45)
    @Column(name = "apartment_number")
    private String apartmentNumber;
    @Size(max = 45)
    @Column(name = "postal_code")
    private String postalCode;
    @Size(max = 45)
    @Column(name = "postal_city")
    private String postalCity;
    @OneToMany(mappedBy = "idAddress")
    private List<PharmaceuticalCompany> pharmaceuticalCompanyList;
    @OneToMany(mappedBy = "address")
    private List<Person> persons;
    @OneToMany(mappedBy = "idAddress")
    private List<OutpatientClinic> outpatientClinics;

    public Address() {
    }

    public Address(String city, String street, String houseNumber, String apartmentNumber, String postalCode, String postalCity) {
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.apartmentNumber = apartmentNumber;
        this.postalCode = postalCode;
        this.postalCity = postalCity;
    }


    public Address(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPostalCity() {
        return postalCity;
    }

    public void setPostalCity(String postalCity) {
        this.postalCity = postalCity;
    }

    @XmlTransient
    public List<PharmaceuticalCompany> getPharmaceuticalCompanyList() {
        return pharmaceuticalCompanyList;
    }

    public void setPharmaceuticalCompanyList(List<PharmaceuticalCompany> pharmaceuticalCompanyList) {
        this.pharmaceuticalCompanyList = pharmaceuticalCompanyList;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public List<OutpatientClinic> getOutpatientClinics() {
        return outpatientClinics;
    }

    public void setOutpatientClinics(List<OutpatientClinic> outpatientClinics) {
        this.outpatientClinics = outpatientClinics;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.app.model.Address[ id=" + id + " ]";
    }
    
}
