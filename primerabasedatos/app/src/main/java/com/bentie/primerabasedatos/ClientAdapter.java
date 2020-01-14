package com.bentie.primerabasedatos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ClientAdapter extends ArrayAdapter<Client> {

    private Context context;
    private List<Client> clients;

    public ClientAdapter(@NonNull Context context, int resource, @NonNull List<Client> objects) {
        super(context, resource, objects);
        this.context = context;
        this.clients = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        Holder h;
        if(v == null){
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_client_item, parent, false);
            h = new Holder();
            h.tvId = v.findViewById(R.id.tv_client_id);
            h.tvName = v.findViewById(R.id.tv_client_name);
            h.tvPhone = v.findViewById(R.id.tv_client_phone);
            v.setTag(h);
        }else
            h = (Holder) v.getTag();

        Client client = clients.get(position);
        h.tvId.setText(client.getId());
        h.tvName.setText(client.getName());
        h.tvPhone.setText(client.getPhone());
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }


    static class Holder{
        public TextView tvId, tvName, tvPhone;
    }

}
