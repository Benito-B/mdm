package com.bentie.ejerbotones2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private ImageButton ibtRed, ibtBlue;
    private TextView tvColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ibtRed = findViewById(R.id.ibtRed);
        ibtBlue = findViewById(R.id.ibtBlue);
        tvColor = findViewById(R.id.tvColor);

        ibtRed.setOnClickListener(this);
        ibtBlue.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.ibtBlue:
                tvColor.setBackgroundColor(Color.BLUE);
                break;
            case R.id.ibtRed:
                tvColor.setBackgroundColor(Color.RED);
                break;
        }
    }
}
