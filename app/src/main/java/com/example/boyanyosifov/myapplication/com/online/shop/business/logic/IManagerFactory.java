package com.example.boyanyosifov.myapplication.com.online.shop.business.logic;

public interface IManagerFactory {
    IUsersManager getUsersManager();
    IPhonesManager getPhonesManager();
    ILaptopManager getLaptopManager();
}
