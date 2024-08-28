package com.Wm.Todo.Service.Imp;


import com.Wm.Todo.Exception.ServerUnavilableException;
import com.Wm.Todo.Factory.TaskRepositorySingleInstanceHandler;
import com.Wm.Todo.pack.Task;
import com.Wm.Todo.Repository.TaskRepository;
import com.Wm.Todo.Service.TaskService;

import java.sql.SQLException;
import java.util.List;

public class TaskServiceImp implements TaskService {
    private static TaskRepository taskRepository = null;
    public TaskServiceImp() throws SQLException {
        taskRepository = TaskRepositorySingleInstanceHandler.getInDBTaskRepositoryInstance();
    }
    @Override
    public Task addTask(Task task) throws ServerUnavilableException {
        return taskRepository.addTask(task);
    }

    @Override
    public Task getTaskById(int taskId) throws ServerUnavilableException {
        return taskRepository.getTaskById(taskId);
    }

    @Override
    public Task deleteTaskById(int taskId) throws ServerUnavilableException {
        return taskRepository.deleteTaskById(taskId);
    }

    @Override
    public Task updateTask(Task task) throws ServerUnavilableException {
        return taskRepository.updateTask(task);
    }

    @Override
    public List<Task> getAllTasks(int userId) throws ServerUnavilableException {
        return taskRepository.getAllTasks(userId);
    }

    @Override
    public List<Task> getAllTasksByPriority(int userId, String priority) throws ServerUnavilableException {
        return taskRepository.getAllTasksByPriority(userId, priority);
    }

    @Override
    public List<Task> getAllTasksByDueDateTime(int userId, String order) throws ServerUnavilableException {
        return taskRepository.getAllTasksByDueDateTime(userId,order);
    }

    @Override
    public List<Task> getAllTasksByPriorityOrder(int userId, String order) throws ServerUnavilableException {
        return taskRepository.getAllTasksByPriorityOrder(userId,order);
    }
}