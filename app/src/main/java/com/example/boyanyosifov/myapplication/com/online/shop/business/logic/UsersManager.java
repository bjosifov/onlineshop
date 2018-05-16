package com.example.boyanyosifov.myapplication.com.online.shop.business.logic;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.boyanyosifov.myapplication.com.online.shop.repository.Database;
import com.example.boyanyosifov.myapplication.com.online.shop.repository.DbContentProvider;
import com.example.boyanyosifov.myapplication.com.online.shop.repository.User;
import com.example.boyanyosifov.myapplication.com.online.shop.repository.UserContentProvider;

import java.util.List;

public class UsersManager implements IUsersManager {
    private Context context;
    private UserContentProvider userDbContentProvider;
    public UsersManager(Context context) {
        userDbContentProvider = Database.getInstance(context).getUserDbContentProvider();
    }

    @Override
    public User login(String username, String password) {
        List<User> userList = userDbContentProvider.getAll();
        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().hashCode()==password.hashCode()) {
                return  user;
            }
        }
        return null;
    }

    @Override
    public User create(String username, String password, String email) {
        return userDbContentProvider.addEntity(new User(username,password,email));
    }

    @Override
    public User getUserByUsername(String username) {
        List<User> userList = userDbContentProvider.getAll();
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return  user;
            }
        }
        return null;
    }


}
