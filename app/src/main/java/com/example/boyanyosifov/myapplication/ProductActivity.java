package com.example.boyanyosifov.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.boyanyosifov.myapplication.com.online.shop.repository.Phone;
import com.example.boyanyosifov.myapplication.com.online.shop.repository.Product;
import com.example.boyanyosifov.myapplication.com.online.shop.utils.Constants;
import com.example.boyanyosifov.myapplication.com.online.shop.utils.SharedProductRefs;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductActivity extends AppCompatActivity {

   Product product;

    private TextView productManufacturer, productModel, productPrice, productDescription;

    private ImageView productImage;

    private Gson gson;

    private SharedProductRefs sharedPreference;

    private int cartProductNumber = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        sharedPreference = new SharedProductRefs(ProductActivity.this);

        productImage = (ImageView)findViewById(R.id.full_product_image);
        productManufacturer = (TextView)findViewById(R.id.product_manufacturer);
        productModel = (TextView)findViewById(R.id.product_model);
        productPrice = (TextView)findViewById(R.id.product_price);
        productDescription = (TextView)findViewById(R.id.product_description);


        try {
            GsonBuilder builder = new GsonBuilder();
            gson = builder.create();
            String productJson = getIntent().getExtras().getString("PRODUCT");
            product = gson.fromJson(productJson, Product.class);

            if(product != null) {


                if(product.getModel().contains("Samsung S6"))
                    productImage.setImageResource(R.drawable.samsung_galaxy_s6);

                if(product.getModel().contains("Iphone 6"))
                    productImage.setImageResource(R.drawable.apple_iphone_6s1);

                if(product.getModel().contains("Samsung Galaxy S8"))
                    productImage.setImageResource(R.drawable.samsung_galaxy_s8);

                if(product.getModel().contains("Iphone X"))
                    productImage.setImageResource(R.drawable.apple_iphone_x);

                if(product.getModel().contains("Lenovo K320t"))
                    productImage.setImageResource(R.drawable.samsungs8);

                if(product.getModel().contains("Nokia 6"))
                    productImage.setImageResource(R.drawable.nokia_6_2018);

                if(product.getModel().contains("Lenovo T540P"))
                    productImage.setImageResource(R.drawable.lenovot540p);

                if(product.getModel().contains("Acer Aspire E5"))
                    productImage.setImageResource(R.drawable.aceraspiree5);

                if(product.getModel().contains("Lenovo ThinkPad E480"))
                    productImage.setImageResource(R.drawable.thinkpad_e480);

                if(product.getModel().contains("DELL XPS 15"))
                    productImage.setImageResource(R.drawable.dell_xps_15);

                if(product.getModel().contains("HP Spectre x360"))
                    productImage.setImageResource(R.drawable.hp_spectre_x360);

                if(product.getModel().contains("Acer Nitro 5"))
                    productImage.setImageResource(R.drawable.nitro_5);


                productManufacturer.setText("Manufacturer: " + product.getManufacturer());
                productModel.setText("Model: " + product.getModel());
                productPrice.setText("Price: " + String.valueOf(new Double(product.getPrice()).intValue()) + " $");
                productDescription.setText("Description: \n " + product.getDescription());

            }
        }
        catch (RuntimeException ex){
            System.out.println(ex.getStackTrace());
        }

        Button addToCartButton = (Button)findViewById(R.id.add_to_cart);
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productsFromCart = sharedPreference.retrieveProductFromCart();

                if(productsFromCart.equals("")){
                    List<Product> cartProductList = new ArrayList<Product>();
                    cartProductList.add(product);
                    String cartValue = gson.toJson(cartProductList);
                    sharedPreference.addProductToTheCart(cartValue);
                    cartProductNumber = cartProductList.size();
                }else{
                    String productsInCart = sharedPreference.retrieveProductFromCart();
                    Product[] storedProducts = gson.fromJson(productsInCart, Product[].class);

                    List<Product> allNewProduct = convertObjectArrayToListObject(storedProducts);
                    allNewProduct.add(product);
                    String addAndStoreNewProduct = gson.toJson(allNewProduct);
                    sharedPreference.addProductToTheCart(addAndStoreNewProduct);
                    cartProductNumber = allNewProduct.size();
                }

                Toast.makeText(ProductActivity.this, "Item added to the cart", Toast.LENGTH_SHORT).show();
            }
        });



    }

    private List<Product> convertObjectArrayToListObject(Product[] storedProducts) {
        List<Product> mProduct = new ArrayList<Product>();
        Collections.addAll(mProduct, storedProducts);
        return mProduct;
    }
}
