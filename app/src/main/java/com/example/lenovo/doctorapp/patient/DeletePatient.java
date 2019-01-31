package com.example.lenovo.doctorapp.patient;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lenovo.doctorapp.R;
import com.example.lenovo.doctorapp.dbutil.Constants;
import com.example.lenovo.doctorapp.dbutil.Manager;

import static com.example.lenovo.doctorapp.dbutil.Constants.P_COL_TOKEN;

public class DeletePatient extends AppCompatActivity {
    Manager cm;
    SQLiteDatabase sb;
    EditText deletetoken;
    String del_token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_patient);

        cm=new Manager(this);
        sb=cm.openDb();
        deletetoken=(EditText)findViewById(R.id.deletetoken);
    }

    public  void DeleteData(View v)
    {
        del_token=deletetoken.getText().toString();
        // String em="abc@gmail.com";
        String args[]={del_token};
        long rw=  sb.delete(Constants.P_TBL_NAME,P_COL_TOKEN+" =?",args);
        //? is place holder
        if(rw>0)
            Toast.makeText(this,"datadelete",Toast.LENGTH_SHORT).show();

    }
}
