package com.app.helper;

public class SumOfEveryMedicine {

    private String name;
    private double amount;
    private double price;

    public SumOfEveryMedicine() {
    }

    public SumOfEveryMedicine(String name, double amount, double price) {
        this.name = name;
        this.amount = amount;
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
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
