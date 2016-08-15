package com.example.administrator.mynewsxinwentoutiao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Administrator on 2016/8/11.
 */
public class Dao  {
    private MyBHelper myDBHelper;
    Context mcontext;

    public Dao(MyBHelper myDBHelper, Context mcontext) {
        this.myDBHelper = myDBHelper;
        this.mcontext = mcontext;

    }
    public void inSert(){
        myDBHelper=new MyBHelper(mcontext,"userDB",null,1);
        SQLiteDatabase database = myDBHelper.getReadableDatabase();
       database.execSQL("insert into usertable(name,password) value(?,?) ",new Object[]{});
        database.close();
    }


}
