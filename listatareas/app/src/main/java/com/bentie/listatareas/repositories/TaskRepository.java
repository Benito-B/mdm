package com.bentie.listatareas.repositories;

import androidx.lifecycle.MutableLiveData;

import com.bentie.listatareas.database.dao.TaskDAO;
import com.bentie.listatareas.model.Task;

import java.util.List;

public class TaskRepository {

    private static TaskRepository instance;
    private TaskDAO taskDAO = null;
    private List<Task> tasks;

    private TaskRepository(){}

    public static TaskRepository getInstance() {
        if(instance == null)
            instance = new TaskRepository();
        return instance;
    }

    public TaskRepository init(TaskDAO taskDAO){
        this.taskDAO = taskDAO;
        System.out.println("Initializing, taskdao = " + taskDAO);
        tasks = taskDAO.getAllTasks();
        return this;
    }

    public MutableLiveData<List<Task>> getTasks() {
        MutableLiveData<List<Task>> data = new MutableLiveData<>();
        data.setValue(tasks);
        return data;
    }

    public Task insert(Task task){
        Task insertedTask = taskDAO.insert(task);
        return insertedTask;
    }

}
