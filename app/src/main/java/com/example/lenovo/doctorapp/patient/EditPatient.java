package com.example.lenovo.doctorapp.patient;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lenovo.doctorapp.R;
import com.example.lenovo.doctorapp.dbutil.Constants;
import com.example.lenovo.doctorapp.dbutil.Manager;

import java.io.ByteArrayOutputStream;

public class EditPatient extends AppCompatActivity {
    Manager cm;
    SQLiteDatabase sb;
    EditText txttoken,new_name,new_phone,new_prob,new_date;
    ImageView newimg;
    String token,name,phone,prob,date;
    Bitmap bm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cm=new Manager(this);
        sb=cm.openDb();

        setContentView(R.layout.activity_edit_patient);
        txttoken=(EditText)findViewById(R.id.txttoken);

        new_name=(EditText)findViewById(R.id.new_name);
        new_phone=(EditText)findViewById(R.id.new_phone);
        new_prob=(EditText)findViewById(R.id.new_prob);
        new_date=(EditText)findViewById(R.id.new_date);
        newimg=(ImageView)findViewById(R.id.newimg);

    }

    public void click(View v) {

        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//to invoke inbuilt activity to click picture
        startActivityForResult(i, 1);//1 is the request code
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle b = data.getExtras();
        bm = (Bitmap) b.get("data");//bitmap is inbuilt class
        newimg.setImageBitmap(bm);
    }

    public void update(View v)
    {
        token=txttoken.getText().toString();
        String  arr[]={token};

        name=new_name.getText().toString();
        phone=new_phone.getText().toString();
        prob=new_prob.getText().toString();
        date=new_date.getText().toString();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] imgarr = bos.toByteArray();

        ContentValues cv=new ContentValues();

        cv.put(Constants.P_COL_NAME,name);
        cv.put(Constants.P_COL_PHONE,phone);
        cv.put(Constants.P_COL_PROB,prob);
        cv.put(Constants.P_COL_DATE,date);
        cv.put(Constants.P_COL_PIC,imgarr);

        long l=sb.update(Constants.TBL_NAME,cv,Constants.COL_ID+" =?",arr);
        if (l>0)
        {
            Toast.makeText(this,"record updated",Toast.LENGTH_SHORT).show();
        }

    }

}
