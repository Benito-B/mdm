package com.bentie.examenprimeraevaluacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bentie.examenprimeraevaluacion.model.MedioTransporte;

import java.util.ArrayList;
import java.util.Arrays;

//Implemento la interfaz View.OnClickListener ya que así puedo usar la propia clase como contexto
// listener y clase para lanzar nuevas actividades a partir de ella
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String DATA_BUNDLE = "vehicles";
    private static final int TYPE_ELECTRIC = 1;
    private static final int TYPE_BIKE = 2;
    private static final int TYPE_CAR = 3;

    private MedioTransporte[] electricos = new MedioTransporte[]{
            new MedioTransporte("skate", "Roxi", "12", R.drawable.skate),
            new MedioTransporte("patinete", "Roxi", "15", R.drawable.monociclo1),
            new MedioTransporte("monociclo", "Oneil", "18", R.drawable.monociclo2)};

    private MedioTransporte[] bicis = new MedioTransporte[]{
            new MedioTransporte("Paseo", "Orbea", "15", R.drawable.bici1),
            new MedioTransporte("Ciudad", "Cube", "20", R.drawable.bici2),
            new MedioTransporte("Montaña", "Bike", "25", R.drawable.bici3)};

    private MedioTransporte[] coches = new MedioTransporte[]{
            new MedioTransporte("Megane", "Renault", "60", R.drawable.megan1),
            new MedioTransporte("Leon", "Seat", "70", R.drawable.leon3),
            new MedioTransporte("Fiesta", "Ford", "75", R.drawable.fiesta2)};

    private ImageView ivBikes, ivCars, ivElectrics, ivSelected;
    private Button btNext;
    private int typeSelected = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Asigno los elementos
        ivElectrics = findViewById(R.id.img_11);
        ivBikes = findViewById(R.id.img_12);
        ivCars = findViewById(R.id.img_13);
        ivSelected = findViewById(R.id.imgResul);
        btNext = findViewById(R.id.btn);
        //Les asigno a cada uno un listener (esta misma clase ya que implementa View.OnClickListener)
        ivElectrics.setOnClickListener(this);
        ivBikes.setOnClickListener(this);
        ivCars.setOnClickListener(this);
        btNext.setOnClickListener(this);
    }

    //Sobreescribo el método onClick de View.OnClickListener para poder utilizar esta misma clase como listener
    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        //Hago un switch con las ID de los elementos que se pueden tocar
        switch(v.getId()){
            case R.id.img_11://Imagen de patinetes
                //Seteo la imagen del tipo seleccionado a la correspondiente
                ivSelected.setImageResource(R.drawable.patinete);
                //Y después seteo el flag al valor correcto para poder continuar a la siguiente activity
                typeSelected = TYPE_ELECTRIC;
                break;
            case R.id.img_12://Imagen de bicis
                ivSelected.setImageResource(R.drawable.bicis);
                typeSelected = TYPE_BIKE;
                break;
            case R.id.img_13://Imagen de coches
                ivSelected.setImageResource(R.drawable.coches);
                typeSelected = TYPE_CAR;
                break;
            case R.id.btn://Botón continuar
                //En caso de que sea el botón compruebo que primero se haya seleccionado un tipo de vehículo
                if(typeSelected < 0){
                    Toast.makeText(this, "Selecciona un tipo de vehículo para alquilar.", Toast.LENGTH_SHORT).show();
                    return;
                }
                //Si hay un tipo seleccionado lo primero que hago es rellenar un arraylist con los transportes
                ArrayList<MedioTransporte> transports= new ArrayList<MedioTransporte>();
                switch(typeSelected){
                    case TYPE_ELECTRIC:
                        transports.addAll(Arrays.asList(electricos));
                        break;
                    case TYPE_BIKE:
                        transports.addAll(Arrays.asList(bicis));
                        break;
                    case TYPE_CAR:
                        transports.addAll(Arrays.asList(coches));
                        break;
                }
                //Si hay tipo seleccionado creo un intent, le añado el bundle con los datos e inicio la activity
                bundle.putSerializable(DATA_BUNDLE, transports);
                Intent intent = new Intent(this, RentActivity.class);
                intent.putExtras(bundle);
                System.out.println(bundle);
                System.out.println("Bundle: " + bundle.getSerializable(DATA_BUNDLE));
                startActivity(intent);
            break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.main_menu_logo:
                Intent intent = new Intent(this, LogoActivity.class);
                startActivity(intent);
                break;
            case R.id.main_menu_help:
            case R.id.main_menu_copy:
                Toast.makeText(this, "Hecho por Benito Barreiro.", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
