package com.bentie.listapersonas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ArrayList<Persona> people = new ArrayList<>();

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        people.add(new Persona("Benito", 30));
        people.add(new Persona("Miguel", 31));
        people.add(new Persona("Perico", 10));
        people.add(new Persona("Jaimito", 20));

        lv = findViewById(R.id.listView);

        CustomPeopleAdapter adapter = new CustomPeopleAdapter(this, people);
        lv.setAdapter(adapter);

    }
}
