package com.example.clickevent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "travel.db";
    private static final int DATABASE_VERSION = 1;

    // Table names
    private static final String TABLE_USERS = "users";
    private static final String TABLE_TRIP = "trip";

    // Columns for users table
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_FULL_NAME = "full_name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_PHONE = "phone";

    // Columns for trip table
    private static final String COLUMN_TRIP_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_EMAILADDRESS = "email";
    private static final String COLUMN_TRIP_PHONE = "phone";
    private static final String COLUMN_TRIP_PLACENAME = "placename";
    private static final String COLUMN_TRIP_ADULT = "adult";
    private static final String COLUMN_TRIP_CHILDREN = "children";
    private static final String COLUMN_TRIP_DATE = "date";

    // Create table SQL statements
    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_FULL_NAME + " TEXT,"
            + COLUMN_EMAIL + " TEXT,"
            + COLUMN_USERNAME + " TEXT,"
            + COLUMN_PASSWORD + " TEXT,"
            + COLUMN_PHONE + " TEXT)";

    private static final String CREATE_TABLE_TRIP = "CREATE TABLE " + TABLE_TRIP + "("
            + COLUMN_TRIP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_ADDRESS + " TEXT,"
            + COLUMN_EMAILADDRESS + " TEXT,"
            + COLUMN_TRIP_PHONE + " TEXT,"
            + COLUMN_TRIP_PLACENAME + " TEXT,"
            + COLUMN_TRIP_ADULT + " TEXT,"
            + COLUMN_TRIP_CHILDREN + " TEXT,"
            + COLUMN_TRIP_DATE + " TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_TRIP);
    }

    public Cursor read_data(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from TRIP", null);
        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older tables if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRIP);

        // Create tables again
        onCreate(db);
    }

    // Insert data into the users table
    public long insertUser(String fullName, String email, String username, String password, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FULL_NAME, fullName);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_PHONE, phone);
        // Inserting Row
        long id = db.insert(TABLE_USERS, null, values);
        // Closing database connection
        db.close();
        return id;
    }

    // Insert data into the trip table
    public long insertTrip(String name, String address, String phone, String placename, String adult, String children, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_ADDRESS, address);
        values.put(COLUMN_TRIP_PHONE, phone);
        values.put(COLUMN_TRIP_PLACENAME, placename);
        values.put(COLUMN_TRIP_ADULT, adult);
        values.put(COLUMN_TRIP_CHILDREN, children);
        values.put(COLUMN_TRIP_DATE, date);

        long newRowId = -1;
        try {
            newRowId = db.insertOrThrow(TABLE_TRIP, null, values);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Closing database connection
            db.close();
        }

        return newRowId;
    }
}
