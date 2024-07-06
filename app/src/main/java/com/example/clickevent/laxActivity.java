package com.example.clickevent;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class laxActivity extends AppCompatActivity {

    EditText Name, Address, Phone, EmailAddress, Date;
    Button buttonSubmit;
    DatabaseHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jk);

        Name = findViewById(R.id.Name);
        Address = findViewById(R.id.Address);
        Phone = findViewById(R.id.Phone);
        EmailAddress = findViewById(R.id.EmailAddress);
        Date = findViewById(R.id.Date);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        dbHelper = new DatabaseHelper(this);
        sqLiteDatabase = dbHelper.getWritableDatabase();
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(laxActivity.this,MytripActivity.class));
            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                book(); // Call book method to insert data into the database
            }
        });
    }

    private void book() {
        String NameStr = Name.getText().toString().trim();
        String AddressStr = Address.getText().toString().trim();
        String PhoneStr = Phone.getText().toString().trim();
        String EmailAddressStr = EmailAddress.getText().toString().trim();
        String DateStr = Date.getText().toString().trim();

        if (NameStr.isEmpty() || AddressStr.isEmpty() || PhoneStr.isEmpty() || EmailAddressStr.isEmpty() || DateStr.isEmpty()) {
            Toast.makeText(this, "Please fill in all details", Toast.LENGTH_SHORT).show();
            return; // Exit the method if any field is empty
        }

        ContentValues values = new ContentValues();
        values.put("name", NameStr);
        values.put("address", AddressStr);
        values.put("phone", PhoneStr);
        values.put("email", EmailAddressStr);
        values.put("date", DateStr);

        long newRowId = sqLiteDatabase.insert("trip", null, values);

        if (newRowId != -1) {
            Toast.makeText(this, "Booking successful", Toast.LENGTH_SHORT).show();
            // Optionally, you can navigate to another activity after successful booking
            // startActivity(new Intent(JkActivity.this, AnotherActivity.class));
            // finish();
        } else {
            Toast.makeText(this, "Booking failed", Toast.LENGTH_SHORT).show();
        }
    }
}
