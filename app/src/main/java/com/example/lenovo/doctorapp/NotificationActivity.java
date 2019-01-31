package com.example.lenovo.doctorapp;

import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.doctorapp.dbutil.Constants;
import com.example.lenovo.doctorapp.dbutil.Manager;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NotificationActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    Manager im;
    SQLiteDatabase sb;
    DatePickerDialog dlg;
    String systemdate;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        txt=(TextView)findViewById(R.id.txt);

        im = new Manager(this);
        sb = im.openDb();

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DATE);
        SimpleDateFormat dfDate_day;
        dfDate_day = new SimpleDateFormat("dd/MM/yyyy");

        dlg = new DatePickerDialog(this, this, year, month, day);
        dlg.show();


    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        systemdate = String.valueOf(dayOfMonth) + "/" + String.valueOf(month+1) + "/" + String.valueOf(year);//+1 ka dhyaan rakhna :p
        Toast.makeText(this, "date" + " " + systemdate, Toast.LENGTH_SHORT).show();
        String[] arr = {systemdate};

        Cursor c = sb.query(Constants.P_TBL_NAME, null, Constants.P_COL_DATE + "=?", arr, null, null, null, null);


        if (c!= null && c.moveToFirst()) {


            String token = c.getString(c.getColumnIndex(Constants.P_COL_TOKEN));
            String name = c.getString(c.getColumnIndex(Constants.P_COL_NAME));
            String prob = c.getString(c.getColumnIndex(Constants.P_COL_PROB));

            txt.setText("You have an appointment with" + " " + name+"with token no."+token+" on "+systemdate+" for "+prob);

            NotificationCompat.Builder nb = new NotificationCompat.Builder(this);
            nb.setSmallIcon(R.drawable.smallicon);

            nb.setColor(200);
         /*   PendingIntent contentIntent =
                    PendingIntent.getActivity(this, 0, new Intent(getApplicationContext(), ViewPatient.class), 0);
            nb.setContentIntent(contentIntent);*/

            nb.setLargeIcon(BitmapFactory.decodeResource(this.getResources(),
                    R.drawable.docpad));

            nb.setLights(Color.RED,1000,1000);
            nb.setVibrate (new long[] {1000,1000,1000,1000});

            Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            nb.setSound(alarmSound);


            nb.setContentTitle("Patient Appointment");
            nb.setContentText("You have an appointment with" + " " + name);
            //nb.setAutoCancel(true);
            nb.setSubText("with token no."+token+" on "+systemdate+" for "+prob);
            Intent i = new Intent(this, MainActivity.class);
            PendingIntent pi = PendingIntent.getActivity(this, 1, i, PendingIntent.FLAG_ONE_SHOT);
            nb.setContentIntent(pi);
            //nb.addAction();
            Notification notification = nb.build();
            NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            nm.notify(1, notification);



        }
        else
        {
            Toast.makeText(this,"no appointment for today",Toast.LENGTH_SHORT).show();
        }
    }
}
