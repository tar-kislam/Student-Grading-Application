package com.example.study_final_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity  {

    String[] genders= {"Male","Female"};

    EditText name,password;

    Button submit,login,update,btn_delete;
    DBHelper DB;

    String genderTXT="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        name=findViewById(R.id.editTextTextPersonName);
        password=findViewById(R.id.editTextTextPassword2);
        Spinner gender = (Spinner) findViewById(R.id.spinner);
        submit=findViewById(R.id.button4);
        login=findViewById(R.id.button5);
        update=findViewById(R.id.button11);
        btn_delete=findViewById(R.id.button12);
        DB = new DBHelper(this);


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,genders);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapter);

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameTXT = name.getText().toString();
                Boolean checkdeletedata = DB.deleteuserdata(nameTXT);
                if (checkdeletedata == true){
                    Toast.makeText(MainActivity2.this,"Entry Deleted",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity2.this,"Entry NOT Deleted",Toast.LENGTH_SHORT).show();
                }

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = name.getText().toString();
                String passwordTXT= password.getText().toString();
                Boolean checkupdatedata = DB.updateuserdata(nameTXT,passwordTXT,genderTXT);
                if (checkupdatedata == true){
                    Toast.makeText(MainActivity2.this,"Entry Updated",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity2.this,"Entry NOT Updated",Toast.LENGTH_SHORT).show();
                }

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this,MainActivity.class);
                startActivity(intent);
            }
        });

        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                genderTXT = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = name.getText().toString();
                String passTXT = password.getText().toString();

                if (name.equals("") || password.equals("")){
                    Toast.makeText(MainActivity2.this, "Please enter All Fields !", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkname = DB.checkname(nameTXT);
                    if (checkname == false){
                        Boolean insert = DB.insertuserdata(nameTXT,passTXT,genderTXT);
                        if (insert == true){
                            Toast.makeText(MainActivity2.this, "Registered Successfully !", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(MainActivity2.this, "Registration Failed !", Toast.LENGTH_SHORT).show();
                    }
                }

                Log.i("TAG","Gender : " +genderTXT);

            }
        });

    }
}