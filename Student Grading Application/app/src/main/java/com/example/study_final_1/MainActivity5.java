package com.example.study_final_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity5 extends AppCompatActivity {

    EditText mid1,mid2,finalScore;
    RadioGroup rg;
    Button calculate,showList;
    DBHelper3 DB;
    int calculateGrade;
    String value="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        mid1=findViewById(R.id.midterm1);
        mid2=findViewById(R.id.midterm2);
        finalScore=findViewById(R.id.finalscore);
        rg=findViewById(R.id.radiogroup);
        calculate=findViewById(R.id.button16);
        showList=findViewById(R.id.button17);
        DB = new DBHelper3(this);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) findViewById(checkedId);
                value = rb.getText().toString();
                System.out.println(value);
            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    calculateGrade = (int) ((Integer.parseInt(mid1.getText().toString())*0.25)+(Integer.parseInt(mid2.getText().toString())*0.25)+(Integer.parseInt(finalScore.getText().toString())*0.5));
                }catch (Exception e){
                    System.out.println(calculateGrade);
                }

                String nameText = (String) getIntent().getStringExtra("NAME");
                Boolean insert = DB.insertusergrade(nameText,String.valueOf(calculateGrade),value);
                if (insert == true){
                    Toast.makeText(MainActivity5.this, "Calculate Successfully !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        showList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity5.this,MainActivity6.class);
                startActivity(intent);
            }
        });



    }
}