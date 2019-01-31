package com.example.lenovo.doctorapp.clerk;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lenovo.doctorapp.R;
import com.example.lenovo.doctorapp.dbutil.Constants;
import com.example.lenovo.doctorapp.dbutil.Manager;

import java.io.ByteArrayOutputStream;

public class EditClerk extends AppCompatActivity {
    Manager cm;
    SQLiteDatabase sb;
    EditText new_pass,new_email,new_phone,txtid;
    String id,pass,email,phone;
    ImageView newimg;
    Bitmap bm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_clerk);
        cm=new Manager(this);
        sb=cm.openDb();
        new_pass=(EditText)findViewById(R.id.new_pass);
        new_email=(EditText)findViewById(R.id.new_email);
        new_phone=(EditText)findViewById(R.id.new_phone);
        txtid=(EditText)findViewById(R.id.txtid);
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
         id=txtid.getText().toString();
      String  arr[]={id};
        pass=new_pass.getText().toString();
        email=new_email.getText().toString();
        phone=new_phone.getText().toString();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, bos);
       byte[] imgarr = bos.toByteArray();

        ContentValues cv=new ContentValues();
        cv.put(Constants.COL_PASS,pass);
        cv.put(Constants.COL_EMAIL,pass);
        cv.put(Constants.COL_PHONE,phone);
        cv.put(Constants.COL_PIC,imgarr);

        long l=sb.update(Constants.TBL_NAME,cv,Constants.COL_ID+" =?",arr);
        if (l>0)
        {
            Toast.makeText(this,"record updated",Toast.LENGTH_SHORT).show();
        }

    }
}
