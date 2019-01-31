package com.example.lenovo.doctorapp.patient;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.lenovo.doctorapp.BeanPatient;
import com.example.lenovo.doctorapp.MyAdapter2;
import com.example.lenovo.doctorapp.R;
import com.example.lenovo.doctorapp.dbutil.Constants;
import com.example.lenovo.doctorapp.dbutil.Manager;

import java.util.ArrayList;

public class ViewPatient extends AppCompatActivity {
    ListView lv;
    Manager im;
    SQLiteDatabase sb;
    ArrayList<BeanPatient> mylist;
    BeanPatient s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_patient);
        lv = (ListView) findViewById(R.id.lv);
        im = new Manager(this);
        sb = im.openDb();


        mylist = new ArrayList<BeanPatient>();
        fillList();
        MyAdapter2 ad = new MyAdapter2(this, mylist);

        lv.setAdapter(ad);

    }

    void fillList() {
        Cursor c = sb.query(Constants.P_TBL_NAME, null, null, null, null, null, null, null);
        if (c != null && c.moveToFirst()) {
            do {

                String token = c.getString(c.getColumnIndex(Constants.P_COL_TOKEN));
                String name = c.getString(c.getColumnIndex(Constants.P_COL_NAME));
                String phone = c.getString(c.getColumnIndex(Constants.P_COL_PHONE));
                byte[] arr = c.getBlob(c.getColumnIndex(Constants.P_COL_PIC));
                String prob = c.getString(c.getColumnIndex(Constants.P_COL_PROB));
                String date = c.getString(c.getColumnIndex(Constants.P_COL_DATE));

                s = new BeanPatient();
                s.setToken(token);
                s.setName(name);
                s.setPhone(phone);
                s.setPic(arr);
                s.setProblem(prob);
                s.setDate(date);
                mylist.add(s);


            }
            while (c.moveToNext());
            c.close();
        }
    }
}
