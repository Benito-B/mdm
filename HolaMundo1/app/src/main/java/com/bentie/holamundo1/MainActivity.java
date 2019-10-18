package com.bentie.holamundo1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText etName;
    private TextView helloMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        helloMsg = findViewById(R.id.tvHello);
    }

    public void saludar(View view){
        helloMsg.setText(getString(R.string.helloMsg, etName.getText().toString()));
    }


}
