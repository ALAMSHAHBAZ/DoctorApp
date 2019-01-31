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

public class AddClerk extends AppCompatActivity {
    Manager im;
    SQLiteDatabase sb;
    ImageView imgView;
    EditText c_id, c_name, c_pass, c_email, c_phone;
    Bitmap bm;
    String id, name, pass, email, phone;
    byte[] imgarr;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        im = new Manager(AddClerk.this);
        sb = im.openDb();
        setContentView(R.layout.activity_add_clerk);
        imgView = (ImageView) findViewById(R.id.imgView);
        c_id = (EditText) findViewById(R.id.c_id);
        c_name = (EditText) findViewById(R.id.c_name);
        c_pass = (EditText) findViewById(R.id.c_pass);
        c_email = (EditText) findViewById(R.id.c_email);
        c_phone = (EditText) findViewById(R.id.c_phone);

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
        imgView.setImageBitmap(bm);
    }


    public void insert(View v) {
        id = c_id.getText().toString();
        int cid=Integer.parseInt(id);
       // Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
        name = c_name.getText().toString();
       // Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
        pass = c_pass.getText().toString();
        //Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
        email = c_email.getText().toString();
        //Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
        phone = c_phone.getText().toString();
        //Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, bos);
         imgarr = bos.toByteArray();

        ContentValues cv = new ContentValues();
        cv.put(Constants.COL_ID, cid);
        cv.put(Constants.COL_NAME, name);
        cv.put(Constants.COL_PASS, pass);
        cv.put(Constants.COL_EMAIL, email);
        cv.put(Constants.COL_PHONE, phone);
        cv.put(Constants.COL_PIC, imgarr);
        long l = sb.insert(Constants.TBL_NAME, null, cv);
        if (l > 0) {
            Toast.makeText(this, String.valueOf(l), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "insert successful", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(im!=null)
        {
            im.closeDb();
        }

    }
}
