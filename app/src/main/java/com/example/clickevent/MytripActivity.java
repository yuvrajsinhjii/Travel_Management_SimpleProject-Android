package com.example.clickevent;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.clickevent.DatabaseHelper;

import java.util.ArrayList;

public class MytripActivity extends AppCompatActivity {
    ListView lvdata;
    ArrayList list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mytrip);

        lvdata=(ListView) findViewById(R.id.listviewdata);
        list= new ArrayList();

        DatabaseHelper dbhelper=new DatabaseHelper(getApplicationContext());
        Cursor resultset=dbhelper.read_data();
        list.clear();
        if(resultset.getCount()==0)
            Toast.makeText(getApplicationContext(),"No Data",Toast.LENGTH_SHORT).show();
        else {
            while(resultset.moveToNext())
            {
                list.add(resultset.getString(0)+" "+ "\n"+ "Name : "+ resultset.getString(1)+" "+"\n"+ "Address : "+ resultset.getString(2)+" "+"\n"+ "Email : "+ resultset.getString(3)+" "+"\n"+ "Phone: "+ resultset.getString(4)+" "+"\n"+ "Placename: "+ resultset.getString(5)+" "+"\n"+ "Adult: "+ resultset.getString(6)+" "+"\n"+ "Children: "+ resultset.getString(7)+" "+"\n"+ "Date : "+ resultset.getString(8));
            }
            ArrayAdapter adapter=new ArrayAdapter(getApplicationContext(),
                    android.R.layout.simple_list_item_1,list);
            lvdata.setAdapter(adapter);
        }
    }
}