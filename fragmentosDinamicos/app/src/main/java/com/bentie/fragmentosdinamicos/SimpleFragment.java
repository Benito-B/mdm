package com.bentie.fragmentosdinamicos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class SimpleFragment extends Fragment {

    private int num;

    public static SimpleFragment newInstance(int number){
        SimpleFragment f = new SimpleFragment();
        Bundle args = new Bundle();
        args.putInt("num", number);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        num = getArguments().getInt("num");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = null;
        TextView tv;
        if(num % 2 == 0){
            v = inflater.inflate(R.layout.simple1, container, false);
            tv = v.findViewById(R.id.text1);
        }else{
            v = inflater.inflate(R.layout.simple2, container, false);
            tv = v.findViewById(R.id.text2);
            ImageView iv = v.findViewById(R.id.image);
            iv.setImageResource(R.mipmap.ic_launcher);
        }
        tv.setText("Fragmento número: " + num);
        return v;
    }
}
