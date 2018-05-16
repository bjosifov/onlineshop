package com.example.boyanyosifov.myapplication.com.online.shop.repository;

public interface UserSchema {
    String ID = "id";
    String USER_TABLE = "USERS";
    String COLUMN_ID = "id";
    String COLUMN_USERNAME = "username";
    String COLUMN_PASSWORD = "password";
    String COLUMN_ADDRESS = "address";

    String USER_TABLE_CREATE = "CREATE TABLE " + USER_TABLE + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            COLUMN_USERNAME + " VARCHAR(32), " +
            COLUMN_PASSWORD + " VARCHAR(32), " +
            COLUMN_ADDRESS + " VARCHAR(255) " +
            ");";

    String[] USER_COLUMNS = new String[] { COLUMN_ID,
            COLUMN_USERNAME, COLUMN_PASSWORD,COLUMN_ADDRESS };
}
