package com.example.lenovo.doctorapp.clerk;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.lenovo.doctorapp.MyAdapter;
import com.example.lenovo.doctorapp.BeanClerk;
import com.example.lenovo.doctorapp.R;
import com.example.lenovo.doctorapp.dbutil.Constants;
import com.example.lenovo.doctorapp.dbutil.Manager;

import java.util.ArrayList;

public class ViewClerk extends AppCompatActivity {

    ListView lv;
    Manager im;
    SQLiteDatabase sb;
    ArrayList<BeanClerk> mylist;
    BeanClerk s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_clerk);
        lv = (ListView) findViewById(R.id.lv);
        im = new Manager(this);
        sb = im.openDb();


        mylist = new ArrayList<BeanClerk>();
        fillList();
        MyAdapter ad = new MyAdapter(this, mylist);

        lv.setAdapter(ad);


    }

    void fillList() {
        Cursor c = sb.query(Constants.TBL_NAME, null, null, null, null, null, null, null);
        if (c != null && c.moveToFirst()) {
            do {

                String name = c.getString(c.getColumnIndex(Constants.COL_NAME));
                String stid = c.getString(c.getColumnIndex(Constants.COL_ID));
                byte[] arr = c.getBlob(c.getColumnIndex(Constants.COL_PIC));
                String pass=c.getString(c.getColumnIndex(Constants.COL_PASS));
                String email=c.getString(c.getColumnIndex(Constants.COL_EMAIL));
                String phone=c.getString(c.getColumnIndex(Constants.COL_PHONE));
                s = new BeanClerk();
                s.setId(stid);
                s.setName(name);
                s.setPassword(pass);
                s.setEmail(email);
                s.setPhone(phone);
                s.setClerkPic(arr);
                mylist.add(s);


            }
            while (c.moveToNext());
            c.close();
        }
    }
}
