package com.bentie.juegobici;


import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.Vector;

public class VistaJuego extends View {
    //Context
    private Context context;
    //	COCHES	//
    private Vector<Grafico> coches;	//Vector con los coches
    private int numCoches = 5;	//Número inicial de coches
    private int numMotos = 3;	//Fragmentos/Motos en que se dividirá un Coche

    // BICI //
    private Grafico bici;
    private int giroBici;	//Incremento en la dirección de la bici
    private float aceleracionBici;	//Aumento de velocidad en la bici
    private static final int PASO_GIRO_BICI = 5;
    private static final float PASO_ACELERACION_BICI = 1.5f;

    // THREAD Y TIEMPO //
    //Hilo encargado de procesar el tiempo
    private HiloJuego hiloJuego;
    //Tiempo que debe transcurrir para procesar cambios (ms)
    private static int PERIODO_PROCESO = 50;
    //Momento en el que se realizó el último proceso
    private long ultimoProceso = 0;

    // PANTALLA TÁCTIL //
    // Las variables mX y mY se utilizarán para recordar
    // las coordenadas del último evento.
    private float mX=0, mY=0;
    private boolean disparo=false;

    // RUEDA //
    private Grafico rueda;
    private static int VELOCIDAD_RUEDA = 50;
    private boolean ruedaActiva;
    private int distanciaRueda;

    // VARIABLES GLOBALES //
    //Controlar si la aplicación está en segundo plano
    private boolean corriendo = false;
    //Controlar si la aplicación está en pausa
    private boolean pausa;



    public VistaJuego(Context contexto, AttributeSet atributos) {
        super(contexto, atributos);
        this.context = contexto;
        Drawable graficoBici, graficoCoche, graficoRueda;
        //Obtenemos la imagen/recurso del coche
        graficoCoche = contexto.getResources().getDrawable(R.drawable.coche);

        //vector para contener todos los coches que irán por la pantalla
        // los coches tienen valores aleatorios en velocidad, dirección y rotación.
        coches = new Vector<Grafico>();
        for (int i=0; i<numCoches; i++) {
            Grafico coche = new Grafico(this, graficoCoche);
            coche.setIncX(Math.random() * 4 - 2);
            coche.setIncY(Math.random() * 4 - 2);
            coche.setAngulo((int) (Math.random() * 360));
            coche.setRotacion((int) (Math.random() * 8 - 4));
            coches.add(coche);
        }

        //BICI
        graficoBici = contexto.getResources().getDrawable(R.drawable.bici);
        bici = new Grafico(this, graficoBici);
        // CONTROL DEL HILO DEL JUEGO
        corriendo = true;

        // RUEDA

        graficoRueda = contexto.getResources().getDrawable(R.drawable.rueda);

        rueda = new Grafico(this, graficoRueda);
        ruedaActiva = false;

    }
    //Al comenzar y dibujar por primera vez la pantalla del juego
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        //Dibujamos los coches en posiciones aleatorias
        for (Grafico coche: coches) {
            do {
                coche.setPosX(Math.random()*(w-coche.getAncho()));
                coche.setPosY(Math.random()*(h-coche.getAlto()));
            } while (coche.distancia(bici) < (w+h)/3);
        }

        //Para la bici ponemos la posición central de la pantalla
        bici.setPosX((w-bici.getAncho())/2);
        bici.setPosY((h-bici.getAlto())/2);

