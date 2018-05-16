package com.example.boyanyosifov.myapplication.com.online.shop.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class OrderContentProvider extends DbContentProvider implements DataManager<Order>, OrderSchema {
    private ContentValues contentValues;
    private Cursor cursor;

    public OrderContentProvider(SQLiteDatabase db) {
        super(db);
    }

    @Override
    public Order getById(long id) {
        Order order = new Order();
        cursor = super.query(OrderSchema.ORDER_TABLE, OrderSchema.ORDER_COLUMNS,ID + " = ? ", new String[]{ String.valueOf(ID)}, COLUMN_ID);
        if(cursor != null ) {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                order = cursorToEntity(cursor);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return order;
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        cursor = super.query(ORDER_TABLE, OrderSchema.ORDER_COLUMNS, null,null,COLUMN_ID);
        if(cursor != null){
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                orders.add(cursorToEntity(cursor));
                cursor.moveToNext();
            }
            cursor.close();
        }

        return orders;
    }

    @Override
    public Order addEntity(Order entity) {
        setContentValues(entity);
        return new Order(super.insert(ORDER_TABLE, getContentValues()), entity.getProductType(), entity.getUserId(), entity.getProductId());
    }

    @Override
    public boolean addEntities(List<Order> entities) {
        for (Order order : entities) {
            addEntity(order);
        }

        return true;
    }

    @Override
    public boolean removeEntity(Order entity) {
        return super.delete(ORDER_TABLE, ID + " =? ", new String[] {String.valueOf(entity.getId())})  > 0;
    }

    @Override
    public boolean updateEntity(Order updatedEntity) {
        setContentValues(updatedEntity);
        return super.update(ORDER_TABLE,contentValues, ID + " = ? ", new String[] {String.valueOf(updatedEntity.getId())}) > 0;
    }

    @Override
    protected Order cursorToEntity(Cursor cursor) {
        Order order = new Order();
        order.setId(cursor.getInt(cursor.getColumnIndexOrThrow(OrderSchema.COLUMN_ID)));
        order.setProductId(cursor.getInt(cursor.getColumnIndexOrThrow(OrderSchema.COLUMN_PRODUCT_ID)));
        order.setUserId(cursor.getInt(cursor.getColumnIndexOrThrow(OrderSchema.COLUMN_USER_ID)));
        order.setProductType(ProductType.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(OrderSchema.COLUMN_PRODUCT_TYPE))));
        return order;
    }

    private void setContentValues(Order order) {
        contentValues = new ContentValues();
        contentValues.put(COLUMN_USER_ID, order.getUserId());
        contentValues.put(COLUMN_PRODUCT_ID, order.getProductId());
        contentValues.put(COLUMN_PRODUCT_TYPE, order.getProductType().toString());
    }

    private ContentValues getContentValues() {
        return contentValues;
    }
}
