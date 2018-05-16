package com.example.boyanyosifov.myapplication.com.online.shop.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.boyanyosifov.myapplication.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class UserContentProvider extends DbContentProvider implements DataManager<User>, UserSchema {
    private Cursor cursor;
    private ContentValues initialValues;

    public UserContentProvider(SQLiteDatabase db) {
        super(db);
    }

    @Override
    public User getById(long id) {
        final String selectionArgs[] = {String.valueOf(id)};
        final String selection = ID + " = ?";
        User user = new User();
        cursor = super.query(USER_TABLE, USER_COLUMNS, selection,
                selectionArgs, COLUMN_ID);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                user = cursorToEntity(cursor);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<User>();
        try {
            cursor = super.query(USER_TABLE, USER_COLUMNS, null,
                    null, COLUMN_ID);

            if (cursor != null) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    User user = cursorToEntity(cursor);
                    userList.add(user);
                    cursor.moveToNext();
                }
                cursor.close();
            }
        }
        catch (RuntimeException ex){
            System.out.println(ex.getStackTrace());
        }



        return userList;
    }

    @Override
    public User addEntity(User user) {
        setContentValue(user);
        try {
             User createdUser =
                     new User(super.insert(USER_TABLE, getContentValue()), user.getUsername(), user.getPassword(), user.getAddress());
            return createdUser;
        } catch (SQLiteConstraintException ex) {
            Log.w("Database", ex.getMessage());
            return null;
        }
    }

    @Override
    public boolean addEntities(List<User> entities) {
        for(User user : entities) {
            addEntity(user);
        }
        return true;
    }

    @Override
    public boolean removeEntity(User entity) {
        super.delete(USER_TABLE, ID +  " = ?", new String[] {String.valueOf(entity.getId())});
        return true;
    }

    @Override
    public boolean updateEntity(User updatedEntity) {
        setContentValue(updatedEntity);
        return super.update(USER_TABLE, getContentValue(), ID + " = ?", new String[] {String.valueOf(updatedEntity.getId())} ) > 0;
    }

    @Override
    protected User cursorToEntity(Cursor cursor) {
        User user = new User();
        user.setId(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)));
        user.setUsername(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USERNAME)));
        user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD) ));
        user.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)));
        return user;
    }

    private void setContentValue(User user) {
        initialValues = new ContentValues();
        //initialValues.put(COLUMN_ID, user.getId());
        initialValues.put(COLUMN_USERNAME, user.getUsername());
        initialValues.put(COLUMN_ADDRESS, user.getAddress());
        initialValues.put(COLUMN_PASSWORD, user.getPassword());
    }

    private ContentValues getContentValue() {
        return initialValues;
    }
}
