package edu.ucu.loginform;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
        //creating database
    public DatabaseHelper(Context context){
        super(context,"Login.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating table and fields in database
        db.execSQL("Create table user(username text primary key, password text, name text, address text, gender text, status text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // automatic dropping table if the table have a same table name
        db.execSQL("drop table if exists user");
    }
    public boolean insert (String Eusername, String Epassword, String Ename, String Eaddress, String Rgender, String Estatus ){
        SQLiteDatabase db = this.getWritableDatabase();
        //inserting an data to the fields in table
        //Content values is the one holding store tha data that you insert
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", Eusername); //fields in table
        contentValues.put("password",Epassword);
        contentValues.put("name",Ename);
        contentValues.put("address",Eaddress);
        contentValues.put("gender",Rgender);
        contentValues.put("status",Estatus);
        long ins = db.insert("user", null, contentValues);
        if (ins==-1) return false;
        else return true;
    }
        //checking an username exists in register page
    public Boolean chkusername(String username){
        //validation in username
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where username=?", new String[]{username});
        if(cursor.getCount()>0) return false;
        else return true;
    }
    //validation or checking the username and password
    public Boolean usernamepassword (String username, String password){
        //validation username and password
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where username=? and password=?", new String[]{username, password});
        if (cursor.getCount()>0) return true;
        else return false;
    }
}
