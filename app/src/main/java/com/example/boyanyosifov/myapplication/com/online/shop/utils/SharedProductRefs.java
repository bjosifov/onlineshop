package com.example.boyanyosifov.myapplication.com.online.shop.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.boyanyosifov.myapplication.com.online.shop.repository.Product;

public class SharedProductRefs{
    private SharedPreferences prefs;

    private Context context;

    public SharedProductRefs(Context context) {
        this.context = context;
        prefs = context.getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE);
    }

    public void addProductToTheCart(String product){
        SharedPreferences.Editor edits = prefs.edit();
        edits.putString(Constants.PRODUCT_ID, product);
        edits.apply();
    }

    public String retrieveProductFromCart(){
        return prefs.getString(Constants.PRODUCT_ID, "");
    }

    public void addProductCount(int productCount){
        SharedPreferences.Editor edits = prefs.edit();
        edits.putInt(Constants.PRODUCT_COUNT, productCount);
        edits.apply();
    }
    public int retrieveProductCount(){
        return prefs.getInt(Constants.PRODUCT_COUNT, 0);
    }
}
