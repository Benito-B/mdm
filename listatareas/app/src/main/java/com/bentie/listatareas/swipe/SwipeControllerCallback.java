package com.bentie.listatareas.swipe;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.bentie.listatareas.adapter.RvAdapter;

import static androidx.recyclerview.widget.ItemTouchHelper.*;

public class SwipeControllerCallback extends ItemTouchHelper.SimpleCallback {

    private boolean swipeBack = false;
    private ButtonsState buttonShowingState = ButtonsState.GONE;
    private static final float buttonWidth = 350;
    private RectF buttonInstance = null;
    private RvAdapter.TaskViewHolder currentViewHolder = null;
    private SwipeControllerAction swipeControllerAction;

    public SwipeControllerCallback(SwipeControllerAction swipeControllerAction) {
        //Llamo al super deshabilitando el drag y permitiendo swipe en dirección derecha e izquierda
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.swipeControllerAction = swipeControllerAction;
    }

    //Sobreescribo estos métodos por obligación
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
    }


    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                            float dX, float dY, int actionState, boolean isCurrentlyActive){
        //Si está arrastrando
        if(actionState == ACTION_STATE_SWIPE){
            //Compruebo si el botón se está mostrando y busco qué es mayor, donde se está arrastrando o el ancho del botón
            if(buttonShowingState != ButtonsState.GONE){
                    dX = Math.min(dX, -buttonWidth);
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }else
                //Si el botón se está mostrando, le añado el touchlistener para saber cuándo toca fuera y ocultarlo
                setTouchListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            //Dibujar los botones
            drawButtons(c, viewHolder);
        }
        //Si el botón está invisible simplemente llamar al draw del super para actualizar la posición durante el arrastre
        if (buttonShowingState == ButtonsState.GONE){
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
        //Guardo el viewHolder de la tarea que se está arrastrando
        currentViewHolder = (RvAdapter.TaskViewHolder)viewHolder;
    }


    private void setTouchListener(final Canvas c, final RecyclerView recyclerView, final RecyclerView.ViewHolder viewHolder, final float dX, final float dY, final int actionState, final boolean isCurrentlyActive) {
        recyclerView.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                swipeBack = event.getAction() == MotionEvent.ACTION_CANCEL || event.getAction() == MotionEvent.ACTION_UP;
                if(swipeBack){
                    //Si la posición donde ha dejado de arrastrar es superior al tamaño del botón, lo muestro
                    //Además hago que los demás objetos del super NO sean clickables para desactivar sus callbacks
                    //Ya que ahora cualquier toque en la pantalla solo se usará para ocultar el botón mostrado
                    if(dX < -buttonWidth){
                        buttonShowingState = ButtonsState.VISIBLE;
                        setTouchDownListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                        setItemsClickable(recyclerView, false);
                    }
                }
                return false;
            }
        });
    }

    //Esto se encarga de detectar los toques en la pantalla y ocultar el botón cuando detecta uno
    private void setTouchDownListener(final Canvas c, final RecyclerView recyclerView, final RecyclerView.ViewHolder viewHolder, final float dX, final float dY, final int actionState, final boolean isCurrentlyActive) {
        recyclerView.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    setTouchUpListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                }
                return false;
            }
        });
    }

    //
    private void setTouchUpListener(final Canvas c, final RecyclerView recyclerView, final RecyclerView.ViewHolder viewHolder, final float dX, final float dY, final int actionState, final boolean isCurrentlyActive) {
        recyclerView.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){//Esto da un warning pero precisamente se ha sobreescrito para ignorar el performClick!
                if(event.getAction() == MotionEvent.ACTION_UP){
                    //Este método recorre los hijos del super(es decir, todos en el recycler view)
                    //Ahora el único botón que tendrá listener activo será el de borrar, todos los demás solo devolverán false
                    //lo que descartará el toque.
                    SwipeControllerCallback.super.onChildDraw(c, recyclerView, viewHolder, 0F, dY, actionState, isCurrentlyActive);
                    recyclerView.setOnTouchListener(new View.OnTouchListener(){
                        @Override
                        public boolean onTouch(View v, MotionEvent event){
                            return false;
                        }
                    });
                    //Se vuelven a hacer todos los hijos del padre clickables para poder continuar con la ejecución normal del programa
                    setItemsClickable(recyclerView, true);
                    swipeBack = false;
                    //Si se está mostrando un botón y tenemos un controller para él, disparar el evento que recibirá el MainActivity en su respectiva callback
                    if (swipeControllerAction != null && buttonInstance != null && buttonInstance.contains(event.getX(), event.getY())) {
                        if (buttonShowingState == ButtonsState.VISIBLE) {
                            swipeControllerAction.onButtonClicked(viewHolder.getAdapterPosition());
                        }

                    }
                    //Y por fin ocultar el botón
                    buttonShowingState = ButtonsState.GONE;
                }
                return false;
            }
        });
    }

    //Recorre todos los hijos de un recyclerview modificando si son clickables o no
    private void setItemsClickable(RecyclerView recyclerView, boolean isClickable) {
        for(int i = 0; i < recyclerView.getChildCount(); ++i){
            recyclerView.getChildAt(i).setClickable(isClickable);
        }
    }

    //Impide que se arrastren las cosas fuera de la pantalla
    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection){
        if(swipeBack){
            swipeBack = buttonShowingState != ButtonsState.GONE;
            return 0;
        }
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }

    //Este método es el encargado de dibujar el botón detrás de la tarea al arrastrar
    //para ello utiliza los tamaños de la tarea con un poco de margen para que el botón quede disimulado
    //Una vez que la tarea vuelve a su sitio si no se ha borrado. Así me evito tener que borrar el botón,
    //lo que conllevaría invalidar la interfaz y dibujarla nuevamente, innecesario si el botón mide menos que la tarea en si.
    private void drawButtons(Canvas c, RecyclerView.ViewHolder viewHolder){
        float buttonWidthWithoutPadding = buttonWidth - 20;
        float corners = 16;

        View itemView = viewHolder.itemView;
        Paint p = new Paint();

        RectF rightButton = new RectF(itemView.getRight() - buttonWidthWithoutPadding, itemView.getTop()+30, itemView.getRight()-20, itemView.getBottom()-30);
        p.setColor(Color.RED);
        c.drawRoundRect(rightButton, corners, corners, p);
        drawText("Borrar", c, rightButton, p);

        buttonInstance = null;
        if(buttonShowingState == ButtonsState.VISIBLE)
            buttonInstance = rightButton;
    }

    // Método de conveniencia para poner el texto centrado en el botón
    private void drawText(String text, Canvas c, RectF button, Paint p) {
        float textSize = 60;
        p.setColor(Color.WHITE);
        p.setAntiAlias(true);
        p.setTextSize(textSize);
        float textWidth = p.measureText(text);
        c.drawText(text, button.centerX()-(textWidth/2), button.centerY()+(textSize/2), p);
    }

    //Enum con los estados del botón para facilitarme la legibilidad del código
    enum ButtonsState {
        GONE,
        VISIBLE
    }
}
