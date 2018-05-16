package com.example.boyanyosifov.myapplication.com.online.shop.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class PhoneContentProvider extends DbContentProvider implements PhoneSchema, DataManager<Phone> {
    private ContentValues contentValues;
    private Cursor cursor;

    public PhoneContentProvider(SQLiteDatabase db) {
        super(db);
    }

    //consider refactoring the logic
    @Override
    public Phone getById(long id) {
        Phone phone = new Phone();
        cursor = super.query(PHONE_TABLE,PHONE_COLUMNS, ID + " = ?" , new String[]{ String.valueOf(id)}, COLUMN_ID);
        if(cursor != null) {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                phone = cursorToEntity(cursor);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return phone;
    }

    @Override
    public List<Phone> getAll() {
        List<Phone> phones = new ArrayList<>();

        cursor = super.query(PHONE_TABLE, PHONE_COLUMNS, null,null, COLUMN_ID);
        if(cursor != null){
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                phones.add(cursorToEntity(cursor));
                cursor.moveToNext();
            }
            cursor.close();
        }

        return phones;
    }

    @Override
    public Phone addEntity(Phone entity) {
        setContentValues(entity);
        return new Phone(super.insert(PHONE_TABLE,getContentValues()),entity.getManufacturer(),entity.getModel(),entity.getPrice(), entity.getDescription());
    }

    @Override
    public boolean addEntities(List<Phone> entities) {
        for(Phone phone : entities) {
            addEntity(phone);
        }
        return true;
    }

    @Override
    public boolean removeEntity(Phone entity) {
        return super.delete(PHONE_TABLE, ID + " = ? " , new String []{String.valueOf(entity.getId())})>0;
    }

    @Override
    public boolean updateEntity(Phone updatedEntity) {
        setContentValues(updatedEntity);
        return super.update(PHONE_TABLE,getContentValues(), ID + " = ?", new String[] {String.valueOf(updatedEntity.getId())}) > 0;
    }

    @Override
    protected Phone cursorToEntity(Cursor cursor) {
        Phone phone = new Phone();
        phone.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
        phone.setManufacturer(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MANUFACTURER)));
        phone.setModel(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MODEL)));
        phone.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DESCRIPTION)));
        phone.setPrice(cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_PRICE)));
        return phone;
    }

    private void setContentValues(Phone entity) {
        contentValues = new ContentValues();
        //contentValues.put(COLUMN_ID, entity.getId());
        contentValues.put(COLUMN_MANUFACTURER, entity.getManufacturer());
        contentValues.put(COLUMN_MODEL, entity.getModel());
        contentValues.put(COLUMN_DESCRIPTION, entity.getDescription());
        contentValues.put(COLUMN_PRICE, entity.getPrice());
    }

    private ContentValues getContentValues(){
        return contentValues;
    }
}
