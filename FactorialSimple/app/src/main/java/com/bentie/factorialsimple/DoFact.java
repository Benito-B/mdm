package com.bentie.factorialsimple;

import android.os.AsyncTask;
import android.widget.TextView;

import java.math.BigInteger;

public class DoFact extends AsyncTask<Integer, Void, Integer> {

    private TextView tvResult;

    public DoFact(TextView tvResult){
        this.tvResult = tvResult;
    }

    public static int factorial(int num){
        if(num == 0)
            return 1;
        else
            return num * factorial(num-1);
    }

    @Override
    protected void onPreExecute() {
        tvResult.setText("Calculando...");
    }

    @Override
    protected Integer doInBackground(Integer... integers) {
        return factorial(integers[0]);
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);
        tvResult.setText("El resultado es: " + integer);
    }
}
