package com.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Medicine {

    @Id
    private String id;

    private String name;
    private String disease;
    private double price;

    private PharmaceuticalCompany pharmaceuticalCompany;

    public Medicine() {
    }

    public Medicine(String name, String disease, double price, PharmaceuticalCompany pharmaceuticalCompany) {
        this.name = name;
        this.disease = disease;
        this.price = price;
        this.pharmaceuticalCompany = pharmaceuticalCompany;
    }

    public Medicine(String name, String disease) {
        this.name = name;
        this.disease = disease;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public PharmaceuticalCompany getPharmaceuticalCompany() {
        return pharmaceuticalCompany;
    }

    public void setPharmaceuticalCompany(PharmaceuticalCompany pharmaceuticalCompany) {
        this.pharmaceuticalCompany = pharmaceuticalCompany;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", disease='" + disease + '\'' +
                ", price=" + price +
                ", pharmaceuticalCompany=" + pharmaceuticalCompany +
                '}';
    }
}
