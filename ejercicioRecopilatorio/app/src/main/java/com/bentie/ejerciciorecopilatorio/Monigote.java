package com.bentie.ejerciciorecopilatorio;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;


/**
 * Copyright (c) Benito Barreiro <BenitoBarreiro89@gmail.com>
 * Creado 04/11/2019
 */
public class Monigote extends View {

    public Monigote(Context context) {
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
