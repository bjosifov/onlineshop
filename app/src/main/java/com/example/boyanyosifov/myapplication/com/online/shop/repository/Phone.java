package com.example.boyanyosifov.myapplication.com.online.shop.repository;

import java.io.Serializable;

public class Phone extends Product implements Serializable{


    public Phone(long id, String manufacturer, String model, double price, String description) {
        super(id,manufacturer,model, description,price);
    }

    public Phone() {

    }

    public Phone(String manufacturer, String model, String description, double price){
        super(manufacturer, model, description, price);
    }
    public String getManufacturer() {
        return super.getManufacturer();
    }

    public void setManufacturer(String manufacturer) {
        super.setManufacturer(manufacturer);
    }

    public String getModel() {
        return super.getModel();
    }

    public void setModel(String model) {
        super.setModel(model);
    }

    public String getDescription() {
        return super.getDescription();
    }

    public void setDescription(String description) {
        super.setDescription(description);
    }

    public double getPrice() {
        return super.getPrice();
    }

    public void setPrice(double price) {
        super.setPrice(price);
    }


    public long getId() {
        return super.getId();
    }


    public void setId(long id) {
        super.setId(id);
    }
}
