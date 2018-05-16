package com.example.boyanyosifov.myapplication.com.online.shop.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.boyanyosifov.myapplication.R;

public class CheckRecyclerViewHolder extends RecyclerView.ViewHolder {

    public TextView quantity, productName, productPrice, removeProduct;

    public CheckRecyclerViewHolder(View itemView) {
        super(itemView);

        quantity = (TextView)itemView.findViewById(R.id.quantity);
        productName =(TextView)itemView.findViewById(R.id.product_name_cart);
        productPrice = (TextView)itemView.findViewById(R.id.product_price_cart);
        removeProduct = (TextView)itemView.findViewById(R.id.remove_from_cart);
    }
}
