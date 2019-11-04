package com.bentie.ejerciciorecopilatorio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bentie.ejerciciorecopilatorio.adapter.ZoneAdapter;
import com.bentie.ejerciciorecopilatorio.model.Shipment;
import com.bentie.ejerciciorecopilatorio.model.Zone;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Zone> zones = new ArrayList<Zone>();
    private TextView tvResult;
    private Button btCalculate;
    private Spinner spZones;
    private EditText etWeight;
    private RadioButton rbUrgent;
    private CheckBox cbGift, cbCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerForContextMenu(findViewById(R.id.linear_layout_main));

        etWeight = findViewById(R.id.et_weight);
        rbUrgent = findViewById(R.id.rb_fare_urgent);
        cbGift = findViewById(R.id.cb_gift);
        cbCard = findViewById(R.id.cb_giftcard);
        tvResult = findViewById(R.id.tv_result);

        spZones = findViewById(R.id.spinnerZone);
        createZones();

        ArrayAdapter<Zone> zoneAdapter = new ZoneAdapter(this, R.layout.zone_spinner_item, zones);
        spZones.setAdapter(zoneAdapter);

        btCalculate = findViewById(R.id.bt_calculate);
        btCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int weight = Integer.parseInt(etWeight.getText().toString().equals("") ? "0":etWeight.getText().toString());
                Shipment shipment = new Shipment(weight,
                        (Zone)spZones.getSelectedItem(), rbUrgent.isChecked(),
                        cbGift.isChecked(), cbCard.isChecked());
                tvResult.setText(MainActivity.this.getApplicationContext().getString(R.string.calculation_result,
                        ShipmentController.getCost(shipment)));
            }
        });
    }


    private void createZones(){
        zones.add(new Zone(1, 10, "España", R.drawable.iberia));
        zones.add(new Zone(2, 20, "Europa", R.drawable.europe));
        zones.add(new Zone(3, 30, "Resto del mundo", R.drawable.resto));
    }

    @SuppressLint("RestrictedApi")//Quita error "setOptionalIconsVisible not usable here blablabla
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(menu instanceof MenuBuilder){
            MenuBuilder mb = (MenuBuilder) menu;
            mb.setOptionalIconsVisible(true);
        }
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.main_menu_send:
                Toast.makeText(this, "Envío", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_menu_about:
                Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
                break;
            case R.id.submenu_one:
                Toast.makeText(this, "Tocaste el sub menú uno", Toast.LENGTH_SHORT).show();
                break;
            case R.id.submenu_two:
                Toast.makeText(this, "Tocaste el sub menú dos", Toast.LENGTH_SHORT).show();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.contextual_menu_opt_one:
                Toast.makeText(this, "Contextual menu 1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.contextual_menu_opt_two:
                Toast.makeText(this, "Contextual menu 2", Toast.LENGTH_SHORT).show();
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.contextual_menu, menu);
    }
}
