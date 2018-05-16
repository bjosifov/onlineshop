package com.example.boyanyosifov.myapplication.com.online.shop.business.logic;

import com.example.boyanyosifov.myapplication.com.online.shop.repository.Order;
import com.example.boyanyosifov.myapplication.com.online.shop.repository.Product;
import com.example.boyanyosifov.myapplication.com.online.shop.repository.ProductType;
import com.example.boyanyosifov.myapplication.com.online.shop.repository.User;

public interface IOrderManager {
    Order createOrder(ProductType productType, Product product, User user);
    boolean removeOrder(ProductType productType, Product product, User user);
}
