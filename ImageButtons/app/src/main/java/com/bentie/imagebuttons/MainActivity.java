package com.bentie.imagebuttons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton btLinear, btGrid, btRelative, btTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btLinear = findViewById(R.id.btLinear);
        btGrid = findViewById(R.id.btGrid);
        btRelative = findViewById(R.id.btRelative);
        btTable = findViewById(R.id.btTable);

        btLinear.setOnClickListener(this);
        btGrid.setOnClickListener(this);
        btRelative.setOnClickListener(this);
        btTable.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btGrid:
                startActivity(new Intent(this, GridActivity.class));
                break;
            case R.id.btLinear:
                startActivity(new Intent(this, LinearActivity.class));
                break;
            case R.id.btRelative:
                startActivity(new Intent(this, RelativeActivity.class));
                break;
            case R.id.btTable:
                startActivity(new Intent(this, TableActivity.class));
                break;
        }
    }
}
