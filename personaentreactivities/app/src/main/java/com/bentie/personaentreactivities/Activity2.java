package com.bentie.personaentreactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    TextView tvName, tvSurname, tvAge;

    ImageView ivPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        tvName = findViewById(R.id.tv_show_person_name);
        tvSurname = findViewById(R.id.tv_show_person_surname);
        tvAge = findViewById(R.id.tv_show_person_age);
        ivPhoto = findViewById(R.id.tv_show_person_photo);

        Bundle bundle = getIntent().getExtras();
        Persona p = (Persona) bundle.getSerializable("person");

        System.out.println(p.toString());

        tvName.setText("Nombre: " + p.getName());
        tvSurname.setText("Apellido: " + p.getSurname());
        tvAge.setText("Edad: " + p.getAge());
        ivPhoto.setImageResource(p.getPicture());
    }
}