        //HILO QUE CONTROLA EL JUEGO
        hiloJuego = new HiloJuego();
        hiloJuego.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Dibujamos cada uno de los coches
        for (Grafico coche: coches) {
            coche.dibujaGrafico(canvas);
        }
        //Dibujamos la bici
        bici.dibujaGrafico(canvas);
        //Dibujamos la rueda si lo indica la variable ruedaActiva
        if (ruedaActiva)
            rueda.dibujaGrafico(canvas);
    }

    protected synchronized void actualizaMovimiento() {
        long ahora = System.currentTimeMillis();
        // No hacemos nada si el período de proceso no se ha cumplido.
        if (ultimoProceso + PERIODO_PROCESO > ahora) {
            return;
        }
        // Para una ejecución en tiempo real calculamos retardo
        double retardo = (ahora - ultimoProceso) / PERIODO_PROCESO;
        // Actualizamos la posición de la bici
        bici.setAngulo((int) (bici.getAngulo() + giroBici * retardo));
        double nIncX = bici.getIncX() + aceleracionBici
                * Math.cos(Math.toRadians(bici.getAngulo())) * retardo;
        double nIncY = bici.getIncY() + aceleracionBici
                * Math.sin(Math.toRadians(bici.getAngulo())) * retardo;
        if (Grafico.distanciaE(0, 0, nIncX, nIncY) <= Grafico.getMaxVelocidad()) {
            bici.setIncX(nIncX);
            bici.setIncY(nIncY);
        }
        bici.incrementaPos();
        bici.setIncX(0);
        bici.setIncY(0);
        //Movemos los coches
        for (Grafico coche : coches) {
            coche.incrementaPos();
        }
        ultimoProceso = ahora;

        if (ruedaActiva) { //Movemos la rueda
            rueda.incrementaPos();
            distanciaRueda--;
            if (distanciaRueda<0) {
                ruedaActiva = false;
            } else {
                for (int i = 0; i< coches.size(); i++) {
                    if (rueda.verificaColision(coches.elementAt(i))) {
                        destruyeCoche(i);
                        i= coches.size();
                        ruedaActiva=false;
                    }
                }
            }
        }
        //Comprobar si la bici choca con algún coche (= perder la partida)
        for(int i=0;i<coches.size();i++){
            if(bici.verificaColision(coches.elementAt(i))){
                corriendo = false;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "Has perdido!!!!! BBBBBUUUAAARNS", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    } // del metodo actualizaMovimiento

    private class HiloJuego extends Thread {
        @Override
        public void run() {
            while (corriendo) {
                actualizaMovimiento();
            }
        }
    }
    // TODA LA GESTION DE Teclado y Pantalla Taltil
    @Override
    public boolean onKeyDown(int codigoTecla, KeyEvent evento) {
        super.onKeyDown(codigoTecla, evento);
        //Procesamos la pulsación
        boolean pulsacion=true;
        switch (codigoTecla) {
            case KeyEvent.KEYCODE_DPAD_UP:
                aceleracionBici += PASO_ACELERACION_BICI;
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                giroBici -= PASO_GIRO_BICI;
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                giroBici += PASO_GIRO_BICI;
                break;
            case KeyEvent.KEYCODE_DPAD_CENTER:
            case KeyEvent.KEYCODE_ENTER:
                lanzarRueda();
                break;
            default:
                //Si estamos aquí no hemos pulsado nada que nos interese
                pulsacion=false;
                break;
        }
        return pulsacion;
    }

    @Override
    public boolean onKeyUp(int codigoTecla, KeyEvent evento) {
        super.onKeyUp(codigoTecla, evento);
        //Procesamos la pulsación
        boolean pulsacion=true;
        switch (codigoTecla) {
            case KeyEvent.KEYCODE_DPAD_UP:
                //aceleracionBici=0;
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                giroBici=0;
                break;
            default:
                //Si estamos aquí, no hemos pulsado nada que interese
                pulsacion=false;
                break;
        }
        return pulsacion;
    }

    @Override
    public boolean onTouchEvent(MotionEvent evento) {
        super.onTouchEvent(evento);
        //Obtenemos la posición de la pulsación
        float x=evento.getX();
        float y=evento.getY();
        switch (evento.getAction()) {
            //Al comenzar pulsación (ACTION_DOWN) se activa la variable disparo
            case MotionEvent.ACTION_DOWN:
                disparo=true;
                break;
            //Comprobar pulsación continuada con desplazamiento hor/ver.
            //Si es asi, desactivamos disparo: se tratará de un movimiento
            //se trata de un movimiento en vez de  un disparo.
            case MotionEvent.ACTION_MOVE:
                float dx=Math.abs(x-mX);
                float dy=Math.abs(y-mY);
                //Un desplazamiento del dedo horizontal hace girar la bici.
                if (dy<6 && dx>6)
                {
                    giroBici = Math.round((x-mX)/2);
                    disparo = false;
                } else //Un desplazamiento vertical produce una aceleración.
                    if (dx<6 && dy>6)
                    {
                        aceleracionBici = Math.round((mY-y));
                        disparo = false;
                    }
                break;
            //Si se levanta el dedo (ACTION_UP) sin haberse producido desplazamiento horizontal o vertical
            //disparo estará activado y lo que hacemos es disparar
            case MotionEvent.ACTION_UP:
                giroBici = 0;
                //aceleracionBici = 0;
                if (disparo){
                    lanzarRueda();
                }
                break;
        }
        mX=x; mY=y;
        return true;
    }


    private void destruyeCoche(int i) {
        coches.remove(i);
        ruedaActiva = false;
        //Activamos el sonido de explosión
        MediaPlayer miMediaPlayer =
                MediaPlayer.create(getContext(), R.raw.explosion);
        miMediaPlayer.start();
        //Si no quedan coches, detengo el juego y muestro un mensaje de victoria
        if (coches.isEmpty()){
            corriendo = false;
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run(){
                    Toast.makeText(context, "Has acabado con todos los coches!!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void lanzarRueda() {
        rueda.setPosX(bici.getPosX() + bici.getAncho()/2 - rueda.getAncho()/2);
        rueda.setPosY(bici.getPosY() + bici.getAlto()/2 - rueda.getAlto()/2);
        rueda.setAngulo(bici.getAngulo());
        rueda.setIncX(Math.cos(Math.toRadians(rueda.getAngulo())) * VELOCIDAD_RUEDA);
        rueda.setIncY(Math.sin(Math.toRadians(rueda.getAngulo())) * VELOCIDAD_RUEDA);
        distanciaRueda = (int)Math.min(
                this.getWidth() / Math.abs(rueda.getIncX()),
                this.getHeight() / Math.abs(rueda.getIncY())) - 2;
        ruedaActiva = true;
    }

    public HiloJuego getHilo(){
        return hiloJuego;
    }

    public void setCorriendo(boolean corriendo) {
        this.corriendo = corriendo;
    }

    public void setPausa(boolean pausa) {
        this.pausa = pausa;
    }

    /**
     * Obtiene la activity a partir de un contexto, sacando el contexto de dentro de cuantos wrappers sean necesarios
     * @return Activity que ha iniciado la vista
     */
    private Activity getActivity() {
        Context context = getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity)context;
            }
            context = ((ContextWrapper)context).getBaseContext();
        }
        return null;
    }
}
