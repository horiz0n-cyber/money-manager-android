package com.example.project07.model;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project07.database.DbHandler;
import com.example.project07.reminder.RemindClass;

import java.util.ArrayList;

public class RemindModel {
    public void addNoti(String noti_note, String noti_money, String noti_date, int ID, Context context) {

        DbHandler dh = new DbHandler(context);
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(dh.NOTI_MONEY, noti_money);
        values.put(dh.NOTI_NOTE, noti_note);
        values.put(dh.NOTI_DATE, noti_date);
        values.put(dh.NOTI_ACC_ID, ID);
        db.insert(dh.TB_NOTI, null, values);
        db.close();
        dh.close();
    }

    public ArrayList<RemindClass> getListNoti(int ID, Context context){
        DbHandler dh = new DbHandler(context);
        SQLiteDatabase db = dh.getReadableDatabase();
        ArrayList<RemindClass> arrayList = new ArrayList<>();
        String sql = "SELECT * FROM noti WHERE id='"+ID+"'";

        Cursor cs = db.rawQuery(sql, null);
        cs.moveToFirst();
        while(!cs.isAfterLast()){
            RemindClass remindClass = new RemindClass(cs.getInt(0), cs.getString(1), cs.getString(2), cs.getString(3), cs.getInt(4));
            arrayList.add(remindClass);
            remindClass.toString();
            cs.moveToNext();
        }
        System.out.println(arrayList);
        return arrayList;
    }

    public RemindClass getNoti(int ID_NOTI, Context context){
        DbHandler dh = new DbHandler(context);
        SQLiteDatabase db = dh.getReadableDatabase();
        ArrayList<RemindClass> arrayList = new ArrayList<>();
        String sql = "SELECT * FROM noti WHERE noti_id='"+ID_NOTI+"'";

        Cursor cs = db.rawQuery(sql, null);
        cs.moveToFirst();
        RemindClass remindClass = new RemindClass(cs.getInt(0), cs.getString(1), cs.getString(2), cs.getString(3), cs.getInt(4));
        return remindClass;
    }

    public void updateNoti(int NOTI_ID, String noti_note, String noti_money, String noti_date, Context context){
        DbHandler dh = new DbHandler(context);
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();

        String dk = "noti_id = '"+NOTI_ID+"'";
        values.put(dh.NOTI_MONEY, noti_money);
        values.put(dh.NOTI_NOTE, noti_note);
        values.put(dh.NOTI_DATE, noti_date);
        db.update(dh.TB_NOTI,values,dk, null);
        db.close();
        dh.close();
    }

    public void delete(int NOTI_ID, Context context){
        DbHandler dh = new DbHandler(context);
        SQLiteDatabase db = dh.getWritableDatabase();
        String selection = dh.NOTI_ID + " = ?";

        db.delete(dh.TB_NOTI, selection, new String[]{String.valueOf(NOTI_ID)});

    }
}
