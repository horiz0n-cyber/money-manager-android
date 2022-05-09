package com.example.project07.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project07.database.DbHandler;
import com.example.project07.income.IncomeClass;
import com.example.project07.reminder.RemindClass;
import com.example.project07.tracking.IncomeExpenseDetail;

import java.util.ArrayList;
import java.util.List;

public class IncomeModel {
    public void addIncome(IncomeClass incomeClass, Context context) {

        DbHandler dh = new DbHandler(context);
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(dh.IN_MONEY, incomeClass.getMoney());
        values.put(dh.IN_CATE_ID, incomeClass.getCate_id());
        values.put(dh.IN_NOTE, incomeClass.getNote());
        values.put(dh.IN_DATE, incomeClass.getDate());
        values.put(dh.IN_ACC_ID, incomeClass.getAccId());
        db.insert(dh.TB_IN, null, values);
        db.close();
        dh.close();
    }

    public String getAdvByCate(int ID, int cate_id, Context context){
        DbHandler dh = new DbHandler(context);
        SQLiteDatabase db = dh.getReadableDatabase();
        int money = 0;
        String sql = "SELECT "+dh.IN_MONEY+" FROM "+dh.TB_IN+" WHERE "+dh.IN_CATE_ID+" =\""+ cate_id +"\" AND "+dh.IN_ACC_ID+"=\""+ ID +"\"";

        Cursor cs = db.rawQuery(sql, null);
        cs.moveToFirst();
        while(!cs.isAfterLast()){
            money += cs.getInt(0);
            cs.moveToNext();
        }
        return String.valueOf(money);
    }

    public ArrayList<IncomeExpenseDetail> getListIncomeDetail(int ID, int cate_id, Context context){
        DbHandler dh = new DbHandler(context);
        SQLiteDatabase db = dh.getReadableDatabase();
        ArrayList<IncomeExpenseDetail> listIncomeDetail = new ArrayList<>();
        String sql = "SELECT "+dh.IN_ID+","+dh.IN_MONEY+","+dh.IN_DATE+" FROM "+dh.TB_IN+" WHERE "+dh.IN_CATE_ID+" =\""+ cate_id +"\" AND "+dh.IN_ACC_ID+"=\""+ ID +"\"";

        Cursor cs =  db.rawQuery(sql, null);
        cs.moveToFirst();
        while(!cs.isAfterLast()){
            listIncomeDetail.add(new IncomeExpenseDetail(cs.getInt(0), cs.getString(1), cs.getString(2)));
            cs.moveToNext();
        }
        System.out.println(listIncomeDetail);
        return listIncomeDetail;

    }
}
