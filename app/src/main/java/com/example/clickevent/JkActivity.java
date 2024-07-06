package com.example.clickevent;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class JkActivity extends AppCompatActivity {

    EditText Name, Address, Phone, EmailAddress, Date, Placename, Adult, Children;
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
        Placename = findViewById(R.id.spinner1);
        Adult = findViewById(R.id.spinner2);
        Children = findViewById(R.id.spinner3);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        dbHelper = new DatabaseHelper(this);
        sqLiteDatabase = dbHelper.getWritableDatabase();

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                book();
            }
        });
    }

    private void book() {
        String NameStr = Name.getText().toString().trim();
        String AddressStr = Address.getText().toString().trim();
        String PhoneStr = Phone.getText().toString().trim();
        String EmailAddressStr = EmailAddress.getText().toString().trim();
        String DateStr = Date.getText().toString().trim();
        String PlacenameStr = Placename.getText().toString().trim();
        String AdultStr = Adult.getText().toString().trim();
        String ChildrenStr = Children.getText().toString().trim();

        // Check if any of the fields are empty
        if (NameStr.isEmpty() || AddressStr.isEmpty() || PhoneStr.isEmpty() || EmailAddressStr.isEmpty() || DateStr.isEmpty() || PlacenameStr.isEmpty() || AdultStr.isEmpty() || ChildrenStr.isEmpty()) {
            Toast.makeText(this, "Please fill in all details", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate email address
        if (!Patterns.EMAIL_ADDRESS.matcher(EmailAddressStr).matches()) {
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isValidPhoneNumber(PhoneStr)) {
            Phone.setError("Enter a valid phone number");
            Phone.requestFocus();
            return;
        }

        if (!isValidDateFormat(DateStr)) {
            Toast.makeText(this, "Please enter a date in yyyy-MM-dd format", Toast.LENGTH_SHORT).show();
            return;
        }

        ContentValues values = new ContentValues();
        values.put("name", NameStr);
        values.put("address", AddressStr);
        values.put("phone", PhoneStr);
        values.put("email", EmailAddressStr);
        values.put("date", DateStr);
        values.put("placename", PlacenameStr);
        values.put("adult", AdultStr);
        values.put("children", ChildrenStr);

        long newRowId = sqLiteDatabase.insert("trip", null, values);

        if (newRowId != -1) {
            Toast.makeText(this, "Booking successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(JkActivity.this, MytripActivity.class));
        } else {
            Toast.makeText(this, "Booking failed", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.length() == 10 && Patterns.PHONE.matcher(phoneNumber).matches();
    }

    // Method to validate date format
    private boolean isValidDateFormat(String date) {
        try {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            sdf.parse(date);
            return true;
        } catch (java.text.ParseException e) {
            return false;
        }
    }
}
