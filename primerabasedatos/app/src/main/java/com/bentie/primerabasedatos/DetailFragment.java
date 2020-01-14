package com.bentie.primerabasedatos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DetailFragment extends Fragment {

    private Client client;

    public static DetailFragment newInstance(Client client){
        DetailFragment df = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("client", client);
        df.setArguments(bundle);
        return df;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        client = (Client)getArguments().getSerializable("client");
        System.out.println("Client en fragmento: " + client);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail_layout, container, false);
        TextView tvId = v.findViewById(R.id.tv_client_id);
        TextView tvName = v.getRootView().findViewById(R.id.tv_client_name);
        TextView tvPhone = v.getRootView().findViewById(R.id.tv_client_phone);
        ImageView ivImage = v.getRootView().findViewById(R.id.iv_detail_image);
        tvId.setText(client.getId());
        tvName.setText(client.getName());
        tvPhone.setText(client.getPhone());
        ivImage.setImageResource(client.getImage());
        return v;
    }

}
