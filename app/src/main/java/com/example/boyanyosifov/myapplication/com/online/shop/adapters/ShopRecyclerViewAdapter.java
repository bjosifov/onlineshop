package com.example.boyanyosifov.myapplication.com.online.shop.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.boyanyosifov.myapplication.ProductActivity;
import com.example.boyanyosifov.myapplication.R;
import com.example.boyanyosifov.myapplication.StoreActivity;
import com.example.boyanyosifov.myapplication.com.online.shop.repository.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class ShopRecyclerViewAdapter extends RecyclerView.Adapter<ShopRecyclerViewHolder>{

    private Context context;

    private List<Product> allProducts;

    public ShopRecyclerViewAdapter(Context context, List<Product> allProducts) {
        this.context = context;
        this.allProducts = allProducts;
    }

    @NonNull
    @Override
    public ShopRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_listing, parent, false);
        ShopRecyclerViewHolder productHolder = new ShopRecyclerViewHolder(layoutView);
        return productHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShopRecyclerViewHolder holder, int position) {
        final Product singleProduct = allProducts.get(position);

        holder.productName.setText(singleProduct.getModel());

        if(singleProduct.getModel().contains("Samsung S6"))
            holder.produceImage.setImageResource(R.drawable.samsung_galaxy_s6);

        if(singleProduct.getModel().contains("Iphone 6"))
            holder.produceImage.setImageResource(R.drawable.apple_iphone_6s1);

        if(singleProduct.getModel().contains("Samsung Galaxy S8"))
            holder.produceImage.setImageResource(R.drawable.samsung_galaxy_s8);

        if(singleProduct.getModel().contains("Iphone X"))
            holder.produceImage.setImageResource(R.drawable.apple_iphone_x);

        if(singleProduct.getModel().contains("Lenovo K320t"))
            holder.produceImage.setImageResource(R.drawable.samsungs8);

        if(singleProduct.getModel().contains("Nokia 6"))
            holder.produceImage.setImageResource(R.drawable.nokia_6_2018);

        if(singleProduct.getModel().contains("Lenovo T540P"))
            holder.produceImage.setImageResource(R.drawable.lenovot540p);

        if(singleProduct.getModel().contains("Acer Aspire E5"))
            holder.produceImage.setImageResource(R.drawable.aceraspiree5);

        if(singleProduct.getModel().contains("Lenovo ThinkPad E480"))
            holder.produceImage.setImageResource(R.drawable.thinkpad_e480);

        if(singleProduct.getModel().contains("DELL XPS 15"))
            holder.produceImage.setImageResource(R.drawable.dell_xps_15);

        if(singleProduct.getModel().contains("HP Spectre x360"))
            holder.produceImage.setImageResource(R.drawable.hp_spectre_x360);

        if(singleProduct.getModel().contains("Acer Nitro 5"))
            holder.produceImage.setImageResource(R.drawable.nitro_5);



        holder.produceImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent productIntent = new Intent(context, ProductActivity.class);

                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();

                String productJson = gson.toJson(singleProduct);


                productIntent.putExtra("PRODUCT", productJson);
                context.startActivity(productIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allProducts.size();
    }
}
