package com.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PharmaceuticalCompany {

    @Id
    private String id;

    private String companyName;
    private String nip;


    private Address address;

    public PharmaceuticalCompany() {
    }

    public PharmaceuticalCompany(String companyName, String nip, Address address) {
        this.companyName = companyName;
        this.nip = nip;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "PharmaceuticalCompany{" +
                "id='" + id + '\'' +
                ", companyName='" + companyName + '\'' +
                ", nip='" + nip + '\'' +
                ", address=" + address +
                '}';
    }
}
