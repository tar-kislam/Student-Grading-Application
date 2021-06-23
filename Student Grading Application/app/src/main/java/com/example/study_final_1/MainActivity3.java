package com.example.study_final_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {

    Button showdeleteuser,updateuser,addnewuser,next;
    DBHelper DB;
    LinearLayout ll;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        showdeleteuser=findViewById(R.id.button6);
        updateuser=findViewById(R.id.button7);
        addnewuser=findViewById(R.id.button8);
        next=findViewById(R.id.button10);
        ll=findViewById(R.id.linearLayout);
        DB = new DBHelper(this);
        ll.setBackgroundColor(Color.BLACK);
        showdeleteuser.setBackgroundColor(Color.GRAY);
        updateuser.setBackgroundColor(Color.GRAY);
        addnewuser.setBackgroundColor(Color.GRAY);
        next.setBackgroundColor(Color.RED);



        updateuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this,MainActivity2.class);
                startActivity(intent);
                Toast.makeText(MainActivity3.this, "You have to enter the *NAME* you want to update. ", Toast.LENGTH_LONG).show();
            }
        });



        showdeleteuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity3.this,MainActivity4.class);
                startActivity(intent);
            }
        });



        addnewuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this,MainActivity2.class);
                startActivity(intent);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameText = (String) getIntent().getStringExtra("NAME");
                Intent intent = new Intent(MainActivity3.this,MainActivity5.class);
                intent.putExtra("NAME",nameText);
                startActivity(intent);
            }
        });
    }
}