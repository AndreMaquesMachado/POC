package com.example.sqlinjectionpoc;

import android.content.ContentValues;
import android.os.Bundle;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText input;
    TextView showInput;

    DatabaseHelper dbhelper;
    SQLiteDatabase db;
    public static final String TB_NAME = "usertable";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = (EditText) findViewById(R.id.editText);
        showInput = (TextView) findViewById(R.id.textView2);
        dbhelper = new DatabaseHelper(this, TB_NAME, null, 1);
        db = dbhelper.getWritableDatabase();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_1:
                showResult(input.getText().toString());
                break;
        }
    }

    public void showResult(String info) {
        if (info == null || info.length() <= 0)
            showInput.setText("Please input:");
        else {
            Cursor cursor;
            //Testing raw query - exposed to 1' or '1'='1

            /*
            cursor = db.rawQuery("SELECT * FROM usertable WHERE _id='" + info + "'", null);
            cursor.moveToFirst();
            */

            //Testing raw query with parameterized input - failed to 1' or '1'='1

            /*
            String m_argv[] = {input.getText().toString()};
            cursor = db.rawQuery("SELECT * FROM  usertable WHERE _id=?", m_argv);
            cursor.moveToFirst();
            */

            //Testing an update before the vulnerable select - delete and select don t do nothing

            String m_argv[] = {input.getText().toString()};
            ContentValues cv = new ContentValues();
            cv.put("username","';--");
            //cv.put("password","1; Select * from usertable;");
            cv.put("password","newpassword");
            //db.rawQueryWithFactory()
            //db.execSQL("delete from usertable where _id =2");
            db.update("usertable",cv,"_id=?",new String[]{String.valueOf(1)});
            cursor = db.rawQuery("SELECT * FROM  usertable WHERE _id='" + info + "'", null);
            cursor.moveToFirst();

            //Testing replace in android
            /*
            String m_argv[] = {input.getText().toString()};
            ContentValues cv = new ContentValues();
            cv.put("username",";--");
            cv.put("password",";--");
            //cv.put("password","delete from usertable where _id =2");
            db.replace("usertable","1",cv);
            cursor = db.rawQuery("SELECT * FROM  usertable WHERE id='" + info + "'", null);
            cursor.moveToFirst();
            */
            String result = "";
            while (!cursor.isAfterLast()) {
                result += "id:" + cursor.getInt(0) + "\r\n" + "user:" + cursor.getString(1) + "\r\n" + "pass:" + cursor.getString(2) + "\r\n";
                cursor.moveToNext();
            }
            showInput.setText(result);
            cursor.close();
        }
    }
}