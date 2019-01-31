package com.example.lenovo.doctorapp.clerk;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.doctorapp.R;
import com.example.lenovo.doctorapp.dbutil.Constants;
import com.example.lenovo.doctorapp.dbutil.Manager;

import java.io.ByteArrayInputStream;

public class SearchClerk extends AppCompatActivity {
    Manager im;
    SQLiteDatabase sb;
    TextView txtname,txtpass,txtemail,txtphone;
    ImageView img;
    EditText clerk_search;
    String name,pass,email,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_clerk);
        im=new Manager(this);
        sb=im.openDb();
        txtname=(TextView)findViewById(R.id.txtname);
        txtpass=(TextView)findViewById(R.id.txtpass);
        txtemail=(TextView)findViewById(R.id.txtemail);
        txtphone=(TextView)findViewById(R.id.txtphone);
        img=(ImageView)findViewById(R.id.img);
        clerk_search=(EditText)findViewById(R.id.clerk_search);

    }
    public  void fetch(View v)
    {
        String id=clerk_search.getText().toString();
        String arr[]={id};

        Cursor c= sb.query(Constants.TBL_NAME,null,Constants.COL_ID+"=?",arr,null,null,null,null);


        byte[]im=null;
        if(c!=null &&c.moveToFirst())
        {

            name=c.getString(c.getColumnIndex(Constants.COL_NAME));
            pass=c.getString(c.getColumnIndex(Constants.COL_PASS));
           email=c.getString(c.getColumnIndex(Constants.COL_EMAIL));
            phone=c.getString(c.getColumnIndex(Constants.COL_PHONE));
            Toast.makeText(this,name,Toast.LENGTH_LONG).show();
            im=   c.getBlob(c.getColumnIndex(Constants.COL_PIC));

            txtname.setText(name);
            txtpass.setText(pass);
            txtemail.setText(email);
            txtphone.setText(phone);
            ByteArrayInputStream ba=new ByteArrayInputStream(im);
            Bitmap b= BitmapFactory.decodeStream(ba);
            img.setImageBitmap(b);

        }
        else
            {
            txtname.setText("no match found in records");
                txtpass.setText(" ");
                txtemail.setText(" ");
                txtphone.setText(" ");
                img.setImageBitmap(null);
        }

    }
}


