package com.bentie.spinnerpersonas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomArrayAdapter extends ArrayAdapter {

    private Context context;
    private Persona[] personas;

    public CustomArrayAdapter(@NonNull Context context, int resource, @NonNull Persona[] objects) {
        super(context, resource, objects);
        this.context = context;
        this.personas = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null)
            v = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);

        Persona p = personas[position];
        TextView tvName = v.findViewById(R.id.tv_person_name);
        tvName.setText(p.getName() + " (" + p.getAge() + " años)");


        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}
