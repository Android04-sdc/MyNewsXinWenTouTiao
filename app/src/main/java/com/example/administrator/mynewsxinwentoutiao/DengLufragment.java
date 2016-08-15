package com.example.administrator.mynewsxinwentoutiao;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.Contacts;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DengLufragment extends Fragment {

    private EditText edusername;
    private EditText edrpassword;
    private EditText edpasswordagain;
    private Button zhucembtn;
    private MyBHelper myBHelper;
    public DengLufragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_deng_lufragment, container, false);
        edusername = (EditText) view.findViewById(R.id.denglu_edit_username);
        edrpassword = (EditText) view.findViewById(R.id.denglu_edit_password);
        edpasswordagain = (EditText) view.findViewById(R.id.denglu_edit_passwordagain);
        zhucembtn = (Button) view.findViewById(R.id.zhuce_btn);
        inSert();
        return view;
    }
    public void inSert(){
        myBHelper = new MyBHelper(getActivity(), "userDB", null, 1);
        SQLiteDatabase database = myBHelper.getReadableDatabase();
        if (!edpasswordagain.getText().toString().trim().equals(edrpassword.getText().toString().trim())){
          return;
        }
        ContentValues values=new ContentValues();
        values.put("name", String.valueOf(edusername.getText()));
        values.put("password", String.valueOf(edrpassword.getText()));
//        database.execSQL("insert into usertable(name,password) value(?,?) ",new Object[]{edusername.getText(),edrpassword.getText()});
       database.insert("usetable",null,values);
        database.close();
    }
}
