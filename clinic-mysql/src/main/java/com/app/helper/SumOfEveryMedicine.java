package com.app.helper;

import java.math.BigDecimal;

public class SumOfEveryMedicine {

    private String name;
    private BigDecimal amount;
    private double price;

    public SumOfEveryMedicine() {
    }

    public SumOfEveryMedicine(String name, BigDecimal amount, double price) {
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public SumOfEveryMedicine(String name, BigDecimal amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "SumOfEveryMedicine{" +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                '}';
    }
}
