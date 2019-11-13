package com.bentie.examenprimeraevaluacion.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bentie.examenprimeraevaluacion.R;
import com.bentie.examenprimeraevaluacion.model.MedioTransporte;

import java.util.ArrayList;
import java.util.List;

public class VehicleAdapter extends ArrayAdapter<MedioTransporte> {

    class Holder{
        TextView tvName;
        TextView tvModel;
        TextView tvPrice;
        ImageView ivImage;
    }

    private List<MedioTransporte> transports;

    public VehicleAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MedioTransporte> transports) {
        super(context, resource, transports);
        this.transports = transports;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        Holder h;
        if(v == null){
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.transport_item, parent, false);
            h = new Holder();
            h.tvName = v.findViewById(R.id.item_tv_name);
            h.tvModel = v.findViewById(R.id.item_tv_model);
            h.tvPrice = v.findViewById(R.id.item_tv_price);
            h.ivImage = v.findViewById(R.id.item_iv_image);
            v.setTag(h);
        }else
            h = (Holder)v.getTag();
        MedioTransporte vehicle = transports.get(position);
        h.tvName.setText(vehicle.getName());
        h.tvModel.setText(vehicle.getModel());
        h.tvPrice.setText(vehicle.getPrice());
        h.ivImage.setImageResource(vehicle.getImage());
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public int getCount() {
        return transports.size();
    }
}
