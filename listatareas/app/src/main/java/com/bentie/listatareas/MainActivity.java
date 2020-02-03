package com.bentie.listatareas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.bentie.listatareas.adapter.RvAdapter;
import com.bentie.listatareas.model.Task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv = findViewById(R.id.recycler_view);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1, "asd", "desc", new Date(1590760377), R.color.card_informative));
        tasks.add(new Task(2, "Urgent task", "Very, veeeeeery urgent task.", new Date(1580761377), R.color.card_urgent));
        rv.setAdapter(new RvAdapter(tasks, this));
    }
}
