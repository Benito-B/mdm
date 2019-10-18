package com.bentie.holamundo3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class saludo extends AppCompatActivity {

    TextView tvGreeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saludo);

        tvGreeting = findViewById(R.id.tvGreetings);
        Intent intent = getIntent();
        tvGreeting.setText(getString(R.string.helloMsg, intent.getStringExtra("name")));
        Intent reply = new Intent();
        reply.putExtra("replyMsg", getString(R.string.helloMsg, intent.getStringExtra("name")));
        setResult(Activity.RESULT_OK, reply);
        finish();
    }
}
