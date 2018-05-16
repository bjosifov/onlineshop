package com.example.boyanyosifov.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.boyanyosifov.myapplication.com.online.shop.adapters.ShopRecyclerViewAdapter;
import com.example.boyanyosifov.myapplication.com.online.shop.business.logic.ManagerFactory;
import com.example.boyanyosifov.myapplication.com.online.shop.repository.DataManager;
import com.example.boyanyosifov.myapplication.com.online.shop.repository.Laptop;
import com.example.boyanyosifov.myapplication.com.online.shop.repository.Phone;
import com.example.boyanyosifov.myapplication.com.online.shop.repository.PhoneContentProvider;
import com.example.boyanyosifov.myapplication.com.online.shop.repository.Product;
import com.example.boyanyosifov.myapplication.com.online.shop.utils.SpacesItemDecoration;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StoreActivity extends AppCompatActivity{

    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        recyclerView = findViewById(R.id.product_list);

        recyclerView = (RecyclerView)findViewById(R.id.product_list);
        GridLayoutManager mGrid = new GridLayoutManager(StoreActivity.this, 1);
        recyclerView.setLayoutManager(mGrid);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new SpacesItemDecoration(2, 12, false));

        ShopRecyclerViewAdapter shopAdapter = new ShopRecyclerViewAdapter(StoreActivity.this, getAllProductsOnSale());
        recyclerView.setAdapter(shopAdapter);

    }

    public void navigateToProductAct(){
        Intent intent = new Intent(getApplicationContext(), ProductActivity.class);
        //intent.putExtra("product_data", laptop);
        //intent.putExtra("product_type", laptop);
        startActivity(intent);
    }

    private List<Product> getAllProductsOnSale(){
        List<Product> products = new ArrayList<>();
        products.addAll(ManagerFactory.getPhonesManager(StoreActivity.this).getAll());
        products.addAll(ManagerFactory.getLaptopManager(StoreActivity.this).getAll());

       return products;
    }


}

