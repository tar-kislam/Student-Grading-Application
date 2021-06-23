package com.example.study_final_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name,password;
    Button enter,register,next;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.editTextTextPersonName3);
        password=findViewById(R.id.editTextTextPassword);
        enter=findViewById(R.id.button);
        register=findViewById(R.id.button2);
        next=findViewById(R.id.button3);
        DB = new DBHelper(this);



        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String user = name.getText().toString();
                 String pass = password.getText().toString();

                 if (user.equals("") || pass.equals("")){
                     Toast.makeText(MainActivity.this, "Please enter all fields !", Toast.LENGTH_SHORT).show();
                 }else{
                     Boolean checkuserpass = DB.namepassword(user,pass);
                     if (checkuserpass == true){
                         Toast.makeText(MainActivity.this, "Sign in Successfull", Toast.LENGTH_SHORT).show();
                         String getName = name.getText().toString();
                         Intent intent = new Intent(MainActivity.this,MainActivity3.class);
                         intent.putExtra("NAME",getName);
                         startActivity(intent);
                     }else {
                         Toast.makeText(MainActivity.this, "Invalid Entry !", Toast.LENGTH_SHORT).show();
                     }
                 }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity3.class);
                startActivity(intent);
            }
        });



    }
}