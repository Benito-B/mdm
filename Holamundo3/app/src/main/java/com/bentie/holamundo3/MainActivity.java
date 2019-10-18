package com.bentie.holamundo3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1;
    EditText etName;
    TextView tvAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        tvAnswer = findViewById(R.id.tvAnswer);
    }

    public void greet(View view){
        Intent intent = new Intent(this, activity2.class);
        intent.putExtra("name", etName.getText().toString());

        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (requestCode == REQUEST_CODE) {
            if(resultCode == Activity.RESULT_OK){
                tvAnswer.setText(getString(R.string.answerMsg, intent.getStringExtra("answerMsg")));
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Error al recuperar el mensaje.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
