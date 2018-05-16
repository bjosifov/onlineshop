package com.example.boyanyosifov.myapplication.com.online.shop.repository;

public interface OrderSchema  {
    String ID = "id";
    String ORDER_TABLE = "ORDERS";
    String COLUMN_PRODUCT_TYPE = "type";
    String COLUMN_ID = "id";
    String COLUMN_USER_ID = "user_id";
    String COLUMN_PRODUCT_ID = "product_id";

    String ORDER_TABLE_CREATE = "CREATE TABLE " + ORDER_TABLE + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
            COLUMN_USER_ID + " INTEGER, " +
            COLUMN_PRODUCT_ID + " INTEGER, " +
            COLUMN_PRODUCT_TYPE + " VARCHAR(32), " +
            " FOREIGN KEY(" + COLUMN_USER_ID + ") REFERENCES " + UserSchema.USER_TABLE + " (" + UserSchema.ID  +
            ")" + ")";

    String[] ORDER_COLUMNS = new String[] { COLUMN_ID,
            COLUMN_PRODUCT_TYPE, COLUMN_USER_ID, COLUMN_PRODUCT_ID };
}
