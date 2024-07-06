package com.example.clickevent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class PlaceActivity extends AppCompatActivity {
    TextView Jk, Rj, lax;
    Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_place);

        Jk = findViewById(R.id.Jk);
        Rj = findViewById(R.id.Rj);
        lax = findViewById(R.id.lax);
        btn2 = findViewById(R.id.btn2);


        Jk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlaceActivity.this, JkActivity.class);
                startActivity(intent);
            }
        });


        Rj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlaceActivity.this, RjActivity.class));
            }
        });

        lax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PlaceActivity.this, laxActivity.class));
            }
        });



        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlaceActivity.this, MytripActivity.class);
                startActivity(intent);
            }
        });
    }
}
