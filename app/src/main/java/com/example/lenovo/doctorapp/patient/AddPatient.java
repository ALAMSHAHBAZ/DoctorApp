package com.example.lenovo.doctorapp.patient;

import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.doctorapp.R;
import com.example.lenovo.doctorapp.dbutil.Constants;
import com.example.lenovo.doctorapp.dbutil.Manager;

import java.io.ByteArrayOutputStream;

public class
AddPatient extends AppCompatActivity {
    Manager im;
    SQLiteDatabase sb;
    EditText p_token,p_name,p_phone,p_problem,p_date;
    String token,name,phone,problem,date;
    ImageView imgView;
    Bitmap bm;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);
        im = new Manager(this);
        sb = im.openDb();
        p_token=(EditText)findViewById(R.id.p_token);
        p_name=(EditText)findViewById(R.id.p_name);
        p_phone=(EditText)findViewById(R.id.p_phone);
        p_problem=(EditText)findViewById(R.id.p_problem);
        p_date=(EditText)findViewById(R.id.p_date);

        imgView=(ImageView)findViewById(R.id.imgView);
        text=(TextView)findViewById(R.id.text);

        Animation a= AnimationUtils.loadAnimation(this,R.anim.alpha);
        text.startAnimation(a);


    }

    public void click(View v)
    {

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


    public  void insert(View v)
    {
        token=p_token.getText().toString();

        name=p_name.getText().toString();
        phone=p_phone.getText().toString();
        problem=p_problem.getText().toString();
        date=p_date.getText().toString();


        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] imgarr = bos.toByteArray();

        ContentValues cv=new ContentValues();
        cv.put(Constants.P_COL_TOKEN,token);
        cv.put(Constants.P_COL_NAME,name);
        cv.put(Constants.P_COL_PHONE,phone);
        cv.put(Constants.P_COL_PROB,problem);
        cv.put(Constants.P_COL_DATE,date);
        cv.put(Constants.P_COL_PIC,imgarr);


        long l = sb.insert(Constants.P_TBL_NAME, null, cv);
        if (l > 0) {
            Toast.makeText(this, String.valueOf(l), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "insert successful", Toast.LENGTH_SHORT).show();
        }
    }

    public void message(View v)
    {
        String msg="Hello"+" "+name+"."+"This is Dr.Shahbaz,we have you scheduled for an appointment";
        SmsManager sm=SmsManager.getDefault();
        Intent i=new Intent(this,AddPatient.class);
        PendingIntent pi=PendingIntent.getActivity(this,2,i,PendingIntent.FLAG_ONE_SHOT);
        //2 is any positive num(res code)
        //flagoneshot means the pending intent is not modified(o pass 0)
        sm.sendTextMessage(phone,null,msg,pi,null);
        Toast.makeText(this,"message sent",Toast.LENGTH_SHORT).show();
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

