package com.bentie.listapersonas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;


public class CustomPeopleAdapter extends ArrayAdapter{

    private ArrayList<Persona> data;

    private Context context;

    public CustomPeopleAdapter(@NonNull Context context, ArrayList<Persona> data) {
        super(context, R.layout.list_item, data);
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(convertView == null)
            v = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);

        Persona p = data.get(position);
        TextView tvName = v.findViewById(R.id.tv_person_name);
        TextView tvAge = v.findViewById(R.id.tv_person_age);

        System.out.println("[DEBUG] Name: " + p.getName() + " - Age: " + p.getAge());
        tvName.setText(p.getName());
        tvAge.setText(String.valueOf(p.getAge()));

        return v;
    }

    @Override
    public int getCount() {
        return data.size();
    }
}
