package com.app.model;

import org.springframework.data.annotation.Id;

public class PrescriptionItem {

    @Id
    private String id;

    private Medicine medicine;

    private double amount;

    public PrescriptionItem() {
    }

    public PrescriptionItem(Medicine medicine, double amount) {
        this.medicine = medicine;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "PrescriptionItem{" +
                "id='" + id + '\'' +
                ", medicine=" + medicine +
                ", amount=" + amount +
                '}';
    }
}
