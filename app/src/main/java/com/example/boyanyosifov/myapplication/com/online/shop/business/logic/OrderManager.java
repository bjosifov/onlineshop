package com.example.boyanyosifov.myapplication.com.online.shop.business.logic;

import android.content.Context;

import com.example.boyanyosifov.myapplication.com.online.shop.repository.Database;
import com.example.boyanyosifov.myapplication.com.online.shop.repository.Order;
import com.example.boyanyosifov.myapplication.com.online.shop.repository.OrderContentProvider;
import com.example.boyanyosifov.myapplication.com.online.shop.repository.Product;
import com.example.boyanyosifov.myapplication.com.online.shop.repository.ProductType;
import com.example.boyanyosifov.myapplication.com.online.shop.repository.User;
import com.example.boyanyosifov.myapplication.com.online.shop.repository.UserContentProvider;

public class OrderManager implements IOrderManager {
    private Context context;
    private OrderContentProvider orderDbContentProvider;

    public OrderManager(Context context) {
        orderDbContentProvider = Database.getInstance(context).getOrderDbContentProvider();
    }

    @Override
    public Order createOrder( ProductType productType, Product product, User user) {
        return orderDbContentProvider.addEntity(new Order(productType, product.getId(), user.getId()));
    }

    @Override
    public boolean removeOrder(ProductType productType, Product product, User user) {
        return orderDbContentProvider.removeEntity(new Order());
    }
}
