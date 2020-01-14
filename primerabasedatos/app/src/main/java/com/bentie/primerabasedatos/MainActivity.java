package com.bentie.primerabasedatos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private Spinner clientSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clientSpinner = findViewById(R.id.sp_clients);
        List<Client> clients = new ArrayList<>();
        db = new DatabaseHelper(this);
        db.open();
        if(db != null){

            for(int i=1; i<=3; i++){
                String nombre = "cli" + i;
                String telf = "XXXXXXXX" + i;
                String charResource = "char" + i;
                db.insertItem(i, nombre, telf, getResources().getIdentifier(charResource,
                        "drawable",
                        getPackageName())
                );
            }
            // Rellenar el Spinner con los clientes
            clients = db.getClients();
        }

        clientSpinner.setAdapter(new ClientAdapter(this, R.layout.spinner_client_item, clients));
        Toast.makeText(this, db.getRawClients(), Toast.LENGTH_SHORT).show();
        db.close();
       /* clientSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Client selectedClient = (Client)clientSpinner.getSelectedItem();
                System.out.println("Client en activity: " + selectedClient);
                Fragment fragment = DetailFragment.newInstance(selectedClient);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
    }
}
