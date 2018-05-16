package com.example.boyanyosifov.myapplication.com.online.shop.repository;

import java.io.Serializable;

public class Product implements Serializable{
    private static final long INVALID_ID = 0;
    private long id;
    private String manufacturer;
    private String model;
    private String description;
    private double price;

    public Product(){}

    public Product(long id, String manufacturer, String model, String description, double price) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.description = description;
        this.price = price;
    }

    public Product(String manufacturer, String model, String description, double price) {
        this.id = INVALID_ID;
        this.manufacturer = manufacturer;
        this.model = model;
        this.description = description;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
