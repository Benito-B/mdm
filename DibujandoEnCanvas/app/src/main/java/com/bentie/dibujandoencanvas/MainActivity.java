package com.bentie.dibujandoencanvas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Dibujo(this));
    }

    class Dibujo extends View {

        public Dibujo(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            int width = getWidth()/2;
            int height = getHeight()/2;
            int bodySize = 200;
            Paint pincel = new Paint();
            pincel.setColor(Color.BLUE);
            pincel.setStrokeWidth(15);
            pincel.setStyle(Paint.Style.STROKE);

            canvas.drawCircle(width, height-100, 100, pincel);
            pincel.setStrokeWidth(10);
            pincel.setColor(Color.RED);
            canvas.drawLine(width,height,width,height+bodySize, pincel);
            canvas.drawLine(width, height+50, width+75, height+75, pincel);
            canvas.drawLine(width, height+50, width-75, height+75, pincel);
            canvas.drawLine(width, height+bodySize, width-100, height+bodySize+100, pincel);
            canvas.drawLine(width, height+bodySize, width+100, height+bodySize+100, pincel);
        }
    }

}
