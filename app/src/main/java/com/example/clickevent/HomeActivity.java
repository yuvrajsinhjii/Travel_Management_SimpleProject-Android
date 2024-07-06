package com.example.clickevent;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    ImageButton btn1, btn2, btn3, btn4;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        button = findViewById(R.id.button);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the next activity
                Intent intent = new Intent(HomeActivity.this, FireActivity.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the next activity
                Intent intent = new Intent(HomeActivity.this, AdventureActivity.class);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the next activity
                Intent intent = new Intent(HomeActivity.this, CampingActivity.class);
                startActivity(intent);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the next activity
                Intent intent = new Intent(HomeActivity.this, GamesActivity.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, PlaceActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.Adventure:
//                // Handle Adventure menu item click
//                Toast.makeText(getApplicationContext(), "Adventure", Toast.LENGTH_SHORT).show();
//                // Example: Navigate to AdventureActivity.class
//                Intent adventureIntent = new Intent(getApplicationContext(), AdventureActivity.class);
//                startActivity(adventureIntent);
//                return true;
//            case R.id.search:
//                // Handle Search menu item click
//                Toast.makeText(getApplicationContext(), "Search", Toast.LENGTH_SHORT).show();
//                // Example: Handle search functionality
//                return true;
//            case R.id.logout:
//                // Handle Logout menu item click
//                Toast.makeText(getApplicationContext(), "Logout", Toast.LENGTH_SHORT).show();
//                // Example: Navigate to MainActivity.class to logout
//                Intent logoutIntent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(logoutIntent);
//                return true;
//            case R.id.exit:
//                // Handle Exit menu item click
//                Toast.makeText(getApplicationContext(), "Exit", Toast.LENGTH_SHORT).show();
//                // Example: Close the app
//                finishAffinity();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
}
