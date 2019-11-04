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
import java.util.List;

public class ZoneAdapter extends ArrayAdapter<Zone> {

    private List<Zone> zones;
    private Context context;

    public ZoneAdapter(@NonNull Context context, int resource, @NonNull List<Zone> objects) {
        super(context, resource, objects);
        this.context = context;
        this.zones = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        Holder h;
        if(v == null) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.zone_spinner_item, parent, false);
            h = new Holder();
            h.ivImage = v.findViewById(R.id.zone_item_image);
            h.tvName = v.findViewById(R.id.zone_item_name);
            h.tvPrice = v.findViewById(R.id.zone_item_price);
            v.setTag(h);
        }else
            h = (Holder) v.getTag();
        Zone zone = zones.get(position);
        h.tvName.setText(zone.getName());
        h.tvPrice.setText(context.getString(R.string.zone_price, zone.getPrice()));
        h.ivImage.setImageResource(zone.getImage());
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    static class Holder{
        public TextView tvName, tvPrice;
        public ImageView ivImage;
    }
}
