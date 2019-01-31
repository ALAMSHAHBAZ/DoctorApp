package com.example.lenovo.doctorapp.dbutil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by lenovo on 7/2/2017.
 */

public class Helper extends SQLiteOpenHelper
{

    Context context;
    public Helper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(Constants.TBL_QUERY);

        Toast.makeText(context,"tablecreated",Toast.LENGTH_LONG).show();
        db.execSQL(Constants.P_TBL_QUERY);
        Toast.makeText(context,"tablecreated1",Toast.LENGTH_LONG).show();


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}
