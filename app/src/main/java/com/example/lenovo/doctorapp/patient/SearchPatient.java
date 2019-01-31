package com.example.lenovo.doctorapp.patient;

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

public class SearchPatient extends AppCompatActivity {
    Manager im;
    SQLiteDatabase sb;
    TextView txtname,txtphone,txtprob,txtdate;
    ImageView img;
    EditText token_search;
    String name,phone,problem,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_patient);
        im=new Manager(this);
        sb=im.openDb();


        txtname=(TextView)findViewById(R.id.txtname);
        txtphone=(TextView)findViewById(R.id.txtphone);
        txtprob=(TextView)findViewById(R.id.txtprob);
        txtdate=(TextView) findViewById(R.id.txtdate);
        img=(ImageView)findViewById(R.id.img);
        token_search=(EditText)findViewById(R.id.token_search);
    }

    public void fetch(View v)
    {
        String token=token_search.getText().toString();

        String arr[]={token};

        Cursor c= sb.query(Constants.P_TBL_NAME,null,Constants.P_COL_TOKEN+"=?",arr,null,null,null,null);

        byte[]im=null;
        if(c!=null &&c.moveToFirst())
        {

            name=c.getString(c.getColumnIndex(Constants.P_COL_NAME));
            phone=c.getString(c.getColumnIndex(Constants.P_COL_PHONE));
            problem=c.getString(c.getColumnIndex(Constants.P_COL_PROB));
            date=c.getString(c.getColumnIndex(Constants.P_COL_DATE));

            Toast.makeText(this,name,Toast.LENGTH_LONG).show();
            im=   c.getBlob(c.getColumnIndex(Constants.P_COL_PIC));



            txtname.setText(name);
            txtphone.setText(phone);
            txtprob.setText(problem);
            txtdate.setText(date);

            ByteArrayInputStream ba=new ByteArrayInputStream(im);
            Bitmap b= BitmapFactory.decodeStream(ba);
            img.setImageBitmap(b);

        }
        else
        {
            txtname.setText("no match found in records");
            txtprob.setText(" ");
            txtphone.setText(" ");
            img.setImageBitmap(null);

        }
    }


}
