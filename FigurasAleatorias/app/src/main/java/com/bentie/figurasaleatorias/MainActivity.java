package com.bentie.figurasaleatorias;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btRandomShapes, btDrawShapes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btRandomShapes = findViewById(R.id.bt_random_shapes);
        btRandomShapes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RandomShapesActivity.class);
                startActivity(intent);
            }
        });

        btDrawShapes = findViewById(R.id.bt_draw_shapes);
        btDrawShapes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DrawShapesActivity.class);
                startActivity(intent);
            }
        });
    }
}
