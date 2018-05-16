package com.example.boyanyosifov.myapplication.com.online.shop.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class LaptopContentProvider extends DbContentProvider implements DataManager<Laptop>, LaptopSchema {
    private ContentValues contentValues;
    private Cursor cursor;

    public LaptopContentProvider(SQLiteDatabase db) {
        super(db);
    }

    @Override
    public Laptop getById(long id) {
        final String selectionArgs[] = {String.valueOf(id)};
        final String selection = ID + " = ?";
        Laptop laptop = new Laptop();
        cursor =  super.query(LAPTOP_TABLE,LAPTOP_COLUMNS,selection, selectionArgs, COLUMN_ID);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                laptop = cursorToEntity(cursor);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return laptop;
    }

    @Override
    public List<Laptop> getAll() {
        List<Laptop> laptops = new ArrayList<>();
        cursor = super.query(LAPTOP_TABLE, LAPTOP_COLUMNS,null,null, COLUMN_ID);
        if(cursor != null) {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                laptops.add(cursorToEntity(cursor));
                cursor.moveToNext();
            }
        }
        return laptops;
    }

    @Override
    public Laptop addEntity(Laptop entity) {
        setContentValues(entity);

        try{
            return new Laptop(super.insert(LAPTOP_TABLE, getContentValues()), entity.getManufacturer(), entity.getModel(), entity.getDescription(),entity.getPrice());
        } catch( SQLiteConstraintException e) {
            Log.w("Database" , e.getMessage());
            return null;
        }
    }

    @Override
    public boolean addEntities(List<Laptop> entities) {
        for(Laptop laptop : entities) {
            addEntity(laptop);
        }

        return true;
    }

    @Override
    public boolean removeEntity(Laptop entity) {
        return super.delete(LAPTOP_TABLE, ID + " = ?", new String[] {String.valueOf(entity.getId())} ) > 0;
    }

    @Override
    public boolean updateEntity(Laptop updatedEntity) {
        setContentValues(updatedEntity);
        return super.update(LAPTOP_TABLE,getContentValues(), ID + " = ? ", new String[] {String.valueOf(updatedEntity.getId())} ) > 0;
    }

    @Override
    protected Laptop cursorToEntity(Cursor cursor) {
        Laptop laptop = new Laptop();
        laptop.setManufacturer(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MANUFACTURER)));
        laptop.setModel(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MODEL)));
        laptop.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION)));
        laptop.setPrice(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRICE)));
        laptop.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
        return laptop;
    }

    private void setContentValues(Laptop laptop) {
        contentValues = new ContentValues();
        //contentValues.put(COLUMN_ID, laptop.getId());
        contentValues.put(COLUMN_MANUFACTURER, laptop.getManufacturer());
        contentValues.put(COLUMN_MODEL, laptop.getModel());
        contentValues.put(COLUMN_DESCRIPTION, laptop.getDescription());
        contentValues.put(COLUMN_PRICE, laptop.getPrice());
    }

    private ContentValues getContentValues() {
        return contentValues;
    }
}
