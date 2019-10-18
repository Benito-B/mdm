package com.bentie.personaentreactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    int[] imageIds = {R.drawable.chico, R.drawable.chica, R.drawable.chico2, R.drawable.chica2, R.drawable.chico3, R.drawable.chica3};
    int[] ivIds = {R.id.img_11, R.id.img_12, R.id.img_13, R.id.img_21, R.id.img_22, R.id.img_23, R.id.img_31, R.id.img_32, R.id.img_33};
    EditText etName, etSurname, etAge;
    ImageView ivSelected;
    Button btSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.et_person_name);
        etSurname = findViewById(R.id.et_person_surname);
        etAge = findViewById(R.id.et_person_age);
        btSubmit = findViewById(R.id.btSubmit);
        ivSelected = findViewById(R.id.selected_image);


        btSubmit.setOnClickListener(v -> {
            String age = etAge.getText().toString();
            if(TextUtils.isEmpty(age)) age = "0";
            Persona p = new Persona(etName.getText().toString(),
                    etSurname.getText().toString(),
                    Integer.parseInt(age),
                    Integer.parseInt(ivSelected.getTag().toString()));


            Bundle bundle = new Bundle();
            bundle.putSerializable("person", p);

            Intent intent = new Intent(this, Activity2.class);
            intent.putExtras(bundle);
            startActivity(intent);
        });

        setImages();
    }

    private void setImages(){
        ImageView iv;
        for(int i = 0; i<imageIds.length;i++){
            iv = findViewById(ivIds[i]);
            iv.setImageResource(imageIds[i]);
            iv.setOnClickListener(this);
            iv.setTag(imageIds[i]);
        }
    }

    @Override
    public void onClick(View v) {
        ivSelected.setImageResource(Integer.parseInt(v.getTag().toString()));
        ivSelected.setTag(v.getTag());
    }
}
