package com.example.boyanyosifov.myapplication.com.online.shop.business.logic;

import android.content.Context;

import com.example.boyanyosifov.myapplication.com.online.shop.business.logic.IManagerFactory;

public final class ManagerFactory {

    public static final  IUsersManager getUsersManager(Context context) {
        return new UsersManager(context);
    }

    public static final IPhonesManager getPhonesManager(Context context) {
        return new PhonesManager(context);
    }


    public static final ILaptopManager getLaptopManager(Context context) {
        return new LaptopManager(context);
    }

    public static final IOrderManager getOrderManager(Context context) {
        return new OrderManager(context);
    }
}
