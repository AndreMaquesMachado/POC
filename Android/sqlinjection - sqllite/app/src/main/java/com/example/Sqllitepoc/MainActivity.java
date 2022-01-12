package com.example.Sqllitepoc;

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

// cursor = db.rawQuery("SELECT * FROM usertable WHERE _id='" + info + "'", null);
//cursor.moveToFirst();


            //Testing raw query with parameterized input - failed to 1' or '1'='1

         /*
         String m_argv[] = {input.getText().toString()};

           cursor = db.rawQuery("SELECT * FROM  usertable WHERE _id=?", m_argv);
            cursor.moveToFirst();
*/

            //Testing an update before the vulnerable select - delete and select don t do nothing
            /*
            String m_argv[] = {input.getText().toString()};
            ContentValues cv = new ContentValues();
            cv.put("username",";--");
            cv.put("password","password");

            //
            //db.update("usertable",cv,"_id=? ",new String[]{String.valueOf(1)});
            //1º campo
            //db.update("usertable where _id=1 or 1=1 Union UPDATE usertable ",cv,"_id=? ",new String[]{String.valueOf("1")});
            //db.update("usertable where _id=1' or '1'='1 Union UPDATE usertable",cv,"_id=? ",new String[]{String.valueOf("1")});
            //db.update("usertable set username = '1' where _id=1' or '1'='1 Union UPDATE usertable",cv,"_id=? ",new String[]{String.valueOf("1")});
            //db.update("usertable set username = 1 where _id=1 or 1=1 Union UPDATE usertable",cv,"_id=? ",new String[]{String.valueOf("1")});
            //3 campo
            //db.update("usertable",cv,"_id=? or 1=1",new String[]{String.valueOf("1")}); - exploit
            //4º campo
            //db.update("usertable",cv,"_id=?",new String[]{String.valueOf("1' or '1'='1")});
            //db.update("usertable",cv,"_id=?",new String[]{String.valueOf("'1' or '1'='1'")});
            //db.update("usertable",cv,"_id=?",new String[]{String.valueOf("1 or 1=1")});
            //db.update("usertable",cv,"_id=?",new String[]{"1 or 1=1"});
            cursor = db.rawQuery("SELECT * FROM  usertable WHERE _id='" + info + "'", null);
            cursor.moveToFirst();
            */


            //testing execSQL
            /*
            //cv.put("password","1; Select * from usertable;");
            //db.rawQueryWithFactory()
            //
            */
            //db.execSQL("delete from usertable where _id =2");



            //Testing replace in android
            /*
            String m_argv[] = {input.getText().toString()};
            ContentValues cv = new ContentValues();
            cv.put("_id","1");
            cv.put("username","admin");
            cv.put("password","admin888");
            db.replace("usertable","1",cv);
            cursor = db.rawQuery("SELECT * FROM  usertable WHERE _id='" + info + "'", null);
            cursor.moveToFirst();
            */

            //Testing insert or throw

            String m_argv[] = {input.getText().toString()};
            ContentValues cv = new ContentValues();
            cv.put("username","admin");
            cv.put("password","admin888");
            db.insertOrThrow("usertable",null, cv);
            cursor = db.rawQuery("SELECT * FROM  usertable WHERE _id='" + info + "'", null);
            cursor.moveToFirst();
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