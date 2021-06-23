package com.example.study_final_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.concurrent.ThreadLocalRandom;

public class DBHelper3 extends SQLiteOpenHelper {
    public static final String DBName = "Usergradedata.db";

    public DBHelper3(Context context) {
        super(context, DBName, null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE Usergradedata(id TEXT primary key , name TEXT , finalgrade TEXT , coursecode TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop TABLE if exists Usergradedata");
    }

    public Boolean insertusergrade(String name ,String finalGrade, String courseCode){
        SQLiteDatabase DB= this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put("id", ThreadLocalRandom.current().nextInt(0, 9 + 1));
        contentValues.put("name",name);
        contentValues.put("finalgrade",finalGrade);
        contentValues.put("coursecode",courseCode);
        long result = DB.insert("Usergradedata",null,contentValues);
        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    public Cursor getdata(){
        SQLiteDatabase DB =this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Usergradedata" , null);
        return cursor;
    }
}
