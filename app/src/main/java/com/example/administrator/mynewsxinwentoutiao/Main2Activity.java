package com.example.administrator.mynewsxinwentoutiao;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
public class Main2Activity extends AppCompatActivity {
    private EditText edusername;
    private EditText edrpassword;
    private EditText edpasswordagain;
    private Button zhucembtn;
    private MyBHelper myBHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        edusername = (EditText) findViewById(R.id.denglu_edit_username);
        edrpassword = (EditText) findViewById(R.id.denglu_edit_password);
        edpasswordagain = (EditText) findViewById(R.id.denglu_edit_passwordagain);
        zhucembtn = (Button)findViewById(R.id.zhuce_btn);
        zhucembtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inSert();
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void inSert(){
        myBHelper = new MyBHelper(getApplicationContext(), "userDB", null, 1);
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
