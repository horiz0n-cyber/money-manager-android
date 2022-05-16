package com.example.project07.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.project07.database.DbHandler;
import com.example.project07.expense.ExpenseClass;
import com.example.project07.income.IncomeClass;
import com.example.project07.tracking.IncomeExpenseDetail;

import java.util.ArrayList;


public class ExpenseModel {
    public void addExpense(ExpenseClass expenseClass, Context context) {

        DbHandler dh = new DbHandler(context);
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(dh.OUT_MONEY, expenseClass.getMoney());
        values.put(dh.OUT_CATE_ID, expenseClass.getCate_id());
        values.put(dh.OUT_NOTE, expenseClass.getNote());
        values.put(dh.OUT_DATE, expenseClass.getDate());
        values.put(dh.OUT_ACC_ID, expenseClass.getAccId());
        db.insert(dh.TB_OUT, null, values);
        db.close();
        dh.close();
    }

    public String getAdvByCate(int ID, int cate_id, Context context){
        DbHandler dh = new DbHandler(context);
        SQLiteDatabase db = dh.getReadableDatabase();
        int money = 0;
        String sql = "SELECT "+dh.OUT_MONEY+" FROM "+dh.TB_OUT+" WHERE "+dh.OUT_CATE_ID+" =\""+ cate_id +"\" AND "+dh.OUT_ACC_ID+"=\""+ ID +"\"";

        Cursor cs = db.rawQuery(sql, null);
        cs.moveToFirst();
        while(!cs.isAfterLast()){
            money += cs.getInt(0);
            cs.moveToNext();
        }
        return String.valueOf(money);
    }

    public ArrayList<IncomeExpenseDetail> getListExpenseDetail(int ID, int cate_id, Context context){
        DbHandler dh = new DbHandler(context);
        SQLiteDatabase db = dh.getReadableDatabase();
        ArrayList<IncomeExpenseDetail> listExpenseDetail = new ArrayList<>();
        String sql = "SELECT "+dh.OUT_ID+","+dh.OUT_MONEY+","+dh.OUT_DATE+" FROM "+dh.TB_OUT+" WHERE "+dh.OUT_CATE_ID+" =\""+ cate_id +"\" AND "+dh.OUT_ACC_ID+"=\""+ ID +"\"";

        Cursor cs =  db.rawQuery(sql, null);
        cs.moveToFirst();
        while(!cs.isAfterLast()){
            listExpenseDetail.add(new IncomeExpenseDetail(cs.getInt(0), cs.getString(1), cs.getString(2)));
            cs.moveToNext();
        }
        System.out.println(listExpenseDetail);
        return listExpenseDetail;

    }
    public void MakeId(Context context){
        DbHandler dh = new DbHandler(context);
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        int ID =0;
        int AccID =1;

        String dk = "id = '"+ID+"'";
        values.put(dh.OUT_ACC_ID, AccID);
        db.update(dh.TB_OUT,values,dk, null);
        db.close();
        dh.close();
    }

    public ExpenseClass getExpenseByInID(int out_id, Context context){
        DbHandler dh = new DbHandler(context);
        SQLiteDatabase db = dh.getReadableDatabase();
        String sql = "SELECT * FROM "+dh.TB_OUT+" WHERE "+dh.OUT_ID+" =\""+ out_id +"\" ";

        Cursor cs = db.rawQuery(sql, null);
        ExpenseClass expenseClass = new ExpenseClass();
        cs.moveToFirst();
        while(!cs.isAfterLast()){
            expenseClass = new ExpenseClass(out_id, cs.getString(1), cs.getInt(2), cs.getString(3), cs.getString(4));
            cs.moveToNext();
        }
        System.out.println(expenseClass);
        return expenseClass;
    }

    public void updateExpense( ExpenseClass expenseClass, Context context){
        DbHandler dh = new DbHandler(context);
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();

        String dk = "out_id = '"+expenseClass.getOut_id()+"'";
        values.put(dh.OUT_MONEY, expenseClass.getMoney());
        values.put(dh.OUT_CATE_ID, expenseClass.getCate_id());
        values.put(dh.OUT_NOTE, expenseClass.getNote());
        values.put(dh.OUT_DATE, expenseClass.getDate());
        db.update(dh.TB_OUT,values,dk, null);
        db.close();
        dh.close();
    }

    public void delete(int out_id, Context context){
        DbHandler dh = new DbHandler(context);
        SQLiteDatabase db = dh.getWritableDatabase();
        String selection = dh.OUT_ID + " = ?";

        db.delete(dh.TB_OUT, selection, new String[]{String.valueOf(out_id)});

    }
}
