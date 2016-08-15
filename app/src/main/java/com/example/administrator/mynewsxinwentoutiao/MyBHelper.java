package com.example.administrator.mynewsxinwentoutiao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/8/11.
 */
public class MyBHelper extends SQLiteOpenHelper {
    public MyBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "userDB", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
           db.execSQL("CREATE TABLE usertable(name VARCHAR,password VARCHAR)");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
