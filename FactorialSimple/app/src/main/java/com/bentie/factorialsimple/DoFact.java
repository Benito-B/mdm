package com.bentie.factorialsimple;

import android.widget.TextView;

public class DoFact implements Runnable{

    private int n;
    private TextView tvResult;

    public DoFact(int n, TextView tvResult){
        this.n = n;
        this.tvResult = tvResult;
    }

    public static int factorial(int num){
        if(num == 0)
            return 1;
        else
            return num * factorial(num-1);
    }

    @Override
    public void run() {
        tvResult.setText("El resultado es: " + factorial(n));
    }
}
