package com.bentie.figurasaleatorias;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class RandomShapesView extends View {

    private Integer[] backgrounds = {Color.GRAY, Color.LTGRAY, Color.MAGENTA,
            Color.YELLOW, Color.WHITE};
    private Paint[] foregrounds = {makePaint(Color.BLACK), makePaint(Color.BLUE),
            makePaint(Color.GREEN), makePaint(Color.RED)};
    private Bitmap[] emojis = {makeBitmap(R.drawable.angry), makeBitmap(R.drawable.crazy),
            makeBitmap(R.drawable.crying), makeBitmap(R.drawable.happy)};

    private String message = "Androide";

    public RandomShapesView(Context context){
        super(context);
    }

    public RandomShapesView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(RandomUtils.randomElement(backgrounds));
        int width = getWidth();
        int height = getHeight();
        int avgSize = width / 5;
        for(int i=0;i<20;i++){
            drawRandomCircle(canvas, width, height, avgSize);
            drawRandomRect(canvas, width, height, avgSize);
            drawRandomBitmap(canvas, width, height);
            drawRandomText(canvas, width, height, avgSize);
        }
    }

    private void drawRandomCircle(Canvas canvas, int width, int height, int avgSize){
        float x = RandomUtils.randomFloat(width);
        float y = RandomUtils.randomFloat(height);
        float radius = RandomUtils.randomFloat(avgSize / 2);
        Paint color = RandomUtils.randomElement(foregrounds);
        canvas.drawCircle(x, y, radius, color);
    }

    private void drawRandomRect(Canvas canvas, int width, int height, int avgSize){
        float left = RandomUtils.randomFloat(width);
        float top = RandomUtils.randomFloat(height);
        float figureWidth = RandomUtils.randomFloat(avgSize);
        float right = left + figureWidth;
        float bottom = top + width;
        Paint color = RandomUtils.randomElement(foregrounds);
        canvas.drawRect(left, top, right, bottom, color);
    }

    private void drawRandomBitmap(Canvas canvas, int width, int height){
        float left = RandomUtils.randomFloat(width);
        float top = RandomUtils.randomFloat(height);
        Bitmap b = RandomUtils.randomElement(emojis);
        canvas.drawBitmap(b, left, top, null);
    }

    private void drawRandomText(Canvas canvas, int width, int height, int avgSize){
        float x = RandomUtils.randomFloat(width);
        float y = RandomUtils.randomFloat(height);
        float size = RandomUtils.randomFloat(avgSize);
        Paint paint = RandomUtils.randomElement(foregrounds);
        paint.setTextSize(size);
        canvas.drawText(message, x, y, paint);
    }

    private Paint makePaint(int c){
        Paint p = new Paint();
        p.setColor(c);
        return p;
    }

    private Bitmap makeBitmap(int id){
        return BitmapFactory.decodeResource(getResources(), id);
    }


}
