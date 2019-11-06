package com.bentie.ejerciciorecopilatorio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bentie.ejerciciorecopilatorio.model.Shipment;

public class ShipmentActivity extends AppCompatActivity {

    private TextView tvZoneName, tvCost, tvFare, tvWeight, tvDecoration;
    private ImageView ivZone;
    private Button btBack;
    private Shipment shipment;
    private ShipmentController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipment);

        tvZoneName = findViewById(R.id.shipment_zone_name);
        tvCost = findViewById(R.id.shipment_cost);
        tvFare = findViewById(R.id.shipment_zone_fare);
        tvWeight = findViewById(R.id.shipment_zone_weight);
        tvDecoration = findViewById(R.id.shipment_decoration);
        ivZone = findViewById(R.id.shipment_zone_image);
        btBack = findViewById(R.id.shipment_bt_back);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        registerForContextMenu(ivZone);

        Bundle bundle = getIntent().getExtras();
        shipment = (Shipment) bundle.getSerializable("Shipment");
        controller = new ShipmentController(shipment);

        fillData();
    }

    private void fillData(){
        tvZoneName.setText(getString(R.string.shipment_zone_name, shipment.getZone().getName()));
        tvCost.setText(getString(R.string.shipment_cost, controller.getCost()));
        tvFare.setText(getString(R.string.shipment_zone_fare, controller.getFare()));
        tvWeight.setText(getString(R.string.shipment_weight, shipment.getWeight()));
        tvDecoration.setText(getString(R.string.shipment_decoration, controller.getDecorations()));
        ivZone.setImageResource(shipment.getZone().getImage());
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.contextual_menu_opt_one:
                Toast.makeText(this, shipment.getZone().getDescription(), Toast.LENGTH_SHORT).show();
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
