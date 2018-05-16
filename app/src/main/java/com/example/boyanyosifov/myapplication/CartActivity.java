package com.example.boyanyosifov.myapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.boyanyosifov.myapplication.com.online.shop.adapters.CheckRecyclerViewAdapter;
import com.example.boyanyosifov.myapplication.com.online.shop.repository.Product;
import com.example.boyanyosifov.myapplication.com.online.shop.utils.SimpleItemDecor;
import com.example.boyanyosifov.myapplication.com.online.shop.utils.SharedProductRefs;
import com.example.boyanyosifov.myapplication.com.online.shop.utils.SimpleItemDecor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView checkRecyclerView;

    private TextView subTotal;

    private double mSubTotal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Button checkButton = (Button)findViewById(R.id.checkout);
        Button shoppingButton = (Button)findViewById(R.id.shopping);

        checkButton.setEnabled(true);

        subTotal = (TextView )findViewById(R.id.sub_total);

        checkRecyclerView = (RecyclerView)findViewById(R.id.checkout_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CartActivity.this);
        checkRecyclerView.setLayoutManager(linearLayoutManager);
        checkRecyclerView.setHasFixedSize(true);
        checkRecyclerView.addItemDecoration(new SimpleItemDecor(CartActivity.this));

        // get content of cart
        try {
            SharedProductRefs mShared = new SharedProductRefs(CartActivity.this);

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            Product[] addCartProducts = gson.fromJson(mShared.retrieveProductFromCart(), Product[].class);

            List<Product> productList = convertObjectArrayToListObject(addCartProducts);

            CheckRecyclerViewAdapter mAdapter = new CheckRecyclerViewAdapter(CartActivity.this, productList);
            checkRecyclerView.setAdapter(mAdapter);

            mSubTotal = getTotalPrice(productList);
            subTotal.setText("Subtotal excluding tax and shipping: " + String.valueOf(mSubTotal) + " $");
        }catch (NullPointerException ex){
            System.out.println(ex.getStackTrace());

            checkButton.setEnabled(false);
            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
            dlgAlert.setMessage("Cart is empty");
            dlgAlert.setTitle("Cart");
            dlgAlert.setPositiveButton("OK", null);
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
        }


        assert shoppingButton != null;
        shoppingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shoppingIntent = new Intent(CartActivity.this, StoreNavigationActivity.class);
                startActivity(shoppingIntent);
            }
        });


        assert checkButton != null;
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent paymentIntent = new Intent(CartActivity.this, PayPalCheckoutActivity.class);
                paymentIntent.putExtra("TOTAL_PRICE", mSubTotal);
                startActivity(paymentIntent);
            }
        });


    }

    private List<Product> convertObjectArrayToListObject(Product[] allProducts){
        List<Product> mProduct = new ArrayList<Product>();
        Collections.addAll(mProduct, allProducts);
        return mProduct;
    }

    private double getTotalPrice(List<Product> mProducts){
        double totalCost = 0;
        for(int i = 0; i < mProducts.size(); i++){
            Product pObject = mProducts.get(i);
            totalCost = totalCost + pObject.getPrice();
        }
        return totalCost;
    }
}
