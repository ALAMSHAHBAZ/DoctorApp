package com.example.lenovo.doctorapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class DocLogin extends AppCompatActivity implements TextToSpeech.OnInitListener {
    SharedPreferences sp;
    TextToSpeech tts;
    SharedPreferences.Editor se;
    String name,pass;
    EditText txtname,txtpass;
    TextView txterror;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_login);
        sp=getSharedPreferences("logininfo",MODE_PRIVATE);
        txtname=(EditText)findViewById(R.id.txtname);
        txtpass=(EditText)findViewById(R.id.txtpass);
        txterror=(TextView)findViewById(R.id.txterror);

tts=new TextToSpeech(this,this);

    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    @Override
    public void onInit(int status)
    {
        int res=tts.setLanguage(Locale.ENGLISH);
        if(res==TextToSpeech.LANG_NOT_SUPPORTED||res==TextToSpeech.LANG_NOT_SUPPORTED)
        {
            Toast.makeText(this,"lang not supported",Toast.LENGTH_SHORT).show();
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void onClick(View v)
    {
        String nm=txtname.getText().toString();
        String ps=txtpass.getText().toString();
        name=sp.getString("name","");
        pass=sp.getString("pass","");

        if (nm.equalsIgnoreCase(name)&&ps.equalsIgnoreCase(pass))
        {
            Intent i=new Intent(this,DoctorActivity.class);
            startActivity(i);
            tts.speak("welcome doctor shahbaz",TextToSpeech.QUEUE_FLUSH,null,null);
        }
        else
        {
            txterror.setText("Invalid UserId/Password");
            tts.speak("incorrect I D or password. Please Re enter",TextToSpeech.QUEUE_FLUSH,null,null);
        }

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(tts!=null)
        {
            tts.stop();
            tts.shutdown();
        }
    }
}
