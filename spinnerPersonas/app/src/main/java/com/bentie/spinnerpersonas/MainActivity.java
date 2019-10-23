package com.bentie.spinnerpersonas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Persona personas [] = {
            new Persona("Jaimito", 15),
            new Persona("Jaume", 19),
            new Persona("Miguelito", 45)
    };


    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner_personas);

        spinner.setAdapter(new CustomArrayAdapter(this, R.layout.list_item, personas));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Persona p = personas[position];

                Toast.makeText(MainActivity.this, p.getName() + " tiene " + p.getAge() + " años.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
