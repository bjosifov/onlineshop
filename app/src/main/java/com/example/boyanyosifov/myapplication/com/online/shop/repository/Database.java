package com.example.boyanyosifov.myapplication.com.online.shop.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.boyanyosifov.myapplication.com.online.shop.business.logic.ManagerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import static com.example.boyanyosifov.myapplication.com.online.shop.repository.LaptopSchema.LAPTOP_TABLE;
import static com.example.boyanyosifov.myapplication.com.online.shop.repository.LaptopSchema.LAPTOP_TABLE_CREATE;
import static com.example.boyanyosifov.myapplication.com.online.shop.repository.OrderSchema.ORDER_TABLE;
import static com.example.boyanyosifov.myapplication.com.online.shop.repository.OrderSchema.ORDER_TABLE_CREATE;
import static com.example.boyanyosifov.myapplication.com.online.shop.repository.PhoneSchema.PHONE_TABLE;
import static com.example.boyanyosifov.myapplication.com.online.shop.repository.PhoneSchema.PHONE_TABLE_CREATE;
import static com.example.boyanyosifov.myapplication.com.online.shop.repository.UserSchema.USER_TABLE;
import static com.example.boyanyosifov.myapplication.com.online.shop.repository.UserSchema.USER_TABLE_CREATE;

public class Database {
    private DatabaseHelper databaseHelper;
    private UserContentProvider userDbContentProvider;
    private LaptopContentProvider laptopDbContentProvider;
    private PhoneContentProvider phoneDbContentProvider;
    private OrderContentProvider orderDbContentProvider;
    private static Database instance;

    private Database(Context context) {
        this.databaseHelper =  new DatabaseHelper(context);
        openConnection();
    }

    public static final Database getInstance(Context context) {
        if(instance == null ) {
            instance = new Database(context);
        }

        return instance;
    }

    public void openConnection() {
        databaseHelper.openConnection();
    }

    public void close(){
        databaseHelper.close();
    }

    public UserContentProvider getUserDbContentProvider() {
        return userDbContentProvider;
    }

    public LaptopContentProvider getLaptopDbContentProvider() {
        return laptopDbContentProvider;
    }

    public PhoneContentProvider getPhoneDbContentProvider() {
        return phoneDbContentProvider;
    }

    public OrderContentProvider getOrderDbContentProvider() {
        return orderDbContentProvider;
    }

    private class DatabaseHelper extends SQLiteOpenHelper {

        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "db_online_shop.db";

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL("DROP TABLE IF EXISTS " + LAPTOP_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + PHONE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + ORDER_TABLE);

            db.execSQL(LAPTOP_TABLE_CREATE);
            db.execSQL(PHONE_TABLE_CREATE);
            db.execSQL(USER_TABLE_CREATE);
            db.execSQL(ORDER_TABLE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Drop older table if existed
            db.execSQL("DROP TABLE IF EXISTS " + LAPTOP_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + PHONE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + ORDER_TABLE);
            // Create tables again
            onCreate(db);
        }

        public void openConnection() {
            try {
                SQLiteDatabase database = getWritableDatabase();
                initContentProviders(database);

                List<User> userList = new ArrayList<User>();
                userList = userDbContentProvider.getAll();
                if(userList.size()<= 1)
                    addTestValues();
            }
            catch (SQLiteException e){
                System.out.println(e.getMessage());
            }
        }

        private void addTestValues() {
            addLaptopEntities();
            addPhoneEntities();
        }

        private void addLaptopEntities() {
            List<Laptop> testValues = new ArrayList<>();
            testValues.add(new Laptop("Lenovo", "Lenovo T540P","" +
                    "Intel Core i7-4600M\n" +
                    "NVIDIA GeForce GT 730M\n" +
                    "256GB SSD\n" +
                    "Windows 8 Pro\n",950.99 ));
            testValues.add(new Laptop("Acer", "Acer Aspire E5","" +
                    "Core i3-4030U\n" +
                    "4GB\n" +
                    "GeForce 840M\n" +
                    "1TB HDD\n",1450.99 ));
            testValues.add(new Laptop("Lenovo", "Lenovo ThinkPad E480 ","" +
                    "Intel Core i7-8550U\n" +
                    "AMD Radeon RX 550\n" +
                    "8GB DDR4\n" +
                    "1TB SSD NVMe\n",1150.99 ));
            testValues.add(new Laptop("Dell", "DELL XPS 15","" +
                    "Intel Core i7-7700HQ\n" +
                    "NVIDIA GeForce GTX 1050\n" +
                    "8GB DDR4\n" +
                    "1TB SSD NVMe",1450.99 ));
            testValues.add(new Laptop("HP", "HP Spectre x360","" +
                    "Intel Core i5-7200U\n" +
                    "Intel HD Graphics 620\n" +
                    "8GB DDR3\n" +
                    "1TB SSD NVMe",1450.99 ));
            testValues.add(new Laptop("Acer", "Acer Nitro 5","" +
                    "Intel Core i5-7300HQ\n" +
                    "NVIDIA GeForce GTX 1050\n" +
                    "8GB DDR4\n" +
                    "250GB SSD + 1TB HDD",1450.99 ));

            laptopDbContentProvider.addEntities(testValues);
        }

