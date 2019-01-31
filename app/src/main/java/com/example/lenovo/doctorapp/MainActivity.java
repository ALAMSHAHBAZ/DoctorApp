package com.example.lenovo.doctorapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends Activity implements TextToSpeech.OnInitListener {
    RadioGroup rg;
    TextToSpeech tts;
    Button button;
    RadioButton rbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg = (RadioGroup) findViewById(R.id.rg);
        button = (Button) findViewById(R.id.button);
        tts=new TextToSpeech(this,this);


        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                int selectedId=rg.getCheckedRadioButtonId();
                rbtn  =(RadioButton)findViewById(selectedId);
                switch (selectedId)
                {
                    case R.id.doc:
                        Intent i1=new Intent(MainActivity.this,DocLogin.class);
                        startActivity(i1);
                        tts.speak("this is the Doctor login page",TextToSpeech.QUEUE_FLUSH,null,null);
                        break;

                    case R.id.cle:
                        Intent i2=new Intent(MainActivity.this,ClerkLogin.class);
                        startActivity(i2);
                        tts.speak("this is the Clerk login page",TextToSpeech.QUEUE_FLUSH,null,null);
                        break;

                    default:break;
                }
            }
        });

    }


    @Override
    public void onInit(int status) {
        int res=tts.setLanguage(Locale.ENGLISH);
        if(res==TextToSpeech.LANG_NOT_SUPPORTED||res==TextToSpeech.LANG_NOT_SUPPORTED)
        {
            Toast.makeText(this,"lang not supported",Toast.LENGTH_SHORT).show();
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




