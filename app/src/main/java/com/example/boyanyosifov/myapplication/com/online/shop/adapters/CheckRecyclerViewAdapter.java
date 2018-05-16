package com.example.boyanyosifov.myapplication.com.online.shop.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.boyanyosifov.myapplication.CartActivity;
import com.example.boyanyosifov.myapplication.R;
import com.example.boyanyosifov.myapplication.com.online.shop.repository.Product;
import com.example.boyanyosifov.myapplication.com.online.shop.utils.Constants;
import com.example.boyanyosifov.myapplication.com.online.shop.utils.SharedProductRefs;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheckRecyclerViewAdapter extends RecyclerView.Adapter<CheckRecyclerViewHolder>{
    private Context context;

    private List<Product> mProductObject;


    public CheckRecyclerViewAdapter(Context context, List<Product> mProductObject) {
        this.context = context;
        this.mProductObject = mProductObject;
    }

    @Override
    public CheckRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        try{
            View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_layout, parent, false);
            CheckRecyclerViewHolder productHolder = new CheckRecyclerViewHolder(layoutView);
            return productHolder;
        }
        catch (InflateException ex){
            System.out.println(ex.getStackTrace());
            return null;
        }
    }

    @Override
    public void onBindViewHolder(CheckRecyclerViewHolder holder, final int position) {
        //get product quantity
        holder.quantity.setText("1");
        holder.productName.setText(mProductObject.get(position).getModel());
        holder.productPrice.setText(String.valueOf(mProductObject.get(position).getPrice()) + " $");
        holder.removeProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, "Do you want to remove product from cart", Toast.LENGTH_LONG).show();
                try {
                    mProductObject.remove(mProductObject.get(position));
                    notifyItemRemoved(position);
                    notifyItemRangeRemoved(0, getItemCount());

                    SharedProductRefs mShared = new SharedProductRefs(context);

                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();

                    Product[] addCartProducts = gson.fromJson(mShared.retrieveProductFromCart(), Product[].class);
                    List<Product> productList = convertObjectArrayToListObject(addCartProducts);
                    productList.remove(position);
                    String newProducts = gson.toJson(productList);
                    mShared.addProductToTheCart(newProducts);
                }catch (IndexOutOfBoundsException ex){
                    System.out.println(ex.getStackTrace());
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return mProductObject.size();
    }


    private List<Product> convertObjectArrayToListObject(Product[] allProducts){
        List<Product> mProduct = new ArrayList<Product>();
        Collections.addAll(mProduct, allProducts);
        return mProduct;
    }
}
