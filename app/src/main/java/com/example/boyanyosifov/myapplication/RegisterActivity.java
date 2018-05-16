package com.example.boyanyosifov.myapplication;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.boyanyosifov.myapplication.com.online.shop.business.logic.ManagerFactory;
import com.example.boyanyosifov.myapplication.com.online.shop.repository.User;
import com.example.boyanyosifov.myapplication.com.online.shop.utils.Validator;
import com.example.boyanyosifov.myapplication.databinding.ActivityRegisterBinding;


public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding registerBinding;
    User newUser;
    EditText editText, register_password_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        editText = (EditText)findViewById(R.id.editText_email);
        newUser = new User();
       registerBinding.setNewuser(newUser);


       registerBinding.setRegisterevent(new IRegisterEvent() {
           @Override
           public void onClickRegister() {
               //validation and add user to db
               if(Validator.validateRegistration(registerBinding.getNewuser().getUsername(), registerBinding.getNewuser().getPassword(),
                                                    registerBinding.getNewuser().getAddress())
                       && ManagerFactory.getUsersManager(RegisterActivity.this).
                                        getUserByUsername(newUser.getUsername()) == null)
               {
                   ManagerFactory.getUsersManager(RegisterActivity.this).
                           create(registerBinding.getNewuser().getUsername(), registerBinding.getNewuser().getPassword(),
                                   registerBinding.getNewuser().getAddress());
                   Toast.makeText(RegisterActivity.this, "Successful registration!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
               }
               else
                   Toast.makeText(RegisterActivity.this, "Failed registration", Toast.LENGTH_SHORT).show();
           }
       });

    }
}
