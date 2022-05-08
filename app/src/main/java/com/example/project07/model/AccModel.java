package com.example.project07.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project07.database.DbHandler;

import java.util.ArrayList;

public class AccModel {
    public void addAcc(String name, String phone, String passwd, String date, Context context) {

        DbHandler dh = new DbHandler(context);
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(dh.KEY_NAME, name);
        values.put(dh.KEY_PHONE, phone);
        values.put(dh.KEY_DATE, date);
        values.put(dh.KEY_PASSWD, passwd);
        db.insert(dh.TB_ACC, null, values);
        db.close();
        dh.close();
    }

    private Cursor getAccData(Context context){
        DbHandler dh = new DbHandler(context);
        SQLiteDatabase db = dh.getReadableDatabase();
        ArrayList<String> arrayList = new ArrayList<>();
        String sql = "SELECT * FROM account";

        Cursor cs = db.rawQuery(sql, null);
        return cs;
    }

    public boolean checkPhone(String phone, Context context){
        boolean check=false;
        Cursor cs = getAccData(context);
        ArrayList<String> arrayList = new ArrayList<>();

        cs.moveToFirst();
        while(!cs.isAfterLast()){
            arrayList.add(cs.getString(2));
            cs.moveToNext();
        }
        for(int i=0; i<arrayList.size(); i++){
            if(phone.equals(arrayList.get(i))){
                check = true;
                break;
            }
        }
        System.out.println("done check sdt");
        return check;
    }

    public boolean checkPass(String phone, String pass, Context context){

        boolean check = false;
        DbHandler dh = new DbHandler(context);
        SQLiteDatabase db = dh.getReadableDatabase();

        String sql = "SELECT passwd FROM account WHERE phone='"+phone+"'";
        Cursor cs = db.rawQuery(sql, null);
        cs.moveToFirst();
        while(!cs.isAfterLast()){
            if(pass.equals(cs.getString(0))){
                check = true;
            }
            cs.moveToNext();
            System.out.println("thisss");
        }
        System.out.println("done check pass");
        return check;
    }

    public int getAccID(String phone, Context context) {

        DbHandler dh = new DbHandler(context);
        SQLiteDatabase db = dh.getReadableDatabase();

        String sql = "SELECT acc_id FROM account WHERE phone='"+phone+"'";
        Cursor cs = db.rawQuery(sql, null);
        cs.moveToFirst();
        int id = cs.getInt(0);
        cs.close();
        return id;
    }
}
