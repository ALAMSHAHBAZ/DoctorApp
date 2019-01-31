package com.example.lenovo.doctorapp.dbutil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by lenovo on 7/2/2017.
 */

public class Manager
{


    Context context;
    Helper ch;
    SQLiteDatabase sb;

    public Manager(Context context)
    {
        this.context=context;
        ch=new Helper(context, Constants.DB_NAME,null,Constants.DB_VERSION);

    }

    public SQLiteDatabase openDb()
    {


        sb=  ch.getWritableDatabase();
        return sb;
    }

    public  void closeDb()
    {

        ch.close();


    }



}
