package com.bentie.examenprimeraevaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bentie.examenprimeraevaluacion.controller.RentCalculator;
import com.bentie.examenprimeraevaluacion.model.MedioTransporte;
import com.bentie.examenprimeraevaluacion.model.Rent;

public class InvoiceActivity extends AppCompatActivity {

    private ImageView ivVehicle;
    private TextView tvModel, tvPrice, tvExtras, tvDays, tvInsurance, tvTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        ivVehicle = findViewById(R.id.invoice_iv_vehicle);
        tvModel = findViewById(R.id.invoice_tv_model);
        tvPrice = findViewById(R.id.invoice_tv_price);
        tvExtras = findViewById(R.id.invoice_tv_extras);
        tvDays = findViewById(R.id.invoice_tv_duration);
        tvInsurance = findViewById(R.id.invoice_tv_insurance);
        tvTotal = findViewById(R.id.invoice_tv_total);

        Bundle b = getIntent().getExtras();
        Rent rent = (Rent)b.getSerializable(RentActivity.RENT_DATA);

        RentCalculator calculator = new RentCalculator(rent);

        ivVehicle.setImageResource(rent.getTransport().getImage());
        tvModel.setText(rent.getTransport().getModel());
        tvPrice.setText(rent.getTransport().getPrice());
        tvExtras.setText(String.valueOf(calculator.getExtrasCost()));
        tvDays.setText(String.valueOf(rent.getDays()));
        tvInsurance.setText(calculator.getInsurance());
        tvTotal.setText(String.valueOf(calculator.calculatePrice()));
    }
}
