package com.bentie.primerabasedatos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
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
            // Rellenar el Spinner con los clientes
            clients = db.getClients();
            for(int i=1; i<=3; i++){
                String nombre = "cli" + i;
                String telf = "XXXXXXXX" + i;
                db.insertItem(i, nombre, telf);
            }
        }

        clientSpinner.setAdapter(new ClientAdapter(this, R.layout.spinner_client_item, clients));
        Toast.makeText(this, db.getRawClients(), Toast.LENGTH_SHORT).show();
        db.close();
    }
}
