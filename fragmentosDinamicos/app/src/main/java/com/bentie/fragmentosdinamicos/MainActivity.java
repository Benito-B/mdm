package com.bentie.fragmentosdinamicos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private int stackPosition = 1;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt = findViewById(R.id.bt_new_fragment);
        bt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                stackPosition++;
                if(savedInstanceState == null){
                    Fragment newFragment = SimpleFragment.newInstance(stackPosition);
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, newFragment)
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("position", stackPosition);
        super.onSaveInstanceState(outState);
    }
}
