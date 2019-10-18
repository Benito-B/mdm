package com.bentie.distintasformasinvocareventos;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;


public class MyButton extends androidx.appcompat.widget.AppCompatButton implements View.OnClickListener {
    Context context;

    public MyButton(Context context) {
        super(context);
        this.context =context;
        this.setOnClickListener(this); //pedir recoger los eventos
    }



    public MyButton(Context context, AttributeSet attr){
        super(context,attr);
        this.context =context;
        this.setOnClickListener(this);
    }

    public MyButton(Context context, AttributeSet attr, int defaultStyles){

        super(context,attr,defaultStyles);
        this.context =context;
        this.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        Toast.makeText(context, "Pulsado el MyButton", Toast.LENGTH_SHORT).show();
    }


}