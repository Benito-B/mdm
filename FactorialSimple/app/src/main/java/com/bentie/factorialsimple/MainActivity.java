package com.bentie.factorialsimple;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btFactorize;
    private EditText etNumber;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btFactorize = findViewById(R.id.bt_factorize);
        etNumber = findViewById(R.id.et_number);
        tvResult = findViewById(R.id.tv_result);

        btFactorize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = etNumber.getText().toString();
                int number;
                if(n.equals(""))
                    number = 0;
                else
                    number = Integer.parseInt(n);
                runOnUiThread(new DoFact(number, tvResult));
            }
        });
    }
}
