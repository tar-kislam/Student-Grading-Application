package com.example.study_final_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBName = "Userdata.db";

    public DBHelper(Context context) {
        super(context, DBName,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE Userdetails(name TEXT primary key , password TEXT, gender TEXT, finalgrade TEXT, coursecode TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop TABLE if exists Userdetails");
    }


    public Boolean insertuserdata(String name , String password, String gender){
        SQLiteDatabase DB= this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put("name",name);
        contentValues.put("password",password);
        contentValues.put("gender",gender);
        long result = DB.insert("Userdetails",null,contentValues);

        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    public Boolean insertgrade(String name ,String finalGrade ,String courseCode){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("finalgrade",finalGrade);
        cv.put("coursecode",courseCode);
        long result = DB.insert("Userdetails",null,cv);
        if (result == -1){
            return false;
        }else {
            return true;
        }
    }




    public Boolean checkname(String name){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?",new String[]{name});
        if (cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }
    public Boolean namepassword(String name,String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ? and password = ?",new String[]{name,password});
        if (cursor.getCount() > 0){
            return true;
        }else {
            return false;
        }
    }
    public Boolean updateuserdata(String name , String password, String gender) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", password);
        contentValues.put("gender", gender);
        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.update("Userdetails", contentValues, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }

    public Boolean deleteuserdata(String name ) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Userdetails", "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }

    public Cursor getdata(){
        SQLiteDatabase DB =this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails" , null);
        return cursor;
    }

}
