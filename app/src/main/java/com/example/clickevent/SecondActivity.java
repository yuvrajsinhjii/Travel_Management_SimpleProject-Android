package com.example.clickevent;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    EditText editTextUsername, editTextPassword;
    Button logbtn;

    SQLiteDatabase sqLiteDatabase;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        editTextUsername = findViewById(R.id. editTextUsername);
        editTextPassword = findViewById(R.id. editTextPassword);
        logbtn = findViewById(R.id.logbtn);

        dbHelper = new DatabaseHelper(this);
        sqLiteDatabase = dbHelper.getWritableDatabase();

        logbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(username)) {
            editTextUsername.setError("Enter username");
            editTextUsername.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Enter password");
            editTextPassword.requestFocus();
            return;
        }

        // Check if username and password match in database
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM users WHERE username=? AND password=?", new String[]{username, password});
        if (cursor.getCount() > 0) {
            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
            // Move to home activity
            startActivity(new Intent(SecondActivity.this, HomeActivity.class));
            // Optionally finish this activity
        } else {
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }
}
