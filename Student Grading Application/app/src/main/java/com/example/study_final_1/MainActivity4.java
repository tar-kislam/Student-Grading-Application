package com.example.study_final_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity4 extends AppCompatActivity {
    ListView lv;
    Button back2menu;
    DBHelper DB;

    ArrayList<String> listItem;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        lv=findViewById(R.id.list);
        back2menu=findViewById(R.id.button9);

        DB = new DBHelper(this);
        listItem = new ArrayList<>();
        ReadData();

        Cursor cursor = DB.getdata();

        if (cursor.getCount()  == 0){
            Toast.makeText(MainActivity4.this, "No data to show", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
            listItem.add("Name: "+cursor.getString(0)+"\n | Password: "+cursor.getString(1)+"\n | Gender: " +cursor.getString(2)+"\n");
            }
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listItem);
            lv.setAdapter(adapter);
        }

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                SQLiteDatabase db1=DB.getWritableDatabase();
                String[] aa={listItem.get(position)};
                db1.delete("Userdetails","name=?",aa);
                listItem.remove(position);
                adapter.notifyDataSetChanged();
                DB.close();

               //String text = lv.getItemAtPosition(position).toString();
               //Toast.makeText(MainActivity4.this, ""+text, Toast.LENGTH_SHORT).show();
            }
        });

        back2menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity4.this,MainActivity3.class);
                startActivity(intent);
            }
        });


    }
    String[] tableinfo={"name"};
    public  void ReadData(){
        SQLiteDatabase db=DB.getReadableDatabase();
        Cursor c=db.query("Userdetails",tableinfo,null,null,null,null,null);//tableinfo[0]
        while (c.moveToNext()){
            listItem.add(""+c.getString(0));
        }
        db.close();
    }
}