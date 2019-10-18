package com.bentie.ejerradiobotones;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private RadioButton rbtGreen, rbtBlue, rbtRed;
    private TextView tvColor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rbtGreen = findViewById(R.id.rbtGreen);
        rbtBlue = findViewById(R.id.rbtBlue);
        rbtRed = findViewById(R.id.rbtRed);
        tvColor = findViewById(R.id.tvColor);

        rbtGreen.setOnCheckedChangeListener(this);
        rbtBlue.setOnCheckedChangeListener(this);
        rbtRed.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.rbtGreen:
                tvColor.setBackgroundColor(Color.GREEN);
                break;
            case R.id.rbtBlue:
                tvColor.setBackgroundColor(Color.BLUE);
                break;
            case R.id.rbtRed:
                tvColor.setBackgroundColor(Color.RED);
                break;
        }
    }
}