        private void addPhoneEntities() {
            List<Phone> testValues = new ArrayList<>();
            testValues.add(new Phone("Apple", "Iphone 6", "Size \t4.7 inches, 60.9 cm2 (~65.6% screen-to-body ratio)\n" +
                    "Batter  \tNon-removable Li-Ion 1715 mAh battery (6.91 Wh)\n" +
                    "Primary camera 12 MP (f/2.2, 29mm, 1/3\", 1.22µm), phase detection autofocus, dual-LED dual-tone flash\n" +
                    "CPU \tDual-core 1.84 GHz Twister\n" +
                    "OS \tiOS 9, upgradable to iOS 11.3\n" +
                    "Internal Memory \t16/32/64/128 GB, 2 GB RAM", 600.0));

            testValues.add(new Phone("Samsung", "Samsung S6","Size \t5.1 inches, 71.5 cm2 (~70.7% screen-to-body ratio)\n" +
                    "Battery Non-removable Li-Ion 2550 mAh battery\n" +
                    "Primary Camera \t16 MP (f/1.9, 28mm, 1/2.6\", 1.12µm), OIS, autofocus, LED flash\n" +
                    "OS \tAndroid 5.0.2 (Lollipop), 7.0 (Nougat), planned upgrade to Android 8.0 (Oreo)\n" +
                    "CPU \tOcta-core (4x2.1 GHz Cortex-A57 & 4x1.5 GHz Cortex-A53)\n" +
                    "Internal \t32/64/128 GB, 3 GB RAM",750.99 ));

            testValues.add(new Phone("Samsung", "Samsung Galaxy S8","Size 5.8 inches, 84.8 cm2 (~83.6% screen-to-body ratio)\n" +
                    "Battery Non-removable Li-Ion 3000 mAh battery (11.55 Wh)\n" +
                    "Primary camera\t12 MP (f/1.7, 26mm, 1/2.5\", 1.4µm, Dual Pixel PDAF), phase detection autofocus, OIS, LED flash\n" +
                    "CPU Octa-core (4x2.3 GHz Mongoose M2 & 4x1.7 GHz Cortex-A53) - EMEA\n" +
                    "OS Android 7.0 (Nougat), upgradable to Android 8.0 (Oreo)\n" +
                    "Internal Memory 64 GB, 4 GB RAM",1200.99 ));

            testValues.add(new Phone("Apple", "Iphone X","Size 5.8 inches, 84.4 cm2 (~82.9% screen-to-body ratio)\n" +
                    "Battery Non-removable Li-Ion 2716 mAh battery (10.35 Wh)\n" +
                    "Primary Camera Dual: 12 MP (f/1.8, 28mm) + 12 MP (f/2.4, 52mm), OIS, phase detection autofocus, 2x optical zoom, quad-LED dual-tone flash\n" +
                    "CPU\tHexa-core 2.39 GHz (2x Monsoon + 4x Mistral)\n" +
                    "OS iOS 11.1.1, upgradable to iOS 11.3\n" +
                    "Internal Memory 64/256 GB, 3 GB RAM",1399.99 ));

            testValues.add(new Phone("Lenovo", "Lenovo K320t","Size 5.7 inches, 83.8 cm2 (~73.5% screen-to-body ratio)\n" +
                    "Battery Non-removable Li-Ion 3000 mAh battery\n" +
                    "Primary Camera Dual: 8 MP (f/2.0, AF) + 2 MP (f/2.2, no AF), autofocus, LED flash\n" +
                    "CPU Quad-core 1.3 GHz Cortex-A7\n" +
                    "OS Android 7.0 (Nougat)\n" +
                    "Internal Memory 32 GB, 3 GB RAM or 16 GB, 2 GB RAM",750.99 ));

            testValues.add(new Phone("Nokia", "Nokia 6","Size 5.5 inches, 82.6 cm2 (~73.2% screen-to-body ratio)\n" +
                    "Bettery Non-removable Li-Ion 3000 mAh battery\n" +
                    "Primary Camera 16 MP (f/2.0, 27mm, 1.0µm), phase detection autofocus, Zeiss optics, dual-LED dual-tone flash\n" +
                    "CPU Octa-core 2.2 GHz Cortex-A53\n" +
                    "OS Android 8.1 (Oreo)\n" +
                    "Internal Memory 32/64 GB, 3/4 GB RAM",750.99 ));
            phoneDbContentProvider.addEntities(testValues);
        }

        private void initContentProviders(SQLiteDatabase database) {
            userDbContentProvider = new UserContentProvider(database);
            laptopDbContentProvider = new LaptopContentProvider(database);
            phoneDbContentProvider = new PhoneContentProvider(database);
            orderDbContentProvider = new OrderContentProvider(database);
        }
    }
}
