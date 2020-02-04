package com.bentie.listatareas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bentie.listatareas.adapter.RvAdapter;
import com.bentie.listatareas.database.DatabaseHelper;
import com.bentie.listatareas.database.dao.TaskDAO;
import com.bentie.listatareas.model.Task;
import com.bentie.listatareas.viewmodel.MainActivityViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;
    //Adaptador como miembro de clase para poder accederlo en el callback del observe
    private RvAdapter adapter;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Crear un taskDAO para pasarle al repository desde el viewmodel
        TaskDAO taskDAO = new TaskDAO(this, new DatabaseHelper(this).open().getDb());
        //ViewModelProviders.of() está deprecated, me costó bastante encontrar la manera de
        // obtener un viewmodel ahora, gracias a stackOverflow por esta línea!
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        mainActivityViewModel.init(taskDAO);
        //Observo el LiveData, basicamente esto dispara el método onChanged cada vez que detecta
        // un cambio en la lista de tareas
        mainActivityViewModel.getTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                //Notificar al adaptador de que la lista ha cambiado para que se actualice
                adapter.notifyDataSetChanged();
                if(mainActivityViewModel.getTasks().getValue().size() > 0)
                    rv.smoothScrollToPosition(mainActivityViewModel.getTasks().getValue().size() - 1);
            }
        });

        rv = findViewById(R.id.recycler_view);
        adapter = new RvAdapter(mainActivityViewModel.getTasks().getValue(), this);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);

        //Floating action button para añadir tareas
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddTaskDialog();
            }
        });

    }

    private void showAddTaskDialog(){
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        final View customDialog = inflater.inflate(R.layout.add_task_dialog, null);
        final EditText tvTaskName, tvTaskDesc, tvTaskDate;
        final Spinner spUrgency;

        String[] arraySpinner = new String[]{"Urgente", "Normal", "Baja"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, arraySpinner);

        tvTaskName = customDialog.findViewById(R.id.add_task_name);
        tvTaskDesc = customDialog.findViewById(R.id.add_task_desc);
        tvTaskDate = customDialog.findViewById(R.id.add_task_date);
        spUrgency = customDialog.findViewById(R.id.add_task_urgency);
        spUrgency.setAdapter(spinnerAdapter);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Nueva tarea")
                .setView(customDialog)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Task t = new Task();
                        t.setName(tvTaskName.getText().toString());
                        t.setDescription(tvTaskDesc.getText().toString());
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.forLanguageTag("ES"));
                        try {
                            Date date = formatter.parse(tvTaskDate.getText().toString());
                            t.setFinishDate(date);
                            System.out.println("DATE:::::::::::::::::::::::::::::::::::::::::::::::: " + date.getTime());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        switch (spUrgency.getSelectedItem().toString()){
                            case "Urgente":
                                t.setUrgency(R.drawable.red_square);
                                break;
                            case "Baja":
                                t.setUrgency(R.drawable.green_square);
                                break;
                            case "Normal":
                            default:
                                t.setUrgency(R.drawable.yellow_square);
                        }
                        mainActivityViewModel.insertTask(t);
                    }
                })
                .setNegativeButton("Cancelar", null).create();
        dialog.show();
    }

}
