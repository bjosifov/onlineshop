package com.example.boyanyosifov.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.boyanyosifov.myapplication.com.online.shop.business.logic.ManagerFactory;
import com.example.boyanyosifov.myapplication.com.online.shop.repository.User;
import com.example.boyanyosifov.myapplication.com.online.shop.utils.Constants;
import com.example.boyanyosifov.myapplication.com.online.shop.utils.Validator;
import com.example.boyanyosifov.myapplication.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;
    User user;
    SharedPreferences sharedPreferences;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        user = new User();
        mainBinding.setUser(user);
        sharedPreferences = getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();
        mainBinding.setMainEvent(new IMainEvent() {
            @Override
            public void onClickLogin() {
                //validation, login and navigate to store activity
                if (Validator.validateLogin(mainBinding.getUser().getUsername(), mainBinding.getUser().getPassword()) &&
                        ManagerFactory.getUsersManager(MainActivity.this).
                                    login(mainBinding.getUser().getUsername(), mainBinding.getUser().getPassword()) != null){

                    navigateToStoreAct();
                }
                else
                    Toast.makeText(MainActivity.this, "User not found", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onClickRegister() {
                //Navigate to register
                navigateToRegistrationAct();
            }
        });
    }

    public void navigateToStoreAct(){
        Intent intent = new Intent(MainActivity.this, StoreNavigationActivity.class);
        //intent.putExtra("user_data", user);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    public void navigateToRegistrationAct(){
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //preventing default implementation previous to android.os.Build.VERSION_CODES.ECLAIR
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
