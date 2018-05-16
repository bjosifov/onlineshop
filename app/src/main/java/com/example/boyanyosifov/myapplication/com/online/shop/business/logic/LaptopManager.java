package com.example.boyanyosifov.myapplication.com.online.shop.business.logic;

import android.content.Context;

import com.example.boyanyosifov.myapplication.com.online.shop.repository.Database;
import com.example.boyanyosifov.myapplication.com.online.shop.repository.Laptop;
import com.example.boyanyosifov.myapplication.com.online.shop.repository.LaptopContentProvider;

import java.util.List;

public class LaptopManager implements ILaptopManager {
    private LaptopContentProvider laptopContentProvider;

    public LaptopManager(Context context) {
        laptopContentProvider = Database.getInstance(context).getLaptopDbContentProvider();
    }

    @Override
    public List<Laptop> getAll() {
        return laptopContentProvider.getAll();
    }
}
