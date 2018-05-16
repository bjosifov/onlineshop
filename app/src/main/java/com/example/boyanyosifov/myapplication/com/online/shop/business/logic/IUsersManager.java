package com.example.boyanyosifov.myapplication.com.online.shop.business.logic;

import com.example.boyanyosifov.myapplication.com.online.shop.repository.User;
import java.util.List;

public interface IUsersManager {
    User login(String username, String password);
    User create(String username, String password, String email);
    User getUserByUsername(String username);
}
