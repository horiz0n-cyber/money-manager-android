package com.example.project07.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "BackEnd";
    private static final int DATABASE_VERSION = 1;

    public static final String TB_ACC = "account";
    public static final String KEY_ID = "acc_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_PASSWD = "passwd";
    public static final String KEY_DATE = "date";

    public static final String TB_NOTI = "noti";
    public static final String NOTI_ID = "noti_id";
    public static final String NOTI_ACC_ID = "id";
    public static final String NOTI_MONEY = "money";
    public static final String NOTI_NOTE = "note";
    public static final String NOTI_DATE = "date";

    public static final String TB_IN = "in_money";
    public static final String IN_ID = "in_id";
    public static final String IN_MONEY = "money";
    public static final String IN_CATE_ID = "cate_id";
    public static final String IN_NOTE = "note";
    public static final String IN_DATE = "date";
    public static final String IN_ACC_ID = "id";

    public static final String TB_OUT = "out_money";
    public static final String OUT_ID = "out_id";
    public static final String OUT_MONEY = "money";
    public static final String OUT_CATE_ID = "cate_id";
    public static final String OUT_NOTE = "note";
    public static final String OUT_DATE = "date";
    public static final String OUT_ACC_ID = "id";

    private static final String TB_CATE = "cate";
    private static final String CATE_ID = "id";
    private static final String CATE_NAME = "name";

    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_acc_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT,%s TEXT)", TB_ACC, KEY_ID, KEY_NAME, KEY_PHONE, KEY_PASSWD, KEY_DATE);
        db.execSQL(create_acc_table);

        String create_noti_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s INTEGER)", TB_NOTI, NOTI_ID, NOTI_MONEY, NOTI_DATE, NOTI_NOTE, NOTI_ACC_ID);
        db.execSQL(create_noti_table);

        String create_in_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s INTEGER)", TB_IN, IN_ID, IN_MONEY, IN_CATE_ID, IN_NOTE, IN_DATE, IN_ACC_ID);
        db.execSQL(create_in_table);

        String create_out_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s INTEGER)", TB_OUT, OUT_ID, OUT_MONEY, OUT_CATE_ID, OUT_NOTE, OUT_DATE, OUT_ACC_ID);
        db.execSQL(create_out_table);

        String create_cate_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT)", TB_CATE, CATE_ID, CATE_NAME);
        db.execSQL(create_cate_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
