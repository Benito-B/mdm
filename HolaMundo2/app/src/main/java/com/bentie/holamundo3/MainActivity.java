package com.bentie.holamundo3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final int REQ_COD = 0;
    private EditText etName;
    private TextView tvReply;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvReply = findViewById(R.id.tvReply);
        etName = findViewById(R.id.etName);
    }

    public void saludar(View view){
        Intent intent = new Intent(this, saludo.class);
        intent.putExtra("name", etName.getText().toString());
        startActivityForResult(intent, REQ_COD);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        if(resultCode == Activity.RESULT_OK){
            tvReply.setText(intent.getStringExtra("replyMsg"));
        }
    }

}
