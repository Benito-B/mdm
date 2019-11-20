package com.bentie.figurasaleatorias;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class RandomShapesActivity extends AppCompatActivity {

    private RandomShapesView randomShapesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_shapes);

        randomShapesView = findViewById(R.id.random_shapes_drawing_area);
    }

    public void redraw(View clickedView){
        randomShapesView.invalidate();
    }
}
