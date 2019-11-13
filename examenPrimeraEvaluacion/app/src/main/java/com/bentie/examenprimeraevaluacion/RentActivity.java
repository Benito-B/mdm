package com.bentie.examenprimeraevaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.bentie.examenprimeraevaluacion.controller.RentCalculator;
import com.bentie.examenprimeraevaluacion.controller.adapter.VehicleAdapter;
import com.bentie.examenprimeraevaluacion.model.MedioTransporte;
import com.bentie.examenprimeraevaluacion.model.Rent;

import java.util.ArrayList;

public class RentActivity extends AppCompatActivity {

    public static final String RENT_DATA = "rent";

    private ArrayList<MedioTransporte> vehicles;
    private Spinner spVehicles;
    private Button btTotal, btInvoice;
    private CheckBox cbHelmet, cbGps, cbExtras;
    private RadioButton rbInsurance;
    private EditText etDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent);
        //Lo primero que hago es obtener el bundle que he metido al intent en la activity anterior
        Bundle bundle = getIntent().getExtras();
        //Y a continuación saco el arrayList de dentro
        vehicles = (ArrayList<MedioTransporte>) bundle.getSerializable(MainActivity.DATA_BUNDLE);
        //Asigno los elementos
        spVehicles = findViewById(R.id.rent_sp_vehicles);
        btTotal = findViewById(R.id.rent_bt_total);
        btInvoice = findViewById(R.id.rent_bt_invoice);
        cbHelmet = findViewById(R.id.rent_cb_helmet);
        cbGps = findViewById(R.id.rent_cb_gps);
        cbExtras = findViewById(R.id.rent_cb_extras);
        rbInsurance = findViewById(R.id.rent_rb_insurance);
        etDays = findViewById(R.id.rent_et_days);
        //Creo el adapter y lo asigno al spinner pasándole la lista de elementos que saqué del bundle
        ArrayAdapter<MedioTransporte> adapter = new VehicleAdapter(this, R.layout.transport_item, vehicles);
        spVehicles.setAdapter(adapter);
        //Añado un listener al botón del total
        btTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RentCalculator calculator = new RentCalculator(getRent());
                Toast.makeText(RentActivity.this, "El precio del alquiler sería de " +
                        calculator.calculatePrice(), Toast.LENGTH_SHORT).show();
            }
        });
        //Añado listener al botón de la factura
        btInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(RENT_DATA, getRent());
                Intent intent = new Intent(RentActivity.this, InvoiceActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    /**
     * Método para obtener un objeto tipo Rent a partir de los datos introducidos por el usuario
     * @return nuevo Rent con los datos introducidos
     */
    private Rent getRent(){
        Rent r = new Rent();
        r.setTransport((MedioTransporte) spVehicles.getSelectedItem());
        r.setDays(TextUtils.isEmpty(etDays.getText().toString()) ? 1: Integer.parseInt(etDays.getText().toString()));
        r.setHasExtras(cbExtras.isChecked());
        r.setHasGps(cbGps.isChecked());
        r.setHasHelmet(cbHelmet.isChecked());
        r.setHasInsurance(rbInsurance.isChecked());
        return r;
    }
}
