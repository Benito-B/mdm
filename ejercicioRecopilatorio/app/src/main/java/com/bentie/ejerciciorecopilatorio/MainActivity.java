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
import android.widget.Spinner;
import android.widget.Toast;

import com.bentie.ejerciciorecopilatorio.model.Zone;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Zone> zones = new ArrayList<Zone>();

    private Spinner spZones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerForContextMenu(findViewById(R.id.linear_layout_main));

        spZones = findViewById(R.id.spinnerZone);

        ArrayAdapter<Zone> zoneAdapter = new ArrayAdapter<Zone>(this, R.layout.support_simple_spinner_dropdown_item, zones);
        spZones.setAdapter(zoneAdapter);
    }


    private void createZones(){
        zones.add(new Zone(1, 10, "Españita", R.drawable.europe));
        zones.add(new Zone(2, 20, "Otros", R.drawable.europe));
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
