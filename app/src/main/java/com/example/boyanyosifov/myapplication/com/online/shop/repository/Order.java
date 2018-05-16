package com.example.boyanyosifov.myapplication.com.online.shop.repository;

public class Order {
    private static final long INVALID_ID = -1;
    private long Id;
    private ProductType productType;
    private long userId;
    private long productId;

    public Order(long id, ProductType productType, long userId, long productId) {
        Id = id;
        this.productType = productType;
        this.userId = userId;
        this.productId = productId;
    }

    public Order(ProductType productType, long userId, long productId) {
        Id = INVALID_ID;
        this.productType = productType;
        this.userId = userId;
        this.productId = productId;
    }

    public Order() {

    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long  getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
