package com.example.boyanyosifov.myapplication.com.online.shop.repository;


import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.boyanyosifov.myapplication.R;

import java.io.Serializable;
import java.util.Base64;

public class User extends BaseObservable implements Serializable {
    private static final long INVALID_ID = 0;
    private long id;
    private String username;
    private String password;
    private String address;

    public User(){

    }

    public User(long id, String username, String password, String address) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.address = address;
    }

    public User(String username, String password, String address) {
        this.id = INVALID_ID;
        this.username = username;
        this.password = password;
        this.address = address;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Bindable
    public void setUsername(String username) {
        this.username = username;
        notifyPropertyChanged(R.id.editText_username);
    }

    public String getPassword() {
        return password;
    }
    @Bindable
    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(R.id.editText_password);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        notifyPropertyChanged(R.id.editText_email);
    }
}
