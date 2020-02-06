package com.bentie.listatareas.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bentie.listatareas.database.dao.TaskDAO;
import com.bentie.listatareas.model.Task;
import com.bentie.listatareas.repositories.TaskRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<List<Task>> tasks;
    private TaskRepository taskRepository;

    public void init(TaskDAO taskDAO){
        if(tasks != null)
            return;
        taskRepository = TaskRepository.getInstance().init(taskDAO);
        tasks = taskRepository.getTasks();
    }

    public void insertTask(Task task){
        List<Task> currentTasks = tasks.getValue();
        Task newTask = taskRepository.insert(task);
        currentTasks.add(newTask);
        tasks.postValue(currentTasks);
    }

    public void deleteTask(int position){
        List<Task> currentTasks = tasks.getValue();
        Task toDelete = currentTasks.get(position);
        currentTasks.remove(toDelete);
        tasks.postValue(currentTasks);
        taskRepository.delete(toDelete);
    }

    public LiveData<List<Task>> getTasks(){
        return tasks;
    }
}
