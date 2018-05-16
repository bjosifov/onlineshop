package com.example.boyanyosifov.myapplication.com.online.shop.business.logic;

import android.content.Context;

import com.example.boyanyosifov.myapplication.com.online.shop.repository.Database;
import com.example.boyanyosifov.myapplication.com.online.shop.repository.Phone;
import com.example.boyanyosifov.myapplication.com.online.shop.repository.PhoneContentProvider;

import java.util.List;

public class PhonesManager implements IPhonesManager {
    private PhoneContentProvider phoneContentProvider;
    public PhonesManager(Context context) {
        phoneContentProvider =  Database.getInstance(context).getPhoneDbContentProvider();
    }

    @Override
    public List<Phone> getAll() {
        return phoneContentProvider.getAll();
    }
}
