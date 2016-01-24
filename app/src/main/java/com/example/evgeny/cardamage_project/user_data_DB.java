package com.example.evgeny.cardamage_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Evgeny on 30/12/2015.
 */
public class user_data_DB  extends SQLiteOpenHelper {
    public user_data_DB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queri = "create table if not exists user(name text)";
        db.execSQL(queri);
    }

    public void authorizateUser(String userName){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("name",userName);
        db.insert("user", null, value);
        db.close();
    }

    public void deleteUser()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        try
        {
            db.delete("user",  null, null);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            db.close();
        }
    }

    public boolean checkIfAuthorized(){
        SQLiteDatabase db = getWritableDatabase();
        String count = "SELECT count(*) FROM user";
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        return (icount>0);
    }

    public String getUser(){
        SQLiteDatabase db = getWritableDatabase();
        String count = "SELECT * FROM user";
        Cursor mcursor = db.rawQuery(count, null);
        if(mcursor.moveToFirst())
            return mcursor.getString(0);
        else return "";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
