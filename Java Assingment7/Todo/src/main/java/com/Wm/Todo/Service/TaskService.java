package com.Wm.Todo.Service;


import com.Wm.Todo.Exception.ServerUnavilableException;
import com.Wm.Todo.pack.Task;

import java.util.List;

public interface TaskService {
    public Task addTask(Task task) throws ServerUnavilableException;

    public Task getTaskById(int taskId) throws ServerUnavilableException;

    public Task deleteTaskById(int taskId) throws ServerUnavilableException;

    public Task updateTask(Task task) throws ServerUnavilableException;

    public List<Task> getAllTasks(int userId) throws ServerUnavilableException;

    public List<Task> getAllTasksByPriority(int userId, String priority) throws ServerUnavilableException;

    public List<Task> getAllTasksByDueDateTime(int userId, String order) throws ServerUnavilableException;

    public List<Task> getAllTasksByPriorityOrder(int userId, String order) throws ServerUnavilableException;
}