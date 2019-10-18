package com.bentie.listasimple;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    Button btSpinner, btList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btSpinner = findViewById(R.id.btSpinner);
        btList = findViewById(R.id.btList);

        btSpinner.setOnClickListener(this);
        btList.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch(v.getId()){
            case R.id.btList:
                intent = new Intent(this, ActivityList.class);
                break;
            case R.id.btSpinner:
                intent = new Intent(this, ActivitySpinner.class);
                break;
        }
        startActivity(intent);
    }
}
