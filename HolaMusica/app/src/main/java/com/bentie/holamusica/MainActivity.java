package com.bentie.holamusica;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private ToggleButton tbt;

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mediaPlayer = MediaPlayer.create(this, R.raw.desigual);
        tbt = findViewById(R.id.tbt);
        tbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tbt.isChecked())
                    mediaPlayer.start();
                else
                    mediaPlayer.stop();
            }
        });
    }
}
