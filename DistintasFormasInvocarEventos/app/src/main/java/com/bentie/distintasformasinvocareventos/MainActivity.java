package com.bentie.distintasformasinvocareventos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt = findViewById(R.id.cmdTres);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Pulsado en botón tres", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void clicked(View view){
        Toast.makeText(this, "Pinchado en botón dos", Toast.LENGTH_SHORT).show();
    }
}
