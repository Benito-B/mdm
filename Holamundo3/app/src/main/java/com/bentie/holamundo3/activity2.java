package com.bentie.holamundo3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class activity2 extends AppCompatActivity {

    TextView tvGreetings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);

        tvGreetings = findViewById(R.id.tvGreetings);
        Intent intent = getIntent();

        tvGreetings.setText(getString(R.string.greetingsMsg, intent.getStringExtra("name")));
    }

    public void goBack(View view){
        Intent answerIntent = new Intent();
        answerIntent.putExtra("answerMsg", tvGreetings.getText().toString());
        setResult(Activity.RESULT_OK, answerIntent);
        finish();
    }

}
