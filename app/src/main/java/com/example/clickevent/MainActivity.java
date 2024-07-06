package com.example.clickevent;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText fullName, email, username, password, phone;
    Button btnNext;
    TextView logbtn;
    SQLiteDatabase sqLiteDatabase;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fullName = findViewById(R.id.fullname);
        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        phone = findViewById(R.id.phone);
        btnNext = findViewById(R.id.btnNext);
        logbtn = findViewById(R.id.logbtn);
        dbHelper = new DatabaseHelper(this);
        sqLiteDatabase = dbHelper.getWritableDatabase();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser(); // Call registerUser method to validate fields and proceed to login if successful
            }
        });

        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
    }

    private void registerUser() {
        String fullNameStr = fullName.getText().toString().trim();
        String emailStr = email.getText().toString().trim();
        String usernameStr = username.getText().toString().trim();
        String passwordStr = password.getText().toString().trim();
        String phoneStr = phone.getText().toString().trim();

        if (TextUtils.isEmpty(fullNameStr)) {
            fullName.setError("Enter full name");
            fullName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(emailStr)) {
            email.setError("Enter email");
            email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailStr).matches()) {
            email.setError("Enter a valid email");
            email.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(usernameStr)) {
            username.setError("Enter username");
            username.requestFocus();
            return;
        }

        if (usernameStr.length() < 6) {
            username.setError("Username must be at least 6 characters long");
            username.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(passwordStr)) {
            password.setError("Enter password");
            password.requestFocus();
            return;
        }

        if (passwordStr.length() < 6) {
            password.setError("Password must be at least 6 characters long");
            password.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(phoneStr)) {
            phone.setError("Enter phone number");
            phone.requestFocus();
            return;
        }

        if (phoneStr.length() != 10) {
            phone.setError("Phone number must be 10 digits");
            phone.requestFocus();
            return;
        }

        if (!Patterns.PHONE.matcher(phoneStr).matches()) {
            phone.setError("Enter a valid phone number");
            phone.requestFocus();
            return;
        }

        // Check if username already exists
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM users WHERE username=?", new String[]{usernameStr});
        if (cursor.getCount() > 0) {
            username.setError("Username already exists");
            username.requestFocus();
            cursor.close();
            return;
        }
        cursor.close();

        // Insert data into database
        ContentValues values = new ContentValues();
        values.put("full_name", fullNameStr);
        values.put("email", emailStr);
        values.put("username", usernameStr);
        values.put("password", passwordStr);
        values.put("phone", phoneStr);

        long newRowId = sqLiteDatabase.insert("users", null, values);

        if (newRowId != -1) {
            Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, SecondActivity.class));
            finish(); // Optionally finish this activity
        } else {
            Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show();
        }
    }
}
