package com.teeniv.texttospeech;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText edtText;
    AppCompatButton btnText;
    TextToSpeech textToSpeech;
    private long pressedtime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(pressedtime + 2000 > System.currentTimeMillis())
        {
            super.onBackPressed();
            finish();
        } else
        {
            Toast.makeText(this, "Press Back again to exit", Toast.LENGTH_SHORT).show();
        }
        pressedtime = System.currentTimeMillis();

        edtText = findViewById(R.id.edtText);
        btnText = findViewById(R.id.btnText);
        textToSpeech = new TextToSpeech(getApplicationContext(), status -> {
            if(status!=TextToSpeech.ERROR)
            {
                textToSpeech.setLanguage(Locale.ENGLISH);
            }
        });

        btnText.setOnClickListener(v -> textToSpeech.speak(edtText.getText().toString(),TextToSpeech.QUEUE_FLUSH,null));
    }
}