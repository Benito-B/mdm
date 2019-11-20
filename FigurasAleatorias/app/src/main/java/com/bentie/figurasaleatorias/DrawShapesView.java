package com.bentie.figurasaleatorias;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.Shape;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.bentie.figurasaleatorias.model.DrawShapesCounter;

import java.util.ArrayList;
import java.util.List;

public class DrawShapesView extends View {

    private List<ShapeDrawable> shapes = new ArrayList<>();
    private Integer[] colors = {Color.BLACK, Color.BLUE, Color.GREEN, Color.RED};
    private DrawShapesCounter counter = new DrawShapesCounter();

    public DrawShapesView(Context context) {
        super(context);
    }

    public DrawShapesView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCounter(canvas);
        for(ShapeDrawable shape : shapes) {
            shape.draw(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            int x = (int)event.getX();
            int y = (int)event.getY();
            //Margen para no tocar en el texto
            if(y < 150) return true;
            if(!isDeletingExistingShape(x, y)){
                ShapeDrawable shapeDrawable = makeShapeDrawable(x, y);
                shapes.add(shapeDrawable);
                switch(shapeDrawable.getPaint().getColor()){
                    case Color.BLACK:
                        counter.increaseBlack();
                        break;
                    case Color.BLUE:
                        counter.increaseBlue();
                        break;
                    case Color.GREEN:
                        counter.increaseGreen();
                        break;
                    case Color.RED:
                        counter.increaseRed();
                        break;
                }
            }
            invalidate();
            return true;
        }
        return false;
    }

    private void drawCounter(Canvas canvas){
        String s = "Lista de figuras por color: ";
            s += "Negro: " + counter.getBlack() + " ";
            s += "Azul: " + counter.getBlue() + " ";
            s += "Verde: " + counter.getGreen() + " ";
            s += "Rojo: " + counter.getRed();
        Paint p = new Paint();
        p.setColor(Color.BLACK);
        p.setTextSize(35);
        canvas.drawText(s, 20, 50, p);
    }

    private boolean isDeletingExistingShape(int x, int y){
        for(ShapeDrawable shape : shapes){
            Rect bounds = shape.getBounds();
            if(bounds.contains(x, y)){
                switch(shape.getPaint().getColor()){
                    case Color.BLACK:
                        counter.decreaseBlack();
                        break;
                    case Color.BLUE:
                        counter.decreaseBlue();
                        break;
                    case Color.GREEN:
                        counter.decreaseGreen();
                        break;
                    case Color.RED:
                        counter.decreaseRed();
                        break;
                }
                shapes.remove(shape);
                return true;
            }
        }
        return false;
    }

    private ShapeDrawable makeShapeDrawable(int x, int y){
        int maxWidth = getWidth() / 10;
        int maxHeight = getHeight() / 10;
        Shape shape;
        if(Math.random() < 0.5)
            shape = new OvalShape();
        else
            shape = new RectShape();

        ShapeDrawable shapeDrawable = new ShapeDrawable(shape);
        int width = RandomUtils.randomInt(maxWidth) + 15;
        int height = RandomUtils.randomInt(maxHeight) + 15;
        shapeDrawable.setBounds(x-width/2, y-height/2, x+width/2, y+width/2);
        shapeDrawable.getPaint().setColor(RandomUtils.randomElement(colors));
        return shapeDrawable;
    }
}
