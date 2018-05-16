package com.example.boyanyosifov.myapplication.com.online.shop.repository;

public interface LaptopSchema {
    String ID = "id";
    String LAPTOP_TABLE = "LAPTOPS";
    String COLUMN_ID = "id";
    String COLUMN_MANUFACTURER = "manufacturer";
    String COLUMN_MODEL = "model";
    String COLUMN_DESCRIPTION = "description";
    String COLUMN_PRICE = "price";

    String LAPTOP_TABLE_CREATE = "CREATE TABLE " + LAPTOP_TABLE + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            COLUMN_MANUFACTURER + " VARCHAR(32), " +
            COLUMN_MODEL + " VARCHAR(32), " +
            COLUMN_DESCRIPTION + " TEXT, " +
            COLUMN_PRICE + " DOUBLE" +
            ");";

    String[] LAPTOP_COLUMNS = new String[] { COLUMN_ID,
            COLUMN_MANUFACTURER, COLUMN_MODEL, COLUMN_DESCRIPTION, COLUMN_PRICE };
}
