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

public class MainActivity6 extends AppCompatActivity {

    ListView lv;
    ArrayAdapter<String> adapter;
    ArrayList<String> gradeList;
    DBHelper3 DB;
    Button btn_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        lv=findViewById(R.id.list2);
        DB = new DBHelper3(this);
        gradeList = new ArrayList<String>();
        btn_update = findViewById(R.id.button13);
        ReadData();

        Cursor cursor = DB.getdata();
        if (cursor.getCount()  == 0){
            Toast.makeText(MainActivity6.this, "No data to show", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                gradeList.add("Name: "+cursor.getString(1)+"\nGrade: "+cursor.getString(2)+"\nCourse Code: "+cursor.getString(3));
            }
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,gradeList);
            lv.setAdapter(adapter);
            DB.close();
        }

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SQLiteDatabase db1=DB.getWritableDatabase();
                String[] aa={gradeList.get(position)};
                db1.delete("Usergradedata","id=?",aa);
                gradeList.remove(position);
                adapter.notifyDataSetChanged();
                DB.close();
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity6.this,MainActivity5.class);
                startActivity(intent);
            }
        });

    }
    String[] tableinfo={"id"};
    public  void ReadData(){
        SQLiteDatabase db=DB.getReadableDatabase();
        Cursor c=db.query("Usergradedata",tableinfo,null,null,null,null,null);//tableinfo[0]
        while (c.moveToNext()){
            gradeList.add(""+c.getString(0));
        }
        db.close();
    }
}