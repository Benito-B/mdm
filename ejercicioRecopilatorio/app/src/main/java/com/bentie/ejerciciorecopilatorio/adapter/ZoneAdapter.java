package com.bentie.ejerciciorecopilatorio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bentie.ejerciciorecopilatorio.R;
import com.bentie.ejerciciorecopilatorio.model.Zone;

public class ZoneAdapter extends ArrayAdapter<Zone> {

    static class Holder{
        public TextView tvName, tvPrice;
        public ImageView ivImage;
    }

    public ZoneAdapter(@NonNull Context context, int resource, @NonNull Zone[] objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.zone_spinner_item, parent, false);
        return v;
    }
}
