package com.example.lenovo.doctorapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.doctorapp.dbutil.Constants;
import com.example.lenovo.doctorapp.dbutil.Manager;

public class ClerkLogin extends AppCompatActivity  {
    Manager im;
    SQLiteDatabase sb;
    EditText txtname,txtpass;
    String name,password,nm;

TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clerk_login);
        im = new Manager(this);
        sb = im.openDb();

        txtname=(EditText)findViewById(R.id.txtname);
        txtpass=(EditText)findViewById(R.id.txtpass);
        txt=(TextView)findViewById(R.id.txt);

    }

    public void  onClick(View v)
    {
        name=txtname.getText().toString();
        password=txtpass.getText().toString();
        String arr[]={name,password};

      Cursor cr=sb.query(Constants.TBL_NAME,null,Constants.COL_NAME+"=?"+" AND "+Constants.COL_PASS+"=?",arr,null,null,null,null);


        if(cr!=null &&cr.moveToFirst())
        {
            Toast.makeText(this,"match found",Toast.LENGTH_SHORT).show();
            nm=cr.getString(cr.getColumnIndex(Constants.COL_NAME));

            byte[] byteArray= cr.getBlob(cr.getColumnIndex(Constants.COL_PIC));

            Intent i=new Intent(getApplicationContext(),ClerkActivity.class);

           /* ByteArrayInputStream ba=new ByteArrayInputStream(byteArray);
            Bitmap b= BitmapFactory.decodeStream(ba);
*/
           i.putExtra("name",nm);
            i.putExtra("image",byteArray);


            startActivity(i);


        }
        else
        {
            txt.setText("No records found.Retry");
        }

    }

}
